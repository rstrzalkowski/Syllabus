package pl.rstrzalkowski.syllabus.schoolclass.application.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateSchoolClassCommand {

    @NotNull
    private Long id;

    @NotNull
    private Long levelId;

    private Long teacherId;
}
