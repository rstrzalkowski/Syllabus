package pl.rstrzalkowski.syllabus.realisation.domain.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.realisation.application.RealisationQueryHandler;
import pl.rstrzalkowski.syllabus.realisation.application.query.GetActiveRealisationsQuery;
import pl.rstrzalkowski.syllabus.realisation.application.query.GetArchivedRealisationsQuery;
import pl.rstrzalkowski.syllabus.realisation.application.query.GetRealisationByIdQuery;
import pl.rstrzalkowski.syllabus.realisation.domain.model.Realisation;
import pl.rstrzalkowski.syllabus.realisation.domain.service.RealisationQueryService;

@RequiredArgsConstructor
@Component
public class RealisationQueryHandlerImpl implements RealisationQueryHandler {

    private final RealisationQueryService realisationQueryService;

    @Override
    public Page<Realisation> handle(GetActiveRealisationsQuery query) {
        return realisationQueryService.getAllActive(query.pageable());
    }

    @Override
    public Page<Realisation> handle(GetArchivedRealisationsQuery query) {
        return realisationQueryService.getAllArchived(query.pageable());
    }

    @Override
    public Realisation handle(GetRealisationByIdQuery query) {
        return realisationQueryService.getById(query.id());
    }
}
