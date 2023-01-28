package pl.rstrzalkowski.syllabus.level.domain.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.level.application.LevelQueryHandler;
import pl.rstrzalkowski.syllabus.level.application.query.GetActiveLevelsQuery;
import pl.rstrzalkowski.syllabus.level.application.query.GetArchivedLevelsQuery;
import pl.rstrzalkowski.syllabus.level.application.query.GetLevelByIdQuery;
import pl.rstrzalkowski.syllabus.level.application.query.GetLevelByValueQuery;
import pl.rstrzalkowski.syllabus.level.domain.model.Level;
import pl.rstrzalkowski.syllabus.level.domain.service.LevelQueryService;

@RequiredArgsConstructor
@Component
public class LevelQueryHandlerImpl implements LevelQueryHandler {

    private final LevelQueryService levelQueryService;

    @Override
    public Page<Level> handle(GetActiveLevelsQuery query) {
        return levelQueryService.getAllActive(query.pageable());
    }

    @Override
    public Page<Level> handle(GetArchivedLevelsQuery query) {
        return levelQueryService.getAllArchived(query.pageable());
    }

    @Override
    public Level handle(GetLevelByIdQuery query) {
        return levelQueryService.getById(query.id());
    }

    @Override
    public Level handle(GetLevelByValueQuery query) {
        return levelQueryService.getByValue(query.level());
    }
}
