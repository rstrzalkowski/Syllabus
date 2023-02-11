package pl.rstrzalkowski.syllabus.application.query.grade;


import org.springframework.data.domain.Pageable;

public record GetOwnGradesByRealisationQuery(Long realisationId, Pageable pageable) {
}
