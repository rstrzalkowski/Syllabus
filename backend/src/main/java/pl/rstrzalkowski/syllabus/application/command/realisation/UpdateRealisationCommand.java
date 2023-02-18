package pl.rstrzalkowski.syllabus.application.command.realisation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateRealisationCommand {

    private Long id;
    
    private Year year;

    private Long teacherId;
}
