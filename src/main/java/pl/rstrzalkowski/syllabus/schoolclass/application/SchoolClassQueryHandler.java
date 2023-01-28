package pl.rstrzalkowski.syllabus.schoolclass.application;

import org.springframework.data.domain.Page;
import pl.rstrzalkowski.syllabus.schoolclass.application.query.GetActiveSchoolClassesQuery;
import pl.rstrzalkowski.syllabus.schoolclass.application.query.GetArchivedSchoolClassesQuery;
import pl.rstrzalkowski.syllabus.schoolclass.application.query.GetSchoolClassByIdQuery;
import pl.rstrzalkowski.syllabus.schoolclass.domain.model.SchoolClass;

public interface SchoolClassQueryHandler {
    Page<SchoolClass> handle(GetActiveSchoolClassesQuery query);

    Page<SchoolClass> handle(GetArchivedSchoolClassesQuery query);

    SchoolClass handle(GetSchoolClassByIdQuery query);
}
