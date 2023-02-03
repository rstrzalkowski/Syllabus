package pl.rstrzalkowski.syllabus.infrastructure.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.application.handler.subject.SubjectQueryHandler;
import pl.rstrzalkowski.syllabus.application.query.subject.GetActiveSubjectsQuery;
import pl.rstrzalkowski.syllabus.application.query.subject.GetArchivedSubjectsQuery;
import pl.rstrzalkowski.syllabus.application.query.subject.GetSubjectByIdQuery;
import pl.rstrzalkowski.syllabus.application.query.subject.SearchSubjectByNameQuery;
import pl.rstrzalkowski.syllabus.domain.model.Subject;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectQueryController {

    private final SubjectQueryHandler subjectQueryHandler;

    @GetMapping("/{id}")
    public Subject getSubjectById(@PathVariable("id") Long id) {
        return subjectQueryHandler.handle(new GetSubjectByIdQuery(id));
    }

    @GetMapping("/search")
    public Page<Subject> getSubjectByName(@Param("name") String name, Pageable pageable) {
        return subjectQueryHandler.handle(new SearchSubjectByNameQuery(name, pageable));
    }

    @GetMapping
    public Page<Subject> getActiveSubjects(Pageable pageable) {
        return subjectQueryHandler.handle(new GetActiveSubjectsQuery(pageable));
    }

    @GetMapping("/archived")
    @Secured({"DIRECTOR", "OFFICE"})
    public Page<Subject> getArchivedSubjects(Pageable pageable) {
        return subjectQueryHandler.handle(new GetArchivedSubjectsQuery(pageable));
    }
}
