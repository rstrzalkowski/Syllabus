package pl.rstrzalkowski.syllabus.schoolclass.domain.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.schoolclass.application.SchoolClassQueryHandler;
import pl.rstrzalkowski.syllabus.schoolclass.application.query.GetActiveSchoolClassesQuery;
import pl.rstrzalkowski.syllabus.schoolclass.application.query.GetArchivedSchoolClassesQuery;
import pl.rstrzalkowski.syllabus.schoolclass.application.query.GetSchoolClassByIdQuery;
import pl.rstrzalkowski.syllabus.schoolclass.domain.model.SchoolClass;
import pl.rstrzalkowski.syllabus.schoolclass.domain.service.SchoolClassQueryService;

@RequiredArgsConstructor
@Component
public class SchoolClassQueryHandlerImpl implements SchoolClassQueryHandler {

    private final SchoolClassQueryService schoolClassQueryService;

    @Override
    public Page<SchoolClass> handle(GetActiveSchoolClassesQuery query) {
        return schoolClassQueryService.getAllActive(query.pageable());
    }

    @Override
    public Page<SchoolClass> handle(GetArchivedSchoolClassesQuery query) {
        return schoolClassQueryService.getAllArchived(query.pageable());
    }

    @Override
    public SchoolClass handle(GetSchoolClassByIdQuery query) {
        return schoolClassQueryService.getById(query.id());
    }
}
