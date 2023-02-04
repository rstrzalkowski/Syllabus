package pl.rstrzalkowski.syllabus.domain.adapter.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.application.command.user.LoginCommand;
import pl.rstrzalkowski.syllabus.application.command.user.RegisterCommand;
import pl.rstrzalkowski.syllabus.application.handler.user.AuthHandler;
import pl.rstrzalkowski.syllabus.domain.service.user.AuthService;
import pl.rstrzalkowski.syllabus.infrastructure.security.JwtResponse;

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
