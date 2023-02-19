package pl.rstrzalkowski.syllabus.application.command.user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AssignCommand {

    @NotNull
    private Long classId;

    private Long studentId;
}
