package pl.rstrzalkowski.syllabus.application.command.user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateDescriptionCommand {

    @NotNull
    @Length(max = 1024)
    private String description;
}
