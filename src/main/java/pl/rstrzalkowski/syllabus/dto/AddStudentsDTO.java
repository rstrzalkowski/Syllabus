package pl.rstrzalkowski.syllabus.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AddStudentsDTO {
    @NotNull
    private Long courseId;

    @NotNull
    private List<Long> studentIds;
}
