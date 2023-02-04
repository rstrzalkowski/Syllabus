package pl.rstrzalkowski.syllabus.application.command.grade;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateGradeCommand {

    @Min(1)
    @Max(5)
    private Integer value;

    private Long gradeId;
}
