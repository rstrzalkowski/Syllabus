package pl.rstrzalkowski.syllabus.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateGradeDTO {
    private Integer value;

    private Long studentId;

    private Long teacherId;
}
