package pl.rstrzalkowski.syllabus.domain.adapter.activity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.application.command.activity.ArchiveActivityCommand;
import pl.rstrzalkowski.syllabus.application.command.activity.CreateActivityCommand;
import pl.rstrzalkowski.syllabus.application.command.activity.UpdateActivityCommand;
import pl.rstrzalkowski.syllabus.application.handler.activity.ActivityCommandHandler;
import pl.rstrzalkowski.syllabus.domain.service.activity.ActivityCommandService;

@RequiredArgsConstructor
@Component
public class ActivityCommandHandlerImpl implements ActivityCommandHandler {

    private final ActivityCommandService activityCommandService;

    @Override
    public void handle(CreateActivityCommand command) {
        activityCommandService.create(command);
    }

    @Override
    public void handle(UpdateActivityCommand command) {
        activityCommandService.update(command);
    }

    @Override
    public void handle(ArchiveActivityCommand command) {
        activityCommandService.archiveById(command);
    }

}
