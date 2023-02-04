package pl.rstrzalkowski.syllabus.infrastructure.level;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Level getLevelById(@PathVariable("id") Long id) {
        return levelQueryHandler.handle(new GetLevelByIdQuery(id));
    }

    @GetMapping("/search")
    public Level getLevelById(@PathParam("value") int value) {
        return levelQueryHandler.handle(new GetLevelByValueQuery(value));
    }

    @GetMapping
    public Page<Level> getActiveSubjects(Pageable pageable) {
        return levelQueryHandler.handle(new GetActiveLevelsQuery(pageable));
    }

    @GetMapping("/archived")
    public Page<Level> getArchivedSubjects(Pageable pageable) {
        return levelQueryHandler.handle(new GetArchivedLevelsQuery(pageable));
    }
}
