package pl.rstrzalkowski.syllabus.post.application.query;


import org.springframework.data.domain.Pageable;

public record GetArchivedPostsByRealisationQuery(Long realisationId, Pageable pageable) {
}
