package pl.rstrzalkowski.syllabus.grade.application.query;


import org.springframework.data.domain.Pageable;

public record GetActiveGradesByStudentQuery(Long studentId, Pageable pageable) {
}
