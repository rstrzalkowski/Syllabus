package pl.rstrzalkowski.syllabus.application.query.level;


import org.springframework.data.domain.Pageable;

public record GetActiveLevelsQuery(Pageable pageable) {
}
