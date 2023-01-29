package pl.rstrzalkowski.syllabus.domain.exception.schoolclass;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.rstrzalkowski.syllabus.domain.exception.SyllabusException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SchoolClassUpdateException extends SyllabusException {
}
