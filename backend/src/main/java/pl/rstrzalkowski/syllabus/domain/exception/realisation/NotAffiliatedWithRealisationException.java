package pl.rstrzalkowski.syllabus.domain.exception.realisation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.rstrzalkowski.syllabus.domain.exception.SyllabusException;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotAffiliatedWithRealisationException extends SyllabusException {
}
