package pl.rstrzalkowski.syllabus.application.command.activity;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateActivityCommand {

    private Long id;

    @Length(max = 40)
    private String name;

    @Min(1)
    private Integer weight;

    @Length(max = 200)
    private String description;

    private LocalDateTime date;
}
