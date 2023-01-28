package pl.rstrzalkowski.syllabus.activity.application;

import pl.rstrzalkowski.syllabus.activity.application.command.ArchiveActivityCommand;
import pl.rstrzalkowski.syllabus.activity.application.command.CreateActivityCommand;
import pl.rstrzalkowski.syllabus.activity.application.command.UpdateActivityCommand;

public interface ActivityCommandHandler {
    void handle(CreateActivityCommand command);

    void handle(UpdateActivityCommand command);

    void handle(ArchiveActivityCommand command);
}
