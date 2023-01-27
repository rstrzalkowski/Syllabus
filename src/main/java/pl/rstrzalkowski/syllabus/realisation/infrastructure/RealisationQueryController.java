package pl.rstrzalkowski.syllabus.realisation.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.realisation.application.RealisationQueryHandler;
import pl.rstrzalkowski.syllabus.realisation.application.query.GetActiveRealisationsQuery;
import pl.rstrzalkowski.syllabus.realisation.application.query.GetArchivedRealisationsQuery;
import pl.rstrzalkowski.syllabus.realisation.application.query.GetRealisationByIdQuery;
import pl.rstrzalkowski.syllabus.realisation.domain.model.Realisation;

@RestController
@RequestMapping("/realisations")
@RequiredArgsConstructor
public class RealisationQueryController {

    private final RealisationQueryHandler realisationQueryHandler;

    @GetMapping("/{id}")
    public Realisation getSubjectById(@PathVariable("id") Long id) {
        return realisationQueryHandler.handle(new GetRealisationByIdQuery(id));
    }

    @GetMapping
    public Page<Realisation> getActiveSubjects(Pageable pageable) {
        return realisationQueryHandler.handle(new GetActiveRealisationsQuery(pageable));
    }

    @GetMapping("/archived")
    public Page<Realisation> getArchivedSubjects(Pageable pageable) {
        return realisationQueryHandler.handle(new GetArchivedRealisationsQuery(pageable));
    }
}
