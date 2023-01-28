package pl.rstrzalkowski.syllabus.grade.domain.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.grade.application.GradeQueryHandler;
import pl.rstrzalkowski.syllabus.grade.application.query.GetActiveGradesByStudentQuery;
import pl.rstrzalkowski.syllabus.grade.application.query.GetArchivedGradesByStudentQuery;
import pl.rstrzalkowski.syllabus.grade.application.query.GetGradeByActivityAndStudentQuery;
import pl.rstrzalkowski.syllabus.grade.application.query.GetGradeByIdQuery;
import pl.rstrzalkowski.syllabus.grade.domain.model.Grade;
import pl.rstrzalkowski.syllabus.grade.domain.service.GradeQueryService;

@RequiredArgsConstructor
@Component
public class GradeQueryHandlerImpl implements GradeQueryHandler {

    private final GradeQueryService gradeQueryService;

    @Override
    public Page<Grade> handle(GetActiveGradesByStudentQuery query) {
        return gradeQueryService.getAllActiveByStudent(query.studentId(), query.pageable());
    }

    @Override
    public Page<Grade> handle(GetArchivedGradesByStudentQuery query) {
        return gradeQueryService.getAllArchivedByStudent(query.studentId(), query.pageable());
    }

    @Override
    public Grade handle(GetGradeByActivityAndStudentQuery query) {
        return gradeQueryService.getByActivityAndStudent(query.activityId(), query.studentId());
    }

    @Override
    public Grade handle(GetGradeByIdQuery query) {
        return gradeQueryService.getById(query.id());
    }
}
