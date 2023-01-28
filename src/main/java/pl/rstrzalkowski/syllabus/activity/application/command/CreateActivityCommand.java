package pl.rstrzalkowski.syllabus.activity.application.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateActivityCommand {

    @NotNull
    private String name;

    @NotNull
    private Integer weight;

    @NotNull
    private String description;

    @NotNull
    //TODO get teacher from context instead of dto
    private Long teacherId;

    private Long realisationId;
}
