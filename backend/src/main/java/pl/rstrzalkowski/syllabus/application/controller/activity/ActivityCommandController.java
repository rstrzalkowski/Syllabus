package pl.rstrzalkowski.syllabus.application.controller.activity;

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
import pl.rstrzalkowski.syllabus.application.command.activity.ArchiveActivityCommand;
import pl.rstrzalkowski.syllabus.application.command.activity.CreateActivityCommand;
import pl.rstrzalkowski.syllabus.application.command.activity.UpdateActivityCommand;
import pl.rstrzalkowski.syllabus.application.handler.activity.ActivityCommandHandler;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
public class ActivityCommandController {

    private final ActivityCommandHandler activityCommandHandler;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createActivity(@Valid @RequestBody CreateActivityCommand command) {
        activityCommandHandler.handle(command);
    }

    @PutMapping
    public void updateActivity(@Valid @RequestBody UpdateActivityCommand command) {
        activityCommandHandler.handle(command);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void archiveById(@PathVariable("id") Long id) {
        activityCommandHandler.handle(new ArchiveActivityCommand(id));
    }
}
