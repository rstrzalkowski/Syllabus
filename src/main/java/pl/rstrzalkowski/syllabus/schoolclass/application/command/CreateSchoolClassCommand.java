package pl.rstrzalkowski.syllabus.schoolclass.application.command;

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
}
