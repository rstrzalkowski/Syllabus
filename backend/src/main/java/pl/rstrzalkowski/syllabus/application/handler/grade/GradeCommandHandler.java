package pl.rstrzalkowski.syllabus.application.handler.grade;

import pl.rstrzalkowski.syllabus.application.command.grade.ArchiveGradeCommand;
import pl.rstrzalkowski.syllabus.application.command.grade.CreateGradeCommand;
import pl.rstrzalkowski.syllabus.application.command.grade.UpdateGradeCommand;

public interface GradeCommandHandler {
    void handle(CreateGradeCommand command);

    void handle(UpdateGradeCommand command);

    void handle(ArchiveGradeCommand command);
}
