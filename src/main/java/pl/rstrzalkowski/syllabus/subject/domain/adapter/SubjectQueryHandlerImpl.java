package pl.rstrzalkowski.syllabus.subject.domain.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.subject.application.SubjectQueryHandler;
import pl.rstrzalkowski.syllabus.subject.application.query.GetActiveSubjectsQuery;
import pl.rstrzalkowski.syllabus.subject.application.query.GetArchivedSubjectsQuery;
import pl.rstrzalkowski.syllabus.subject.application.query.GetSubjectByIdQuery;
import pl.rstrzalkowski.syllabus.subject.application.query.SearchSubjectByNameQuery;
import pl.rstrzalkowski.syllabus.subject.domain.model.Subject;
import pl.rstrzalkowski.syllabus.subject.domain.service.SubjectQueryService;

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
