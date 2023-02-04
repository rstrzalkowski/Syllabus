package pl.rstrzalkowski.syllabus.application.query.schoolclass;


import org.springframework.data.domain.Pageable;

public record GetActiveSchoolClassesQuery(Pageable pageable) {
}
