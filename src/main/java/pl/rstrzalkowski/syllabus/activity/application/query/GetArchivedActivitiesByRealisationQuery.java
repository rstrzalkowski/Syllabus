package pl.rstrzalkowski.syllabus.activity.application.query;


import org.springframework.data.domain.Pageable;

public record GetArchivedActivitiesByRealisationQuery(Long realisationId, Pageable pageable) {
}
