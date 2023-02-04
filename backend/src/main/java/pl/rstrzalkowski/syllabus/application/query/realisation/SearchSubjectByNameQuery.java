package pl.rstrzalkowski.syllabus.application.query.realisation;


import org.springframework.data.domain.Pageable;

public record SearchSubjectByNameQuery(String name, Pageable pageable) {

}
