package pl.rstrzalkowski.syllabus.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateActivityDTO {
    @NotNull
    private String name;

    @NotNull
    private Integer weight;

    @NotNull
    private String description;

    @NotNull
    //TODO get teacher from context instead of dto
    private Long teacherId;
}
