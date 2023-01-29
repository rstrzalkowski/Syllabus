package pl.rstrzalkowski.syllabus.application.handler.realisation;

import org.springframework.data.domain.Page;
import pl.rstrzalkowski.syllabus.application.query.realisation.GetActiveRealisationsQuery;
import pl.rstrzalkowski.syllabus.application.query.realisation.GetArchivedRealisationsQuery;
import pl.rstrzalkowski.syllabus.application.query.realisation.GetRealisationByIdQuery;
import pl.rstrzalkowski.syllabus.domain.model.Realisation;

public interface RealisationQueryHandler {
    Page<Realisation> handle(GetActiveRealisationsQuery query);

    Page<Realisation> handle(GetArchivedRealisationsQuery query);

    Realisation handle(GetRealisationByIdQuery query);
}
