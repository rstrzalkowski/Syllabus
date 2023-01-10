package pl.rstrzalkowski.syllabus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.rstrzalkowski.syllabus.domain.Activity;
import pl.rstrzalkowski.syllabus.domain.SubjectRealisation;
import pl.rstrzalkowski.syllabus.domain.User;
import pl.rstrzalkowski.syllabus.dto.create.CreateActivityDTO;
import pl.rstrzalkowski.syllabus.dto.update.UpdateActivityDTO;
import pl.rstrzalkowski.syllabus.exception.activity.ActivityNotFoundException;
import pl.rstrzalkowski.syllabus.exception.course.SubjectRealisationNotFound;
import pl.rstrzalkowski.syllabus.exception.post.PostNotFoundException;
import pl.rstrzalkowski.syllabus.repository.ActivityRepository;
import pl.rstrzalkowski.syllabus.repository.SubjectRealisationRepository;
import pl.rstrzalkowski.syllabus.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final SubjectRealisationRepository subjectRealisationRepository;
    private final UserRepository userRepository;


    public List<Activity> getAll() {
        return activityRepository.findAll();
    }

    public Activity getById(Long id) {
        return activityRepository.findById(id)
                .orElseThrow(ActivityNotFoundException::new);
    }

    public List<Activity> getByCourseId(Long courseId) {
        return activityRepository.findBySubjectRealisationId(courseId);
    }

    public Activity create(Long courseId, CreateActivityDTO dto) {
        Activity activity = new Activity();
        activity.setName(dto.getName());
        activity.setWeight(dto.getWeight());
        activity.setDescription(dto.getDescription());

        SubjectRealisation subjectRealisation = subjectRealisationRepository.findById(courseId)
                .orElseThrow(SubjectRealisationNotFound::new);

        User teacher = userRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        activity.setSubjectRealisation(subjectRealisation);
        activity.setTeacher(teacher);
        return activityRepository.save(activity);
    }

    public Activity update(Long id, UpdateActivityDTO dto) {
        Activity activity = getById(id);
        activity.setName(dto.getName() == null ? activity.getName() : dto.getName());
        activity.setDescription(dto.getDescription() == null ? activity.getDescription() : dto.getDescription());
        activity.setWeight(dto.getWeight() == null ? activity.getWeight() : dto.getWeight());
        return activityRepository.save(activity);
    }

    public void deleteById(Long id) {
        try {
            Activity activity = getById(id);
            activityRepository.delete(activity);
        } catch (PostNotFoundException ignored) {
        }
    }
}
