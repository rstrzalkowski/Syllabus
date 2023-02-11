package pl.rstrzalkowski.syllabus.application.command.activity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private LocalDateTime date;

    @NotNull
    //TODO get teacher from context instead of dto
    private Long teacherId;

    @NotNull
    private Long realisationId;
}
