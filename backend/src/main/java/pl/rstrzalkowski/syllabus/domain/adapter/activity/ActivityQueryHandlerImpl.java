package pl.rstrzalkowski.syllabus.domain.adapter.activity;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.application.dto.ActivityDTO;
import pl.rstrzalkowski.syllabus.application.handler.activity.ActivityQueryHandler;
import pl.rstrzalkowski.syllabus.application.query.activity.GetActiveActivitiesByRealisationQuery;
import pl.rstrzalkowski.syllabus.application.query.activity.GetActivityByIdQuery;
import pl.rstrzalkowski.syllabus.application.query.activity.GetArchivedActivitiesByRealisationQuery;
import pl.rstrzalkowski.syllabus.domain.model.Activity;
import pl.rstrzalkowski.syllabus.domain.service.activity.ActivityQueryService;

@RequiredArgsConstructor
@Component
public class ActivityQueryHandlerImpl implements ActivityQueryHandler {

    private final ActivityQueryService activityQueryService;

    @Override
    public Page<ActivityDTO> handle(GetActiveActivitiesByRealisationQuery query) {
        return activityQueryService.getAllActiveByRealisation(query.realisationId(), query.pageable());
    }

    @Override
    public Page<Activity> handle(GetArchivedActivitiesByRealisationQuery query) {
        return activityQueryService.getAllArchivedByRealisation(query.realisationId(), query.pageable());
    }

    @Override
    public Activity handle(GetActivityByIdQuery query) {
        return activityQueryService.getById(query.id());
    }
}
