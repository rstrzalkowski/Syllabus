package pl.rstrzalkowski.syllabus.dto.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateRealisationDTO {
    private Year year;

    private Long teacherId;
}
