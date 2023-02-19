package pl.rstrzalkowski.syllabus.application.handler.grade;

import org.springframework.data.domain.Page;
import pl.rstrzalkowski.syllabus.application.dto.GradeDTO;
import pl.rstrzalkowski.syllabus.application.query.grade.GetActiveGradesByStudentQuery;
import pl.rstrzalkowski.syllabus.application.query.grade.GetArchivedGradesByStudentQuery;
import pl.rstrzalkowski.syllabus.application.query.grade.GetGradeByActivityAndStudentQuery;
import pl.rstrzalkowski.syllabus.application.query.grade.GetGradeByIdQuery;
import pl.rstrzalkowski.syllabus.application.query.grade.GetOwnGradesByRealisationQuery;
import pl.rstrzalkowski.syllabus.application.query.grade.GetRecentGradesQuery;
import pl.rstrzalkowski.syllabus.domain.model.Grade;

public interface GradeQueryHandler {
    Page<Grade> handle(GetActiveGradesByStudentQuery query);

    Page<Grade> handle(GetArchivedGradesByStudentQuery query);

    Grade handle(GetGradeByActivityAndStudentQuery query);

    Grade handle(GetGradeByIdQuery query);

    Page<GradeDTO> handle(GetOwnGradesByRealisationQuery getOwnGradesByRealisationQuery);

    Page<GradeDTO> handle(GetRecentGradesQuery getRecentGradesQuery);
}
