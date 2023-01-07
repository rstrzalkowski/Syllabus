package pl.rstrzalkowski.syllabus.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddStudentsDTO {
    @NotNull
    private List<Long> studentIds;
}
