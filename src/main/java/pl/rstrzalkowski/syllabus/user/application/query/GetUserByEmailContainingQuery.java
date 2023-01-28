package pl.rstrzalkowski.syllabus.user.application.query;

import org.springframework.data.domain.Pageable;

public record GetUserByEmailContainingQuery(String email, Pageable pageable) {
}
