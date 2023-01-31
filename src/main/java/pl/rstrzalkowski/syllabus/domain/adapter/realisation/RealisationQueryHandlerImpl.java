package pl.rstrzalkowski.syllabus.domain.adapter.realisation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.application.handler.realisation.RealisationQueryHandler;
import pl.rstrzalkowski.syllabus.application.query.realisation.GetActiveRealisationsQuery;
import pl.rstrzalkowski.syllabus.application.query.realisation.GetArchivedRealisationsQuery;
import pl.rstrzalkowski.syllabus.application.query.realisation.GetRealisationAverageGradeQuery;
import pl.rstrzalkowski.syllabus.application.query.realisation.GetRealisationByIdQuery;
import pl.rstrzalkowski.syllabus.domain.dto.AverageGradeDTO;
import pl.rstrzalkowski.syllabus.domain.model.Realisation;
import pl.rstrzalkowski.syllabus.domain.service.realisation.RealisationQueryService;

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

    @Override
    public AverageGradeDTO handle(GetRealisationAverageGradeQuery query) {
        return realisationQueryService.getRealisationAverageGrade(query.id());
    }
}
