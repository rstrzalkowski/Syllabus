package pl.rstrzalkowski.syllabus.activity.application.query;


import org.springframework.data.domain.Pageable;

public record GetActiveActivitiesByRealisationQuery(Long realisationId, Pageable pageable) {
}
