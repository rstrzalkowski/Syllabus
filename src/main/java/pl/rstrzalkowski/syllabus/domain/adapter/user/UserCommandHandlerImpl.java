package pl.rstrzalkowski.syllabus.domain.adapter.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.application.command.user.ArchiveUserCommand;
import pl.rstrzalkowski.syllabus.application.command.user.CreateUserByAdminCommand;
import pl.rstrzalkowski.syllabus.application.command.user.UpdateUserCommand;
import pl.rstrzalkowski.syllabus.application.handler.user.UserCommandHandler;
import pl.rstrzalkowski.syllabus.domain.service.user.UserCommandService;

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
