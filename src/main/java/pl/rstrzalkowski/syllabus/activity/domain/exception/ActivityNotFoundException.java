package pl.rstrzalkowski.syllabus.activity.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.rstrzalkowski.syllabus.shared.SyllabusException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ActivityNotFoundException extends SyllabusException {
}
