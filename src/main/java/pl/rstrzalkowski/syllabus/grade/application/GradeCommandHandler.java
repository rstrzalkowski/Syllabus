package pl.rstrzalkowski.syllabus.grade.application;

import pl.rstrzalkowski.syllabus.grade.application.command.ArchiveGradeCommand;
import pl.rstrzalkowski.syllabus.grade.application.command.CreateGradeCommand;
import pl.rstrzalkowski.syllabus.grade.application.command.UpdateGradeCommand;

public interface GradeCommandHandler {
    void handle(CreateGradeCommand command);

    void handle(UpdateGradeCommand command);

    void handle(ArchiveGradeCommand command);
}
