package pl.rstrzalkowski.syllabus.domain.service.activity;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.rstrzalkowski.syllabus.application.dto.ActivityDTO;
import pl.rstrzalkowski.syllabus.domain.exception.activity.ActivityNotFoundException;
import pl.rstrzalkowski.syllabus.domain.model.Activity;
import pl.rstrzalkowski.syllabus.infrastructure.repository.ActivityRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ActivityQueryService {

    private final ActivityRepository activityRepository;

    public Page<ActivityDTO> getAllActiveByRealisation(Long realisationId, Pageable pageable) {
        Page<Activity> activities = activityRepository.findByRealisationIdAndArchived(realisationId, false, pageable);
        return activities.map((ActivityDTO::new));
    }

    public Page<ActivityDTO> getAllIncomingByRealisation(Long realisationId, Pageable pageable) {
        Page<Activity> activities = activityRepository.findByRealisationIdAndArchivedAndDateAfter(realisationId, false, LocalDateTime.now(), pageable);
        return activities.map((ActivityDTO::new));
    }


    public Page<Activity> getAllArchivedByRealisation(Long realisationId, Pageable pageable) {
        return activityRepository.findByRealisationIdAndArchived(realisationId, true, pageable);
    }


    public Activity getById(Long id) {
        return activityRepository.findById(id)
                .orElseThrow(ActivityNotFoundException::new);
    }
}
