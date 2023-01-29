package pl.rstrzalkowski.syllabus.application.handler.user;

import pl.rstrzalkowski.syllabus.application.command.user.ArchiveUserCommand;
import pl.rstrzalkowski.syllabus.application.command.user.CreateUserByAdminCommand;
import pl.rstrzalkowski.syllabus.application.command.user.UpdateUserCommand;

public interface UserCommandHandler {
    void handle(CreateUserByAdminCommand command);

    void handle(UpdateUserCommand command);

    void handle(ArchiveUserCommand command);
}
