package pl.rstrzalkowski.syllabus.realisation.application;

import pl.rstrzalkowski.syllabus.realisation.application.command.ArchiveRealisationCommand;
import pl.rstrzalkowski.syllabus.realisation.application.command.CreateRealisationCommand;
import pl.rstrzalkowski.syllabus.realisation.application.command.UpdateRealisationCommand;

public interface RealisationCommandHandler {
    void handle(CreateRealisationCommand command);

    void handle(UpdateRealisationCommand command);

    void handle(ArchiveRealisationCommand command);
}
