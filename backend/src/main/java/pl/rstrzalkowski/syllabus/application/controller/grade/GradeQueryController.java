package pl.rstrzalkowski.syllabus.application.controller.grade;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.application.handler.grade.GradeQueryHandler;
import pl.rstrzalkowski.syllabus.application.query.grade.GetGradeByIdQuery;
import pl.rstrzalkowski.syllabus.domain.model.Grade;

@RestController
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradeQueryController {

    private final GradeQueryHandler gradeQueryHandler;

    @GetMapping("/{id}")
    @Secured({"TEACHER", "OFFICE", "DIRECTOR", "ADMIN"})
    public Grade getGradeById(@PathVariable("id") Long id) {
        return gradeQueryHandler.handle(new GetGradeByIdQuery(id));
    }
}
