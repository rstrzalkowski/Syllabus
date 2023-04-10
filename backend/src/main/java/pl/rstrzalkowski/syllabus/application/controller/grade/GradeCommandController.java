package pl.rstrzalkowski.syllabus.application.controller.grade;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.application.command.grade.ArchiveGradeCommand;
import pl.rstrzalkowski.syllabus.application.command.grade.CreateGradeCommand;
import pl.rstrzalkowski.syllabus.application.command.grade.UpdateGradeCommand;
import pl.rstrzalkowski.syllabus.application.handler.grade.GradeCommandHandler;
import pl.rstrzalkowski.syllabus.domain.shared.AccessGuard;

@RestController
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradeCommandController {

    private final GradeCommandHandler gradeCommandHandler;
    private final AccessGuard accessGuard;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Secured({"TEACHER", "OFFICE", "DIRECTOR", "ADMIN"})
    public void createGrade(@Valid @RequestBody CreateGradeCommand command) {
        accessGuard.checkAccessToActivity(command.getActivityId());
        gradeCommandHandler.handle(command);
    }

    @PutMapping("/{id}")
    @Secured({"TEACHER", "OFFICE", "DIRECTOR", "ADMIN"})
    public void updateGrade(@PathVariable("id") Long id, @Valid @RequestBody UpdateGradeCommand command) {
        command.setGradeId(id);
        gradeCommandHandler.handle(command);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Secured({"TEACHER", "OFFICE", "DIRECTOR", "ADMIN"})
    public void archiveById(@PathVariable("id") Long id) {
        gradeCommandHandler.handle(new ArchiveGradeCommand(id));
    }
}
