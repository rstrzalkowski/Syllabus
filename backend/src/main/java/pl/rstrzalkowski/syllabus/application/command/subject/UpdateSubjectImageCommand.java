package pl.rstrzalkowski.syllabus.application.command.subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateSubjectImageCommand {

    private Long id;

    private MultipartFile image;
}
