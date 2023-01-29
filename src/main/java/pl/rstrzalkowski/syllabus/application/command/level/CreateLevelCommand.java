package pl.rstrzalkowski.syllabus.application.command.level;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateLevelCommand {

    @NotNull
    private Integer level;
}
