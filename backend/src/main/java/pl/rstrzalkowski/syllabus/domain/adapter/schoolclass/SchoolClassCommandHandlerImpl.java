package pl.rstrzalkowski.syllabus.domain.adapter.schoolclass;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.application.command.schoolclass.ArchiveSchoolClassCommand;
import pl.rstrzalkowski.syllabus.application.command.schoolclass.CreateSchoolClassCommand;
import pl.rstrzalkowski.syllabus.application.command.schoolclass.UpdateSchoolClassCommand;
import pl.rstrzalkowski.syllabus.application.handler.schoolclass.SchoolClassCommandHandler;
import pl.rstrzalkowski.syllabus.domain.service.schoolclass.SchoolClassCommandService;

@RequiredArgsConstructor
@Component
public class SchoolClassCommandHandlerImpl implements SchoolClassCommandHandler {

    private final SchoolClassCommandService schoolClassCommandService;

    @Override
    public void handle(CreateSchoolClassCommand command) {
        schoolClassCommandService.create(command);
    }

    @Override
    public void handle(UpdateSchoolClassCommand command) {
        schoolClassCommandService.update(command);
    }

    @Override
    public void handle(ArchiveSchoolClassCommand command) {
        schoolClassCommandService.archiveById(command);
    }

}
