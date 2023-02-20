package pl.rstrzalkowski.syllabus.application.handler.subject;

import pl.rstrzalkowski.syllabus.application.command.subject.ArchiveSubjectCommand;
import pl.rstrzalkowski.syllabus.application.command.subject.CreateSubjectCommand;
import pl.rstrzalkowski.syllabus.application.command.subject.UpdateSubjectCommand;
import pl.rstrzalkowski.syllabus.application.command.subject.UpdateSubjectImageCommand;

public interface SubjectCommandHandler {
    void handle(CreateSubjectCommand command);

    void handle(UpdateSubjectCommand command);

    void handle(ArchiveSubjectCommand command);

    void handle(UpdateSubjectImageCommand command);
}
