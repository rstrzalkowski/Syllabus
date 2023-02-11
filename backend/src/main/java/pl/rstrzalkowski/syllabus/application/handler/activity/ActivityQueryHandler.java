package pl.rstrzalkowski.syllabus.application.handler.activity;

import org.springframework.data.domain.Page;
import pl.rstrzalkowski.syllabus.application.dto.ActivityDTO;
import pl.rstrzalkowski.syllabus.application.query.activity.GetActiveActivitiesByRealisationQuery;
import pl.rstrzalkowski.syllabus.application.query.activity.GetActivityByIdQuery;
import pl.rstrzalkowski.syllabus.application.query.activity.GetArchivedActivitiesByRealisationQuery;
import pl.rstrzalkowski.syllabus.domain.model.Activity;

public interface ActivityQueryHandler {
    Page<ActivityDTO> handle(GetActiveActivitiesByRealisationQuery query);

    Page<Activity> handle(GetArchivedActivitiesByRealisationQuery query);

    Activity handle(GetActivityByIdQuery query);
}
