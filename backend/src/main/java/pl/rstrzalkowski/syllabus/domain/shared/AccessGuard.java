package pl.rstrzalkowski.syllabus.domain.shared;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.domain.exception.activity.ActivityNotFoundException;
import pl.rstrzalkowski.syllabus.domain.exception.realisation.NotAffiliatedWithRealisationException;
import pl.rstrzalkowski.syllabus.domain.exception.realisation.RealisationNotFoundException;
import pl.rstrzalkowski.syllabus.domain.model.Activity;
import pl.rstrzalkowski.syllabus.domain.model.Realisation;
import pl.rstrzalkowski.syllabus.domain.model.Role;
import pl.rstrzalkowski.syllabus.domain.model.User;
import pl.rstrzalkowski.syllabus.infrastructure.repository.ActivityRepository;
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

    public void checkAccessToRealisation(Long realisationId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            throw new NotAffiliatedWithRealisationException();
        }
        List<Role> privilegedRoles = new ArrayList<>(Arrays.asList(Role.OFFICE, Role.DIRECTOR, Role.ADMIN));

        Realisation realisation = realisationRepository.findById(realisationId)
                .orElseThrow(RealisationNotFoundException::new);

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
}
