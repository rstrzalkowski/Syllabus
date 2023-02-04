package pl.rstrzalkowski.syllabus.application.command.post;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatePostCommand {

    @NotNull
    private Long id;

    @NotNull
    private String content;
}
