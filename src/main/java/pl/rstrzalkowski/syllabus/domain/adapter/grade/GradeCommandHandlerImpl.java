package pl.rstrzalkowski.syllabus.domain.adapter.grade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.application.command.grade.ArchiveGradeCommand;
import pl.rstrzalkowski.syllabus.application.command.grade.CreateGradeCommand;
import pl.rstrzalkowski.syllabus.application.command.grade.UpdateGradeCommand;
import pl.rstrzalkowski.syllabus.application.handler.grade.GradeCommandHandler;
import pl.rstrzalkowski.syllabus.domain.service.grade.GradeCommandService;

@RequiredArgsConstructor
@Component
public class GradeCommandHandlerImpl implements GradeCommandHandler {

    private final GradeCommandService gradeCommandService;

    @Override
    public void handle(CreateGradeCommand command) {
        gradeCommandService.create(command);
    }

    @Override
    public void handle(UpdateGradeCommand command) {
        gradeCommandService.update(command);
    }

    @Override
    public void handle(ArchiveGradeCommand command) {
        gradeCommandService.archiveById(command);
    }

}
