package pl.rstrzalkowski.syllabus.application.handler.realisation;

import pl.rstrzalkowski.syllabus.application.command.realisation.ArchiveRealisationCommand;
import pl.rstrzalkowski.syllabus.application.command.realisation.CreateRealisationCommand;
import pl.rstrzalkowski.syllabus.application.command.realisation.UpdateRealisationCommand;

public interface RealisationCommandHandler {
    void handle(CreateRealisationCommand command);

    void handle(UpdateRealisationCommand command);

    void handle(ArchiveRealisationCommand command);
}
