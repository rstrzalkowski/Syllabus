package pl.rstrzalkowski.syllabus.activity.application;

import org.springframework.data.domain.Page;
import pl.rstrzalkowski.syllabus.activity.application.query.GetActiveActivitiesByRealisationQuery;
import pl.rstrzalkowski.syllabus.activity.application.query.GetActivityByIdQuery;
import pl.rstrzalkowski.syllabus.activity.application.query.GetArchivedActivitiesByRealisationQuery;
import pl.rstrzalkowski.syllabus.activity.domain.model.Activity;

public interface ActivityQueryHandler {
    Page<Activity> handle(GetActiveActivitiesByRealisationQuery query);

    Page<Activity> handle(GetArchivedActivitiesByRealisationQuery query);

    Activity handle(GetActivityByIdQuery query);
}
