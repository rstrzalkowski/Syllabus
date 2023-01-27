package pl.rstrzalkowski.syllabus.exception.grade;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.rstrzalkowski.syllabus.shared.SyllabusException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GradeNotFoundException extends SyllabusException {
}
