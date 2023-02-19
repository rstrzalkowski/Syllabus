package pl.rstrzalkowski.syllabus.application.query.activity;


import org.springframework.data.domain.Pageable;

public record GetIncomingActivitiesQuery(Pageable pageable) {
}
