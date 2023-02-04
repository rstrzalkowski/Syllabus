package pl.rstrzalkowski.syllabus.application.handler.activity;

import pl.rstrzalkowski.syllabus.application.command.activity.ArchiveActivityCommand;
import pl.rstrzalkowski.syllabus.application.command.activity.CreateActivityCommand;
import pl.rstrzalkowski.syllabus.application.command.activity.UpdateActivityCommand;

public interface ActivityCommandHandler {
    void handle(CreateActivityCommand command);

    void handle(UpdateActivityCommand command);

    void handle(ArchiveActivityCommand command);
}
