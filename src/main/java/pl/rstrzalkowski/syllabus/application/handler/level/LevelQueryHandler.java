package pl.rstrzalkowski.syllabus.application.handler.level;

import org.springframework.data.domain.Page;
import pl.rstrzalkowski.syllabus.application.query.level.GetActiveLevelsQuery;
import pl.rstrzalkowski.syllabus.application.query.level.GetArchivedLevelsQuery;
import pl.rstrzalkowski.syllabus.application.query.level.GetLevelByIdQuery;
import pl.rstrzalkowski.syllabus.application.query.level.GetLevelByValueQuery;
import pl.rstrzalkowski.syllabus.domain.model.Level;

public interface LevelQueryHandler {
    Page<Level> handle(GetActiveLevelsQuery query);

    Page<Level> handle(GetArchivedLevelsQuery query);

    Level handle(GetLevelByIdQuery query);

    Level handle(GetLevelByValueQuery query);
}
