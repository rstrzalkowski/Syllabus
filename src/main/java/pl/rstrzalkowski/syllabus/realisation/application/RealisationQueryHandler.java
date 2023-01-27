package pl.rstrzalkowski.syllabus.realisation.application;

import org.springframework.data.domain.Page;
import pl.rstrzalkowski.syllabus.realisation.application.query.GetActiveRealisationsQuery;
import pl.rstrzalkowski.syllabus.realisation.application.query.GetArchivedRealisationsQuery;
import pl.rstrzalkowski.syllabus.realisation.application.query.GetRealisationByIdQuery;
import pl.rstrzalkowski.syllabus.realisation.domain.model.Realisation;

public interface RealisationQueryHandler {
    Page<Realisation> handle(GetActiveRealisationsQuery query);

    Page<Realisation> handle(GetArchivedRealisationsQuery query);

    Realisation handle(GetRealisationByIdQuery query);
}
