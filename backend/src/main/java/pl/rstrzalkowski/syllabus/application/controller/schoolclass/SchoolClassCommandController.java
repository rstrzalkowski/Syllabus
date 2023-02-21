package pl.rstrzalkowski.syllabus.application.controller.schoolclass;

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
import pl.rstrzalkowski.syllabus.application.command.schoolclass.ArchiveSchoolClassCommand;
import pl.rstrzalkowski.syllabus.application.command.schoolclass.CreateSchoolClassCommand;
import pl.rstrzalkowski.syllabus.application.command.schoolclass.UpdateSchoolClassCommand;
import pl.rstrzalkowski.syllabus.application.handler.schoolclass.SchoolClassCommandHandler;

@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
public class SchoolClassCommandController {

    private final SchoolClassCommandHandler schoolClassCommandHandler;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public void createSchoolClass(@Valid @RequestBody CreateSchoolClassCommand command) {
        schoolClassCommandHandler.handle(command);
    }

    @PutMapping("/{id}")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public void updateSchoolClass(@PathVariable("id") Long id, @Valid @RequestBody UpdateSchoolClassCommand command) {
        command.setId(id);
        schoolClassCommandHandler.handle(command);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Secured({"DIRECTOR", "ADMIN"})
    public void archiveById(@PathVariable("id") Long id) {
        schoolClassCommandHandler.handle(new ArchiveSchoolClassCommand(id));
    }
}
