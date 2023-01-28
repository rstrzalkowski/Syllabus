package pl.rstrzalkowski.syllabus.activity.domain.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.activity.application.ActivityCommandHandler;
import pl.rstrzalkowski.syllabus.activity.application.command.ArchiveActivityCommand;
import pl.rstrzalkowski.syllabus.activity.application.command.CreateActivityCommand;
import pl.rstrzalkowski.syllabus.activity.application.command.UpdateActivityCommand;
import pl.rstrzalkowski.syllabus.activity.domain.service.ActivityCommandService;

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
