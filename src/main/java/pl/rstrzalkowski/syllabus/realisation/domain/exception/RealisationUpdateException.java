package pl.rstrzalkowski.syllabus.realisation.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.rstrzalkowski.syllabus.shared.SyllabusException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RealisationUpdateException extends SyllabusException {
}
