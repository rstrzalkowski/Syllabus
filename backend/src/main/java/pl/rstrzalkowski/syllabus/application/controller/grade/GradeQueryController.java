package pl.rstrzalkowski.syllabus.application.controller.grade;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.application.dto.GradeDTO;
import pl.rstrzalkowski.syllabus.application.handler.grade.GradeQueryHandler;
import pl.rstrzalkowski.syllabus.application.query.grade.GetGradeByIdQuery;
import pl.rstrzalkowski.syllabus.application.query.grade.GetRecentGradesQuery;
import pl.rstrzalkowski.syllabus.domain.model.Grade;
import pl.rstrzalkowski.syllabus.domain.shared.AccessGuard;

@RestController
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradeQueryController {

    private final GradeQueryHandler gradeQueryHandler;
    private final AccessGuard accessGuard;

    @GetMapping("/{id}")
    @Secured({"TEACHER", "OFFICE", "DIRECTOR", "ADMIN"})
    public Grade getGradeById(@PathVariable("id") Long id) {
        accessGuard.checkAccessToGrade(id);
        return gradeQueryHandler.handle(new GetGradeByIdQuery(id));
    }

    @GetMapping("/own")
    @Secured({"STUDENT"})
    public Page<GradeDTO> getRecentGrades(Pageable pageable) {
        return gradeQueryHandler.handle(new GetRecentGradesQuery(pageable));
    }
}
