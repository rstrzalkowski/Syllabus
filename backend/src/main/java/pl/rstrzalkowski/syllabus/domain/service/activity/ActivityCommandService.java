package pl.rstrzalkowski.syllabus.domain.service.activity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rstrzalkowski.syllabus.application.command.activity.ArchiveActivityCommand;
import pl.rstrzalkowski.syllabus.application.command.activity.CreateActivityCommand;
import pl.rstrzalkowski.syllabus.application.command.activity.UpdateActivityCommand;
import pl.rstrzalkowski.syllabus.domain.exception.activity.ActivityNotFoundException;
import pl.rstrzalkowski.syllabus.domain.exception.activity.NotAffiliatedWithActivityException;
import pl.rstrzalkowski.syllabus.domain.exception.realisation.RealisationNotFoundException;
import pl.rstrzalkowski.syllabus.domain.model.Activity;
import pl.rstrzalkowski.syllabus.domain.model.Realisation;
import pl.rstrzalkowski.syllabus.domain.model.Role;
import pl.rstrzalkowski.syllabus.domain.model.User;
import pl.rstrzalkowski.syllabus.infrastructure.repository.ActivityRepository;
import pl.rstrzalkowski.syllabus.infrastructure.repository.RealisationRepository;
import pl.rstrzalkowski.syllabus.infrastructure.repository.UserRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ActivityCommandService {

    private final ActivityRepository activityRepository;
    private final RealisationRepository realisationRepository;
    private final UserRepository userRepository;


    public Activity create(CreateActivityCommand command) {
        Activity activity = new Activity();
        activity.setName(command.getName());
        activity.setWeight(command.getWeight());
        activity.setDescription(command.getDescription());
        activity.setDate(command.getDate());

        Realisation realisation = realisationRepository.findById(command.getRealisationId())
                .orElseThrow(RealisationNotFoundException::new);

        User teacher = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        activity.setRealisation(realisation);
        activity.setTeacher(teacher);
        return activityRepository.save(activity);
    }

    public Activity update(UpdateActivityCommand command) {
        Activity activity = activityRepository.findById(command.getId())
                .orElseThrow(ActivityNotFoundException::new);

        activity.setName(command.getName() == null ? activity.getName() : command.getName());
        activity.setDescription(command.getDescription() == null ? activity.getDescription() : command.getDescription());
        activity.setWeight(command.getWeight() == null ? activity.getWeight() : command.getWeight());
        activity.setDate(command.getDate() == null ? activity.getDate() : command.getDate());

        activity.setEdited(true);
        return activityRepository.save(activity);
    }

    public void archiveById(ArchiveActivityCommand command) {
        Activity activity = activityRepository.findById(command.id())
                .orElseThrow(ActivityNotFoundException::new);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getRole() == Role.TEACHER && !Objects.equals(activity.getTeacher().getId(), user.getId())) {
            throw new NotAffiliatedWithActivityException();
        }

        activity.setArchived(true);
        activityRepository.save(activity);
    }
}
