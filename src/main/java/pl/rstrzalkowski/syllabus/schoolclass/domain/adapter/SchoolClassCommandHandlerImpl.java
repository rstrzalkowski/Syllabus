package pl.rstrzalkowski.syllabus.schoolclass.domain.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.schoolclass.application.SchoolClassCommandHandler;
import pl.rstrzalkowski.syllabus.schoolclass.application.command.ArchiveSchoolClassCommand;
import pl.rstrzalkowski.syllabus.schoolclass.application.command.CreateSchoolClassCommand;
import pl.rstrzalkowski.syllabus.schoolclass.application.command.UpdateSchoolClassCommand;
import pl.rstrzalkowski.syllabus.schoolclass.domain.service.SchoolClassCommandService;

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
