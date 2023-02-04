package pl.rstrzalkowski.syllabus.domain.adapter.realisation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.application.command.realisation.ArchiveRealisationCommand;
import pl.rstrzalkowski.syllabus.application.command.realisation.CreateRealisationCommand;
import pl.rstrzalkowski.syllabus.application.command.realisation.UpdateRealisationCommand;
import pl.rstrzalkowski.syllabus.application.handler.realisation.RealisationCommandHandler;
import pl.rstrzalkowski.syllabus.domain.service.realisation.RealisationCommandService;

@RequiredArgsConstructor
@Component
public class RealisationCommandHandlerImpl implements RealisationCommandHandler {

    private final RealisationCommandService realisationCommandService;

    @Override
    public void handle(CreateRealisationCommand command) {
        realisationCommandService.create(command);
    }

    @Override
    public void handle(UpdateRealisationCommand command) {
        realisationCommandService.update(command);
    }

    @Override
    public void handle(ArchiveRealisationCommand command) {
        realisationCommandService.archiveById(command);
    }

}
