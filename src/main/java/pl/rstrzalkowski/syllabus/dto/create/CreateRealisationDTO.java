package pl.rstrzalkowski.syllabus.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateRealisationDTO {

    @NotNull
    private Year year;

    @NotNull
    private Long teacherId;

    @NotNull
    private Long subjectId;

    @NotNull
    private Long classId;
}
