package pl.rstrzalkowski.syllabus.dto.add;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddStudentsDTO {
    @NotNull
    private List<Long> studentIds;
}