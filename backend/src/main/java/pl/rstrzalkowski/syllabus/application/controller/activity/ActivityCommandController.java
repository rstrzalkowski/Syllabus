package pl.rstrzalkowski.syllabus.application.controller.activity;

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
import pl.rstrzalkowski.syllabus.application.command.activity.ArchiveActivityCommand;
import pl.rstrzalkowski.syllabus.application.command.activity.CreateActivityCommand;
import pl.rstrzalkowski.syllabus.application.command.activity.UpdateActivityCommand;
import pl.rstrzalkowski.syllabus.application.handler.activity.ActivityCommandHandler;
import pl.rstrzalkowski.syllabus.domain.shared.AccessGuard;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
public class ActivityCommandController {

    private final ActivityCommandHandler activityCommandHandler;
    private final AccessGuard accessGuard;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Secured({"TEACHER", "OFFICE", "DIRECTOR", "ADMIN"})
    public void createActivity(@Valid @RequestBody CreateActivityCommand command) {
        accessGuard.checkAccessToRealisation(command.getRealisationId());
        activityCommandHandler.handle(command);
    }

    @PutMapping("/{id}")
    @Secured({"TEACHER", "OFFICE", "DIRECTOR", "ADMIN"})
    public void updateActivity(@PathVariable("id") Long id, @Valid @RequestBody UpdateActivityCommand command) {
        accessGuard.checkAccessToRealisation(id);
        command.setId(id);
        activityCommandHandler.handle(command);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Secured({"TEACHER", "DIRECTOR", "ADMIN"})
    public void archiveById(@PathVariable("id") Long id) {
        activityCommandHandler.handle(new ArchiveActivityCommand(id));
    }
}
