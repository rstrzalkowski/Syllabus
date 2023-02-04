package pl.rstrzalkowski.syllabus.application.handler.user;

import pl.rstrzalkowski.syllabus.application.command.user.LoginCommand;
import pl.rstrzalkowski.syllabus.application.command.user.RegisterCommand;
import pl.rstrzalkowski.syllabus.infrastructure.security.JwtResponse;

public interface AuthHandler {
    JwtResponse handle(LoginCommand command);

    void handle(RegisterCommand command);
}
