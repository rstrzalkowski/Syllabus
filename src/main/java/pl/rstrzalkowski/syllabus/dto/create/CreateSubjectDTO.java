package pl.rstrzalkowski.syllabus.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateSubjectDTO {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String abbreviation;
}
