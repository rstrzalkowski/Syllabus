package pl.rstrzalkowski.syllabus.subject.application.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateSubjectCommand {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String abbreviation;
}