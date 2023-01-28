package pl.rstrzalkowski.syllabus.grade.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.grade.application.GradeQueryHandler;
import pl.rstrzalkowski.syllabus.grade.application.query.GetGradeByIdQuery;
import pl.rstrzalkowski.syllabus.grade.domain.model.Grade;

@RestController
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradeQueryController {

    private final GradeQueryHandler gradeQueryHandler;

    @GetMapping("/{id}")
    public Grade getGradeById(@PathVariable("id") Long id) {
        return gradeQueryHandler.handle(new GetGradeByIdQuery(id));
    }
}
