package pl.rstrzalkowski.syllabus.application.controller.realisation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.application.dto.AverageGradeDTO;
import pl.rstrzalkowski.syllabus.application.dto.RealisedSubjectDTO;
import pl.rstrzalkowski.syllabus.application.handler.realisation.RealisationQueryHandler;
import pl.rstrzalkowski.syllabus.application.query.realisation.GetActiveRealisationsQuery;
import pl.rstrzalkowski.syllabus.application.query.realisation.GetArchivedRealisationsQuery;
import pl.rstrzalkowski.syllabus.application.query.realisation.GetOwnRealisationsQuery;
import pl.rstrzalkowski.syllabus.application.query.realisation.GetRealisationAverageGradeQuery;
import pl.rstrzalkowski.syllabus.application.query.realisation.GetRealisationByIdQuery;
import pl.rstrzalkowski.syllabus.domain.model.Realisation;

import java.util.List;

@RestController
@RequestMapping("/realisations")
@RequiredArgsConstructor
public class RealisationQueryController {

    private final RealisationQueryHandler realisationQueryHandler;

    @GetMapping("/{id}")
    public Realisation getRealisationById(@PathVariable("id") Long id) {
        return realisationQueryHandler.handle(new GetRealisationByIdQuery(id));
    }

    @GetMapping
    public Page<Realisation> getActiveRealisations(Pageable pageable) {
        return realisationQueryHandler.handle(new GetActiveRealisationsQuery(pageable));
    }

    @GetMapping("/archived")
    public Page<Realisation> getArchivedRealisations(Pageable pageable) {
        return realisationQueryHandler.handle(new GetArchivedRealisationsQuery(pageable));
    }

    @GetMapping("/{id}/average")
    @Secured("STUDENT")
    public AverageGradeDTO getRealisationAverageGrade(@PathVariable("id") Long id) {
        return realisationQueryHandler.handle(new GetRealisationAverageGradeQuery(id));
    }

    @GetMapping("/me")
    @Secured({"STUDENT", "TEACHER"})
    public List<RealisedSubjectDTO> getOwnRealisations() {
        return realisationQueryHandler.handle(new GetOwnRealisationsQuery());
    }
}
