package pl.rstrzalkowski.syllabus.domain.adapter.level;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.application.command.level.ArchiveLevelCommand;
import pl.rstrzalkowski.syllabus.application.command.level.CreateLevelCommand;
import pl.rstrzalkowski.syllabus.application.command.level.UpdateLevelCommand;
import pl.rstrzalkowski.syllabus.application.handler.level.LevelCommandHandler;
import pl.rstrzalkowski.syllabus.domain.service.level.LevelCommandService;

@RequiredArgsConstructor
@Component
public class LevelCommandHandlerImpl implements LevelCommandHandler {

    private final LevelCommandService levelCommandService;

    @Override
    public void handle(CreateLevelCommand command) {
        levelCommandService.create(command);
    }

    @Override
    public void handle(UpdateLevelCommand command) {
        levelCommandService.update(command);
    }

    @Override
    public void handle(ArchiveLevelCommand command) {
        levelCommandService.archiveById(command);
    }

}
