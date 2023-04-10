package pl.rstrzalkowski.syllabus.domain.shared;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.domain.exception.activity.ActivityNotFoundException;
import pl.rstrzalkowski.syllabus.domain.exception.grade.GradeNotFoundException;
import pl.rstrzalkowski.syllabus.domain.exception.post.PostNotFoundException;
import pl.rstrzalkowski.syllabus.domain.exception.realisation.NotAffiliatedWithRealisationException;
import pl.rstrzalkowski.syllabus.domain.exception.realisation.RealisationArchivedException;
import pl.rstrzalkowski.syllabus.domain.exception.realisation.RealisationNotFoundException;
import pl.rstrzalkowski.syllabus.domain.model.Activity;
import pl.rstrzalkowski.syllabus.domain.model.Grade;
import pl.rstrzalkowski.syllabus.domain.model.Post;
import pl.rstrzalkowski.syllabus.domain.model.Realisation;
import pl.rstrzalkowski.syllabus.domain.model.Role;
import pl.rstrzalkowski.syllabus.domain.model.User;
import pl.rstrzalkowski.syllabus.infrastructure.repository.ActivityRepository;
import pl.rstrzalkowski.syllabus.infrastructure.repository.GradeRepository;
import pl.rstrzalkowski.syllabus.infrastructure.repository.PostRepository;
import pl.rstrzalkowski.syllabus.infrastructure.repository.RealisationRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AccessGuard {

    private final RealisationRepository realisationRepository;
    private final ActivityRepository activityRepository;
    private final PostRepository postRepository;
    private final GradeRepository gradeRepository;

    public void checkAccessToRealisation(Long realisationId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            throw new NotAffiliatedWithRealisationException();
        }
        List<Role> privilegedRoles = new ArrayList<>(Arrays.asList(Role.OFFICE, Role.DIRECTOR, Role.ADMIN));

        Realisation realisation = realisationRepository.findById(realisationId)
                .orElseThrow(RealisationNotFoundException::new);

        if (realisation.isArchived() && !privilegedRoles.contains(user.getRole())) {
            throw new RealisationArchivedException();
        }

        boolean isAffiliatedAsStudent = realisation.getSchoolClass()
                .getStudents()
                .stream()
                .anyMatch((student -> Objects.equals(student.getId(), user.getId())));

        boolean isAffiliatedAsTeacher = Objects.equals(realisation.getTeacher().getId(), user.getId());

        if (!isAffiliatedAsStudent && !isAffiliatedAsTeacher && !privilegedRoles.contains(user.getRole())) {
            throw new NotAffiliatedWithRealisationException();
        }
    }

    public void checkAccessToActivity(Long activityId) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(ActivityNotFoundException::new);
        checkAccessToRealisation(activity.getRealisation().getId());
    }

    public void checkAccessToPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        checkAccessToRealisation(post.getRealisation().getId());
    }

    public void checkAccessToGrade(Long gradeId) {
        Grade grade = gradeRepository.findById(gradeId)
                .orElseThrow(GradeNotFoundException::new);
        checkAccessToRealisation(grade.getActivity().getRealisation().getId());
    }
}
