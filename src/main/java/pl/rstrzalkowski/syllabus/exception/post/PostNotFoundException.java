package pl.rstrzalkowski.syllabus.exception.post;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.rstrzalkowski.syllabus.exception.SyllabusException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFoundException extends SyllabusException {
}
