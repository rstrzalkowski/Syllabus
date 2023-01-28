package pl.rstrzalkowski.syllabus.grade.application.query;


import org.springframework.data.domain.Pageable;

public record GetArchivedGradesByStudentQuery(Long studentId, Pageable pageable) {
}
