package pl.rstrzalkowski.syllabus.application.query.post;


import org.springframework.data.domain.Pageable;

public record GetActivePostsByRealisationQuery(Long realisationId, Pageable pageable) {
}
