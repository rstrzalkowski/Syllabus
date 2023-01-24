package pl.rstrzalkowski.syllabus.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateLevelDTO {
    @NotNull
    private Integer level;
}
