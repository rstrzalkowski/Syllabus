package pl.rstrzalkowski.syllabus.level.infrastructure;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.level.application.LevelQueryHandler;
import pl.rstrzalkowski.syllabus.level.application.query.GetActiveLevelsQuery;
import pl.rstrzalkowski.syllabus.level.application.query.GetArchivedLevelsQuery;
import pl.rstrzalkowski.syllabus.level.application.query.GetLevelByIdQuery;
import pl.rstrzalkowski.syllabus.level.application.query.GetLevelByValueQuery;
import pl.rstrzalkowski.syllabus.level.domain.model.Level;

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
