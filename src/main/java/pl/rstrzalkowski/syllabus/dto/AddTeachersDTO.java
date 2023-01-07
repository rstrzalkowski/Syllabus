package pl.rstrzalkowski.syllabus.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddTeachersDTO {
    @NotNull
    private List<Long> teacherIds;
}
