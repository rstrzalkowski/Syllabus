package pl.rstrzalkowski.syllabus.user.application;

import pl.rstrzalkowski.syllabus.user.application.command.ArchiveUserCommand;
import pl.rstrzalkowski.syllabus.user.application.command.CreateUserByAdminCommand;
import pl.rstrzalkowski.syllabus.user.application.command.UpdateUserCommand;

public interface UserCommandHandler {
    void handle(CreateUserByAdminCommand command);

    void handle(UpdateUserCommand command);

    void handle(ArchiveUserCommand command);
}
