package pl.rstrzalkowski.syllabus.subject.application;

import org.springframework.data.domain.Page;
import pl.rstrzalkowski.syllabus.subject.application.query.GetActiveSubjectsQuery;
import pl.rstrzalkowski.syllabus.subject.application.query.GetArchivedSubjectsQuery;
import pl.rstrzalkowski.syllabus.subject.application.query.GetSubjectByIdQuery;
import pl.rstrzalkowski.syllabus.subject.application.query.SearchSubjectByNameQuery;
import pl.rstrzalkowski.syllabus.subject.domain.model.Subject;

public interface SubjectQueryHandler {
    Page<Subject> handle(GetActiveSubjectsQuery query);

    Page<Subject> handle(GetArchivedSubjectsQuery query);

    Page<Subject> handle(SearchSubjectByNameQuery query);

    Subject handle(GetSubjectByIdQuery query);

}
