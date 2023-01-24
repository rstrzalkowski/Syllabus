package pl.rstrzalkowski.syllabus.dto.update;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateLevelDTO {
    @NotNull
    private Integer level;
}
