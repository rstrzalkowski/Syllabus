package pl.rstrzalkowski.syllabus.application.command.level;

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
