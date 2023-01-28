package pl.rstrzalkowski.syllabus.level.application.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateLevelCommand {

    @NotNull
    private Long id;

    @NotNull
    private Integer level;
}
