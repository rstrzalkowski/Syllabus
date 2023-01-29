package pl.rstrzalkowski.syllabus.application.command.grade;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateGradeCommand {

    @Min(1)
    @Max(5)
    @NotNull
    private Integer value;

    @NotNull
    private Long studentId;

    @NotNull
    private Long activityId;

    @NotNull
    private Long teacherId;
}
