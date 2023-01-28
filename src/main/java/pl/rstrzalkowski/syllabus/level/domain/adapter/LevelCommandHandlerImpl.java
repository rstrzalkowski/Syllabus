package pl.rstrzalkowski.syllabus.level.domain.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.level.application.LevelCommandHandler;
import pl.rstrzalkowski.syllabus.level.application.command.ArchiveLevelCommand;
import pl.rstrzalkowski.syllabus.level.application.command.CreateLevelCommand;
import pl.rstrzalkowski.syllabus.level.application.command.UpdateLevelCommand;
import pl.rstrzalkowski.syllabus.level.domain.service.LevelCommandService;

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
