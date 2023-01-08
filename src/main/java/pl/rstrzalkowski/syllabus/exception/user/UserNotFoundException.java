package pl.rstrzalkowski.syllabus.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.rstrzalkowski.syllabus.exception.SyllabusException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends SyllabusException {
}
