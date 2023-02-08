package pl.rstrzalkowski.syllabus.domain.adapter.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.application.command.user.LoginCommand;
import pl.rstrzalkowski.syllabus.application.command.user.RegisterCommand;
import pl.rstrzalkowski.syllabus.application.controller.security.JwtResponse;
import pl.rstrzalkowski.syllabus.application.handler.user.AuthHandler;
import pl.rstrzalkowski.syllabus.domain.service.user.AuthService;

@RequiredArgsConstructor
@Component
public class AuthHandlerImpl implements AuthHandler {

    private final AuthService authService;

    @Override
    public JwtResponse handle(LoginCommand command) {
        return authService.login(command);
    }

    @Override
    public void handle(RegisterCommand command) {
        authService.register(command);
    }
}
