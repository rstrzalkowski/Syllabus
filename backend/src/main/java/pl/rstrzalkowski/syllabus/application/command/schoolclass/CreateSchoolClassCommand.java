package pl.rstrzalkowski.syllabus.application.command.schoolclass;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateSchoolClassCommand {

    @NotNull
    private Long levelId;

    @NotNull
    private Long teacherId;

    @NotNull
    private String name;

    private String description;
}
