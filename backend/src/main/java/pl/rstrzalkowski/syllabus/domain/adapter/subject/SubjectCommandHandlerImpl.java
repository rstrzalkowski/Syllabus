package pl.rstrzalkowski.syllabus.domain.adapter.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.application.command.subject.ArchiveSubjectCommand;
import pl.rstrzalkowski.syllabus.application.command.subject.CreateSubjectCommand;
import pl.rstrzalkowski.syllabus.application.command.subject.UpdateSubjectCommand;
import pl.rstrzalkowski.syllabus.application.command.subject.UpdateSubjectImageCommand;
import pl.rstrzalkowski.syllabus.application.handler.subject.SubjectCommandHandler;
import pl.rstrzalkowski.syllabus.domain.service.subject.SubjectCommandService;

@RequiredArgsConstructor
@Component
public class SubjectCommandHandlerImpl implements SubjectCommandHandler {

    private final SubjectCommandService subjectCommandService;

    @Override
    public void handle(CreateSubjectCommand command) {
        subjectCommandService.create(command);
    }

    @Override
    public void handle(UpdateSubjectCommand command) {
        subjectCommandService.update(command);
    }

    @Override
    public void handle(ArchiveSubjectCommand command) {
        subjectCommandService.archiveById(command);
    }

    @Override
    public void handle(UpdateSubjectImageCommand command) {
        subjectCommandService.updateSubjectImage(command);
    }

}
