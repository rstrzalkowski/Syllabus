package pl.rstrzalkowski.syllabus.application.command.user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangePasswordCommand {

    @NotNull
    private String oldPassword;

    @NotNull
    private String newPassword;
}
