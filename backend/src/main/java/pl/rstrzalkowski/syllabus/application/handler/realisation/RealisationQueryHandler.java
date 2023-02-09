package pl.rstrzalkowski.syllabus.application.handler.realisation;

import org.springframework.data.domain.Page;
import pl.rstrzalkowski.syllabus.application.dto.AverageGradeDTO;
import pl.rstrzalkowski.syllabus.application.dto.RealisationInfoDTO;
import pl.rstrzalkowski.syllabus.application.dto.RealisedSubjectDTO;
import pl.rstrzalkowski.syllabus.application.query.realisation.GetActiveRealisationsQuery;
import pl.rstrzalkowski.syllabus.application.query.realisation.GetArchivedRealisationsQuery;
import pl.rstrzalkowski.syllabus.application.query.realisation.GetOwnRealisationsQuery;
import pl.rstrzalkowski.syllabus.application.query.realisation.GetRealisationAverageGradeQuery;
import pl.rstrzalkowski.syllabus.application.query.realisation.GetRealisationByIdQuery;
import pl.rstrzalkowski.syllabus.application.query.realisation.GetRealisationInfoByIdQuery;
import pl.rstrzalkowski.syllabus.domain.model.Realisation;

import java.util.List;

public interface RealisationQueryHandler {
    Page<Realisation> handle(GetActiveRealisationsQuery query);

    Page<Realisation> handle(GetArchivedRealisationsQuery query);

    Realisation handle(GetRealisationByIdQuery query);

    AverageGradeDTO handle(GetRealisationAverageGradeQuery getRealisationAverageGradeQuery);

    List<RealisedSubjectDTO> handle(GetOwnRealisationsQuery getOwnRealisationsQuery);

    RealisationInfoDTO handle(GetRealisationInfoByIdQuery getRealisationInfoByIdQuery);
}
