package pl.rstrzalkowski.syllabus.application.query.user;

import org.springframework.data.domain.Pageable;

public record GetUnassignedStudentsQuery(Pageable pageable) {
}
