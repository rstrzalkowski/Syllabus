package pl.rstrzalkowski.syllabus.schoolclass.infrastructure;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.schoolclass.application.SchoolClassCommandHandler;
import pl.rstrzalkowski.syllabus.schoolclass.application.command.ArchiveSchoolClassCommand;
import pl.rstrzalkowski.syllabus.schoolclass.application.command.CreateSchoolClassCommand;
import pl.rstrzalkowski.syllabus.schoolclass.application.command.UpdateSchoolClassCommand;

@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
public class SchoolClassCommandController {

    private final SchoolClassCommandHandler schoolClassCommandHandler;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createSchoolClass(@Valid @RequestBody CreateSchoolClassCommand command) {
        schoolClassCommandHandler.handle(command);
    }

    @PutMapping
    public void updateSchoolClass(@Valid @RequestBody UpdateSchoolClassCommand command) {
        schoolClassCommandHandler.handle(command);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void archiveById(@PathVariable("id") Long id) {
        schoolClassCommandHandler.handle(new ArchiveSchoolClassCommand(id));
    }
}
