package pl.rstrzalkowski.syllabus.exception.level;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.rstrzalkowski.syllabus.shared.SyllabusException;

@ResponseStatus(HttpStatus.CONFLICT)
public class LevelAlreadyExistsException extends SyllabusException {
}
