package pl.rstrzalkowski.syllabus.application.command.level;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateLevelCommand {

    @NotNull
    @Min(0)
    private Integer level;
}
