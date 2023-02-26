package pl.rstrzalkowski.syllabus.application.command.subject;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateSubjectCommand {

    @NotNull
    private String name;

    @NotNull
    private String abbreviation;
}
