package pl.rstrzalkowski.syllabus.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateActivityDTO {
    private String name;

    private Integer weight;

    private String description;
}
