package pl.rstrzalkowski.syllabus.application.command.schoolclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateSchoolClassCommand {

    private Long id;

    private Long levelId;

    private Long teacherId;

    private String shortName;

    private String fullName;
}
