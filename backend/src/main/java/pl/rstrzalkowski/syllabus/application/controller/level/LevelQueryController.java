package pl.rstrzalkowski.syllabus.application.controller.level;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.application.handler.level.LevelQueryHandler;
import pl.rstrzalkowski.syllabus.application.query.level.GetActiveLevelsQuery;
import pl.rstrzalkowski.syllabus.application.query.level.GetArchivedLevelsQuery;
import pl.rstrzalkowski.syllabus.application.query.level.GetLevelByIdQuery;
import pl.rstrzalkowski.syllabus.application.query.level.GetLevelByValueQuery;
import pl.rstrzalkowski.syllabus.domain.model.Level;

@RestController
@RequestMapping("/levels")
@RequiredArgsConstructor
public class LevelQueryController {

    private final LevelQueryHandler levelQueryHandler;

    @GetMapping("/{id}")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public Level getLevelById(@PathVariable("id") Long id) {
        return levelQueryHandler.handle(new GetLevelByIdQuery(id));
    }

    @GetMapping("/search")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public Level getLevelById(@PathParam("value") int value) {
        return levelQueryHandler.handle(new GetLevelByValueQuery(value));
    }

    @GetMapping
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public Page<Level> getActiveLevels(Pageable pageable) {
        return levelQueryHandler.handle(new GetActiveLevelsQuery(pageable));
    }

    @GetMapping("/archived")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public Page<Level> getArchivedLevels(Pageable pageable) {
        return levelQueryHandler.handle(new GetArchivedLevelsQuery(pageable));
    }
}
