package pl.rstrzalkowski.syllabus.application.controller.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.application.command.user.LoginCommand;
import pl.rstrzalkowski.syllabus.application.command.user.RegisterCommand;
import pl.rstrzalkowski.syllabus.application.handler.user.AuthHandler;
import pl.rstrzalkowski.syllabus.infrastructure.security.JwtResponse;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthHandler authHandler;

    @PostMapping("/authorize")
    public JwtResponse login(@Valid @RequestBody LoginCommand command) {
        return authHandler.handle(command);
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterCommand command) {
        authHandler.handle(command);
    }
}
