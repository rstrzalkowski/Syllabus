package pl.rstrzalkowski.syllabus.subject.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.subject.application.SubjectQueryHandler;
import pl.rstrzalkowski.syllabus.subject.application.query.GetActiveSubjectsQuery;
import pl.rstrzalkowski.syllabus.subject.application.query.GetArchivedSubjectsQuery;
import pl.rstrzalkowski.syllabus.subject.application.query.GetSubjectByIdQuery;
import pl.rstrzalkowski.syllabus.subject.application.query.SearchSubjectByNameQuery;
import pl.rstrzalkowski.syllabus.subject.domain.model.Subject;

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
    public Page<Subject> getSubjectById(@Param("name") String name, Pageable pageable) {
        return subjectQueryHandler.handle(new SearchSubjectByNameQuery(name, pageable));
    }

    @GetMapping
    public Page<Subject> getActiveSubjects(Pageable pageable) {
        return subjectQueryHandler.handle(new GetActiveSubjectsQuery(pageable));
    }

    @GetMapping("/archived")
    public Page<Subject> getArchivedSubjects(Pageable pageable) {
        return subjectQueryHandler.handle(new GetArchivedSubjectsQuery(pageable));
    }
}
