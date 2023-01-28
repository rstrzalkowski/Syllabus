package pl.rstrzalkowski.syllabus.schoolclass.application.query;


import org.springframework.data.domain.Pageable;

public record GetArchivedSchoolClassesQuery(Pageable pageable) {
}
