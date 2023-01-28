package pl.rstrzalkowski.syllabus.activity.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.rstrzalkowski.syllabus.activity.domain.exception.ActivityNotFoundException;
import pl.rstrzalkowski.syllabus.activity.domain.model.Activity;
import pl.rstrzalkowski.syllabus.activity.domain.repository.ActivityRepository;

@Service
@RequiredArgsConstructor
public class ActivityQueryService {

    private final ActivityRepository activityRepository;

    public Page<Activity> getAllActiveByRealisation(Long realisationId, Pageable pageable) {
        return activityRepository.findByRealisationIdAndArchived(realisationId, false, pageable);
    }


    public Page<Activity> getAllArchivedByRealisation(Long realisationId, Pageable pageable) {
        return activityRepository.findByRealisationIdAndArchived(realisationId, true, pageable);
    }


    public Activity getById(Long id) {
        return activityRepository.findById(id)
                .orElseThrow(ActivityNotFoundException::new);
    }
}
