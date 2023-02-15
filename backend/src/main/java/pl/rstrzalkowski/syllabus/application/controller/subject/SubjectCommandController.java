package pl.rstrzalkowski.syllabus.application.controller.subject;

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
import pl.rstrzalkowski.syllabus.application.command.subject.ArchiveSubjectCommand;
import pl.rstrzalkowski.syllabus.application.command.subject.CreateSubjectCommand;
import pl.rstrzalkowski.syllabus.application.command.subject.UpdateSubjectCommand;
import pl.rstrzalkowski.syllabus.application.handler.subject.SubjectCommandHandler;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectCommandController {

    private final SubjectCommandHandler subjectCommandHandler;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Secured({"DIRECTOR", "OFFICE", "ADMIN"})
    public void createSubject(@Valid @RequestBody CreateSubjectCommand command) {
        subjectCommandHandler.handle(command);
    }

    @PutMapping
    @Secured({"DIRECTOR", "OFFICE", "ADMIN"})
    public void updateSubject(@Valid @RequestBody UpdateSubjectCommand command) {
        subjectCommandHandler.handle(command);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Secured({"DIRECTOR", "OFFICE", "ADMIN"})
    public void archiveById(@PathVariable("id") Long id) {
        subjectCommandHandler.handle(new ArchiveSubjectCommand(id));
    }
}
