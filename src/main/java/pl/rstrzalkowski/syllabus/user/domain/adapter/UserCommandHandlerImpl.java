package pl.rstrzalkowski.syllabus.user.domain.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.user.application.UserCommandHandler;
import pl.rstrzalkowski.syllabus.user.application.command.ArchiveUserCommand;
import pl.rstrzalkowski.syllabus.user.application.command.CreateUserByAdminCommand;
import pl.rstrzalkowski.syllabus.user.application.command.UpdateUserCommand;
import pl.rstrzalkowski.syllabus.user.domain.service.UserCommandService;

@RequiredArgsConstructor
@Component
public class UserCommandHandlerImpl implements UserCommandHandler {

    private final UserCommandService userCommandService;

    @Override
    public void handle(CreateUserByAdminCommand command) {
        userCommandService.create(command);
    }

    @Override
    public void handle(UpdateUserCommand command) {
        userCommandService.update(command);
    }

    @Override
    public void handle(ArchiveUserCommand command) {
        userCommandService.archiveById(command);
    }

}
