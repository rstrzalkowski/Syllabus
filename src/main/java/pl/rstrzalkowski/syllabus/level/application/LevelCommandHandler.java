package pl.rstrzalkowski.syllabus.level.application;

import pl.rstrzalkowski.syllabus.level.application.command.ArchiveLevelCommand;
import pl.rstrzalkowski.syllabus.level.application.command.CreateLevelCommand;
import pl.rstrzalkowski.syllabus.level.application.command.UpdateLevelCommand;

public interface LevelCommandHandler {
    void handle(CreateLevelCommand command);

    void handle(UpdateLevelCommand command);

    void handle(ArchiveLevelCommand command);
}
