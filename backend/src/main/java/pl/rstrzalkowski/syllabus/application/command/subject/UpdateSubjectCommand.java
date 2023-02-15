package pl.rstrzalkowski.syllabus.application.command.subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateSubjectCommand {

    private Long id;

    private String name;

    private String abbreviation;
}
