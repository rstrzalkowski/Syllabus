package pl.rstrzalkowski.syllabus.application.controller.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.application.command.GenerateRegistrationTokensCommand;
import pl.rstrzalkowski.syllabus.application.handler.user.UserCommandHandler;
import pl.rstrzalkowski.syllabus.domain.model.RegistrationToken;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserCommandController {

    private final UserCommandHandler userCommandHandler;


    @PostMapping("tokens")
    public List<RegistrationToken> generateRegistrationTokens(@Valid @RequestBody GenerateRegistrationTokensCommand command) {
        return userCommandHandler.handle(command);
    }

}
