package pl.rstrzalkowski.syllabus.post.application.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreatePostCommand {

    @NotNull
    private String content;

    @NotNull
    private Long realisationId;

    @NotNull
    //TODO get teacher from context instead of dto
    private Long teacherId;
}
