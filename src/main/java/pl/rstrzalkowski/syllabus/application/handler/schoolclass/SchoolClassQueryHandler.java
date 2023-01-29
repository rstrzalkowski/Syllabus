package pl.rstrzalkowski.syllabus.application.handler.schoolclass;

import org.springframework.data.domain.Page;
import pl.rstrzalkowski.syllabus.application.query.schoolclass.GetActiveSchoolClassesQuery;
import pl.rstrzalkowski.syllabus.application.query.schoolclass.GetArchivedSchoolClassesQuery;
import pl.rstrzalkowski.syllabus.application.query.schoolclass.GetSchoolClassByIdQuery;
import pl.rstrzalkowski.syllabus.domain.model.SchoolClass;

public interface SchoolClassQueryHandler {
    Page<SchoolClass> handle(GetActiveSchoolClassesQuery query);

    Page<SchoolClass> handle(GetArchivedSchoolClassesQuery query);

    SchoolClass handle(GetSchoolClassByIdQuery query);
}
