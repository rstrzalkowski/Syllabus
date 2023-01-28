package pl.rstrzalkowski.syllabus.user.application.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginCommand {

    @NotNull
    private String login;

    @NotNull
    private String password;
}
