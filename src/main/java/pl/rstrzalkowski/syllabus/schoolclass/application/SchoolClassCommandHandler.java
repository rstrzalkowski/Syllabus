package pl.rstrzalkowski.syllabus.schoolclass.application;

import pl.rstrzalkowski.syllabus.schoolclass.application.command.ArchiveSchoolClassCommand;
import pl.rstrzalkowski.syllabus.schoolclass.application.command.CreateSchoolClassCommand;
import pl.rstrzalkowski.syllabus.schoolclass.application.command.UpdateSchoolClassCommand;

public interface SchoolClassCommandHandler {
    void handle(CreateSchoolClassCommand command);

    void handle(UpdateSchoolClassCommand command);

    void handle(ArchiveSchoolClassCommand command);
}
