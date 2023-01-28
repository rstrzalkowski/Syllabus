package pl.rstrzalkowski.syllabus.level.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.rstrzalkowski.syllabus.shared.SyllabusException;

@ResponseStatus(HttpStatus.CONFLICT)
public class LevelAlreadyExistsException extends SyllabusException {
}
