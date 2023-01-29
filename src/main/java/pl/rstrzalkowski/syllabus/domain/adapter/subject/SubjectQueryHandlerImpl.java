package pl.rstrzalkowski.syllabus.domain.adapter.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.application.handler.subject.SubjectQueryHandler;
import pl.rstrzalkowski.syllabus.application.query.subject.GetActiveSubjectsQuery;
import pl.rstrzalkowski.syllabus.application.query.subject.GetArchivedSubjectsQuery;
import pl.rstrzalkowski.syllabus.application.query.subject.GetSubjectByIdQuery;
import pl.rstrzalkowski.syllabus.application.query.subject.SearchSubjectByNameQuery;
import pl.rstrzalkowski.syllabus.domain.model.Subject;
import pl.rstrzalkowski.syllabus.domain.service.subject.SubjectQueryService;

@RequiredArgsConstructor
@Component
public class SubjectQueryHandlerImpl implements SubjectQueryHandler {

    private final SubjectQueryService subjectQueryService;

    @Override
    public Page<Subject> handle(GetActiveSubjectsQuery query) {
        return subjectQueryService.getAllActive(query.pageable());
    }

    @Override
    public Page<Subject> handle(GetArchivedSubjectsQuery query) {
        return subjectQueryService.getAllArchived(query.pageable());
    }

    @Override
    public Page<Subject> handle(SearchSubjectByNameQuery query) {
        return subjectQueryService.getByNameContaining(query.name(), query.pageable());
    }

    @Override
    public Subject handle(GetSubjectByIdQuery query) {
        return subjectQueryService.getById(query.id());
    }
}
