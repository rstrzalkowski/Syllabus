package pl.rstrzalkowski.syllabus.realisation.application.query;


import org.springframework.data.domain.Pageable;

public record GetActiveRealisationsQuery(Pageable pageable) {
}
