package pl.rstrzalkowski.syllabus.application.handler.schoolclass;

import pl.rstrzalkowski.syllabus.application.command.schoolclass.ArchiveSchoolClassCommand;
import pl.rstrzalkowski.syllabus.application.command.schoolclass.CreateSchoolClassCommand;
import pl.rstrzalkowski.syllabus.application.command.schoolclass.UpdateSchoolClassCommand;

public interface SchoolClassCommandHandler {
    void handle(CreateSchoolClassCommand command);

    void handle(UpdateSchoolClassCommand command);

    void handle(ArchiveSchoolClassCommand command);
}
