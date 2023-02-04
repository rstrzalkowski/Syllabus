package pl.rstrzalkowski.syllabus.domain.service.activity;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.rstrzalkowski.syllabus.application.command.activity.ArchiveActivityCommand;
import pl.rstrzalkowski.syllabus.application.command.activity.CreateActivityCommand;
import pl.rstrzalkowski.syllabus.application.command.activity.UpdateActivityCommand;
import pl.rstrzalkowski.syllabus.domain.exception.activity.ActivityNotFoundException;
import pl.rstrzalkowski.syllabus.domain.exception.realisation.RealisationNotFoundException;
import pl.rstrzalkowski.syllabus.domain.model.Activity;
import pl.rstrzalkowski.syllabus.domain.model.Realisation;
import pl.rstrzalkowski.syllabus.domain.model.User;
import pl.rstrzalkowski.syllabus.domain.repository.ActivityRepository;
import pl.rstrzalkowski.syllabus.domain.repository.RealisationRepository;
import pl.rstrzalkowski.syllabus.domain.repository.UserRepository;

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

        Realisation realisation = realisationRepository.findById(command.getRealisationId())
                .orElseThrow(RealisationNotFoundException::new);

        User teacher = userRepository.findById(command.getTeacherId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

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

        activity.setEdited(true);
        return activityRepository.save(activity);
    }

    public void archiveById(ArchiveActivityCommand command) {
        Activity activity = activityRepository.findById(command.id())
                .orElseThrow(ActivityNotFoundException::new);

        activity.setArchived(true);
        activityRepository.save(activity);
    }
}
