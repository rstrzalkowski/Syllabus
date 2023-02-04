package pl.rstrzalkowski.syllabus.application.handler.level;

import pl.rstrzalkowski.syllabus.application.command.level.ArchiveLevelCommand;
import pl.rstrzalkowski.syllabus.application.command.level.CreateLevelCommand;
import pl.rstrzalkowski.syllabus.application.command.level.UpdateLevelCommand;

public interface LevelCommandHandler {
    void handle(CreateLevelCommand command);

    void handle(UpdateLevelCommand command);

    void handle(ArchiveLevelCommand command);
}
