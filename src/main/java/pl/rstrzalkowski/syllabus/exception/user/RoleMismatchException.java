package pl.rstrzalkowski.syllabus.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.rstrzalkowski.syllabus.exception.SyllabusException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RoleMismatchException extends SyllabusException {
}
