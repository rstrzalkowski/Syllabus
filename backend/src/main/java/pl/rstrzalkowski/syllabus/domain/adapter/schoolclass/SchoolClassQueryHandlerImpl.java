package pl.rstrzalkowski.syllabus.domain.adapter.schoolclass;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.application.handler.schoolclass.SchoolClassQueryHandler;
import pl.rstrzalkowski.syllabus.application.query.schoolclass.GetActiveSchoolClassesQuery;
import pl.rstrzalkowski.syllabus.application.query.schoolclass.GetArchivedSchoolClassesQuery;
import pl.rstrzalkowski.syllabus.application.query.schoolclass.GetSchoolClassByIdQuery;
import pl.rstrzalkowski.syllabus.domain.model.SchoolClass;
import pl.rstrzalkowski.syllabus.domain.service.schoolclass.SchoolClassQueryService;

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
