package pl.rstrzalkowski.syllabus.exception.schoolClass;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.rstrzalkowski.syllabus.exception.SyllabusException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SchoolClassNotFoundException extends SyllabusException {
}
