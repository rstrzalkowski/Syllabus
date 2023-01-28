package pl.rstrzalkowski.syllabus.activity.application.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateActivityCommand {

    @NotNull
    private Long id;
    private String name;

    private Integer weight;

    private String description;
}
