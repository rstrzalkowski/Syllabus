package pl.rstrzalkowski.syllabus.domain.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.rstrzalkowski.syllabus.domain.exception.SyllabusException;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyRegisteredException extends SyllabusException {
}
