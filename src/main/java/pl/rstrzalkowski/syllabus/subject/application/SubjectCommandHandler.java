package pl.rstrzalkowski.syllabus.subject.application;

import pl.rstrzalkowski.syllabus.subject.application.command.ArchiveSubjectCommand;
import pl.rstrzalkowski.syllabus.subject.application.command.CreateSubjectCommand;
import pl.rstrzalkowski.syllabus.subject.application.command.UpdateSubjectCommand;

public interface SubjectCommandHandler {
    void handle(CreateSubjectCommand command);

    void handle(UpdateSubjectCommand command);

    void handle(ArchiveSubjectCommand command);
}
