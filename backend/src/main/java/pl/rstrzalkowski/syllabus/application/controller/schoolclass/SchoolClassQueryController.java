package pl.rstrzalkowski.syllabus.application.controller.schoolclass;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.application.handler.schoolclass.SchoolClassQueryHandler;
import pl.rstrzalkowski.syllabus.application.query.schoolclass.GetActiveSchoolClassesQuery;
import pl.rstrzalkowski.syllabus.application.query.schoolclass.GetArchivedSchoolClassesQuery;
import pl.rstrzalkowski.syllabus.application.query.schoolclass.GetSchoolClassByIdQuery;
import pl.rstrzalkowski.syllabus.domain.model.SchoolClass;

@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
public class SchoolClassQueryController {

    private final SchoolClassQueryHandler schoolClassQueryHandler;

    @GetMapping("/{id}")
    public SchoolClass getSchoolClassById(@PathVariable("id") Long id) {
        return schoolClassQueryHandler.handle(new GetSchoolClassByIdQuery(id));
    }

    @GetMapping
    public Page<SchoolClass> getActiveSchoolClasses(Pageable pageable) {
        return schoolClassQueryHandler.handle(new GetActiveSchoolClassesQuery(pageable));
    }

    @GetMapping("/archived")
    public Page<SchoolClass> getArchivedSchoolClasses(Pageable pageable) {
        return schoolClassQueryHandler.handle(new GetArchivedSchoolClassesQuery(pageable));
    }
}
