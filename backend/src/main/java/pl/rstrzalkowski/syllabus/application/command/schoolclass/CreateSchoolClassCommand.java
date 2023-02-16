package pl.rstrzalkowski.syllabus.application.command.schoolclass;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateSchoolClassCommand {

    @NotNull
    private Long levelId;

    @NotNull
    private Long teacherId;

    @NotNull
    @Length(max = 5)
    private String shortName;

    @NotNull
    @Length(max = 40)
    private String fullName;
}
