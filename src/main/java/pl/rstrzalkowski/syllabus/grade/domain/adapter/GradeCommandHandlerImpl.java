package pl.rstrzalkowski.syllabus.grade.domain.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.grade.application.GradeCommandHandler;
import pl.rstrzalkowski.syllabus.grade.application.command.ArchiveGradeCommand;
import pl.rstrzalkowski.syllabus.grade.application.command.CreateGradeCommand;
import pl.rstrzalkowski.syllabus.grade.application.command.UpdateGradeCommand;
import pl.rstrzalkowski.syllabus.grade.domain.service.GradeCommandService;

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
