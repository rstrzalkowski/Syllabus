package pl.rstrzalkowski.syllabus.application.command.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateProfileImageCommand {
    
    private MultipartFile image;
}
