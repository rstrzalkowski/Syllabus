package pl.rstrzalkowski.syllabus.subject.application.query;


import org.springframework.data.domain.Pageable;

public record SearchSubjectByNameQuery(String name, Pageable pageable) {

}
