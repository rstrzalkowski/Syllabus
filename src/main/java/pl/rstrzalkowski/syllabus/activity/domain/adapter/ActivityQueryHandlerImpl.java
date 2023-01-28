package pl.rstrzalkowski.syllabus.activity.domain.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.activity.application.ActivityQueryHandler;
import pl.rstrzalkowski.syllabus.activity.application.query.GetActiveActivitiesByRealisationQuery;
import pl.rstrzalkowski.syllabus.activity.application.query.GetActivityByIdQuery;
import pl.rstrzalkowski.syllabus.activity.application.query.GetArchivedActivitiesByRealisationQuery;
import pl.rstrzalkowski.syllabus.activity.domain.model.Activity;
import pl.rstrzalkowski.syllabus.activity.domain.service.ActivityQueryService;

@RequiredArgsConstructor
@Component
public class ActivityQueryHandlerImpl implements ActivityQueryHandler {

    private final ActivityQueryService activityQueryService;

    @Override
    public Page<Activity> handle(GetActiveActivitiesByRealisationQuery query) {
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
