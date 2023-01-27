package pl.rstrzalkowski.syllabus.realisation.domain.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.realisation.application.RealisationCommandHandler;
import pl.rstrzalkowski.syllabus.realisation.application.command.ArchiveRealisationCommand;
import pl.rstrzalkowski.syllabus.realisation.application.command.CreateRealisationCommand;
import pl.rstrzalkowski.syllabus.realisation.application.command.UpdateRealisationCommand;
import pl.rstrzalkowski.syllabus.realisation.domain.service.RealisationCommandService;

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
