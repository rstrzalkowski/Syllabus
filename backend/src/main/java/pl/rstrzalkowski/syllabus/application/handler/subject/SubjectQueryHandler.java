package pl.rstrzalkowski.syllabus.application.handler.subject;

import org.springframework.data.domain.Page;
import pl.rstrzalkowski.syllabus.application.query.subject.GetActiveSubjectsQuery;
import pl.rstrzalkowski.syllabus.application.query.subject.GetArchivedSubjectsQuery;
import pl.rstrzalkowski.syllabus.application.query.subject.GetSubjectByIdQuery;
import pl.rstrzalkowski.syllabus.application.query.subject.SearchSubjectByNameQuery;
import pl.rstrzalkowski.syllabus.domain.model.Subject;

public interface SubjectQueryHandler {
    Page<Subject> handle(GetActiveSubjectsQuery query);

    Page<Subject> handle(GetArchivedSubjectsQuery query);

    Page<Subject> handle(SearchSubjectByNameQuery query);

    Subject handle(GetSubjectByIdQuery query);

}
