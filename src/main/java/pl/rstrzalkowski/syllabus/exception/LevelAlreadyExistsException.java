package pl.rstrzalkowski.syllabus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class LevelAlreadyExistsException extends SyllabusException {
}
