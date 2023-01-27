package pl.rstrzalkowski.syllabus.subject.application.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateSubjectCommand {

    @NotNull
    private Long id;
    private String name;

    private String description;

    private String abbreviation;
}
