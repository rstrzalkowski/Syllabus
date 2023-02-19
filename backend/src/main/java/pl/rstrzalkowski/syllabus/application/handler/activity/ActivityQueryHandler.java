package pl.rstrzalkowski.syllabus.application.handler.activity;

import org.springframework.data.domain.Page;
import pl.rstrzalkowski.syllabus.application.dto.ActivityDTO;
import pl.rstrzalkowski.syllabus.application.dto.GradeOfActivityDTO;
import pl.rstrzalkowski.syllabus.application.query.activity.GetActiveActivitiesByRealisationQuery;
import pl.rstrzalkowski.syllabus.application.query.activity.GetActivityByIdQuery;
import pl.rstrzalkowski.syllabus.application.query.activity.GetArchivedActivitiesByRealisationQuery;
import pl.rstrzalkowski.syllabus.application.query.activity.GetIncomingActivitiesByRealisationQuery;
import pl.rstrzalkowski.syllabus.application.query.activity.GetIncomingActivitiesQuery;
import pl.rstrzalkowski.syllabus.application.query.grade.GetGradesOfActivityQuery;
import pl.rstrzalkowski.syllabus.domain.model.Activity;

import java.util.List;

public interface ActivityQueryHandler {
    Page<ActivityDTO> handle(GetActiveActivitiesByRealisationQuery query);

    Page<Activity> handle(GetArchivedActivitiesByRealisationQuery query);

    Activity handle(GetActivityByIdQuery query);

    Page<ActivityDTO> handle(GetIncomingActivitiesByRealisationQuery getIncomingActivitiesByRealisationQuery);

    List<GradeOfActivityDTO> handle(GetGradesOfActivityQuery getGradesOfActivityQuery);

    Page<ActivityDTO> handle(GetIncomingActivitiesQuery getIncomingActivitiesQuery);
}
