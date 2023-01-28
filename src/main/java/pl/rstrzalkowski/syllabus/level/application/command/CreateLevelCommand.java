package pl.rstrzalkowski.syllabus.level.application.command;

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
