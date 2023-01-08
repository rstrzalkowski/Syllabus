package pl.rstrzalkowski.syllabus.dto.update;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateGradeDTO {
    @Min(1)
    @Max(5)
    private Integer value;
}
