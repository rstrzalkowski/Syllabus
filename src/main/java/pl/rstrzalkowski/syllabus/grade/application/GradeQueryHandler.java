package pl.rstrzalkowski.syllabus.grade.application;

import org.springframework.data.domain.Page;
import pl.rstrzalkowski.syllabus.grade.application.query.GetActiveGradesByStudentQuery;
import pl.rstrzalkowski.syllabus.grade.application.query.GetArchivedGradesByStudentQuery;
import pl.rstrzalkowski.syllabus.grade.application.query.GetGradeByActivityAndStudentQuery;
import pl.rstrzalkowski.syllabus.grade.application.query.GetGradeByIdQuery;
import pl.rstrzalkowski.syllabus.grade.domain.model.Grade;

public interface GradeQueryHandler {
    Page<Grade> handle(GetActiveGradesByStudentQuery query);

    Page<Grade> handle(GetArchivedGradesByStudentQuery query);

    Grade handle(GetGradeByActivityAndStudentQuery query);

    Grade handle(GetGradeByIdQuery query);
}
