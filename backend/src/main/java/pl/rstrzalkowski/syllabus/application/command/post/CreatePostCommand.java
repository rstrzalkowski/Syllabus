package pl.rstrzalkowski.syllabus.application.command.post;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreatePostCommand {

    @NotNull
    @Length(min = 1, max = 40)
    private String title;

    @NotNull
    @Length(min = 1, max = 2000)
    private String content;

    @NotNull
    private Long realisationId;
}
