package pl.rstrzalkowski.syllabus.subject.domain.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.subject.application.SubjectCommandHandler;
import pl.rstrzalkowski.syllabus.subject.application.command.ArchiveSubjectCommand;
import pl.rstrzalkowski.syllabus.subject.application.command.CreateSubjectCommand;
import pl.rstrzalkowski.syllabus.subject.application.command.UpdateSubjectCommand;
import pl.rstrzalkowski.syllabus.subject.domain.service.SubjectCommandService;

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

}
