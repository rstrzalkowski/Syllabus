package pl.rstrzalkowski.syllabus.subject.application.query;


import org.springframework.data.domain.Pageable;

public record GetArchivedSubjectsQuery(Pageable pageable) {
}
