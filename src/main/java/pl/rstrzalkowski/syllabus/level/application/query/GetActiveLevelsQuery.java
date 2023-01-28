package pl.rstrzalkowski.syllabus.level.application.query;


import org.springframework.data.domain.Pageable;

public record GetActiveLevelsQuery(Pageable pageable) {
}
