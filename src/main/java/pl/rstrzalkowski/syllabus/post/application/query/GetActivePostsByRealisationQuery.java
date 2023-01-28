package pl.rstrzalkowski.syllabus.post.application.query;


import org.springframework.data.domain.Pageable;

public record GetActivePostsByRealisationQuery(Long realisationId, Pageable pageable) {
}
