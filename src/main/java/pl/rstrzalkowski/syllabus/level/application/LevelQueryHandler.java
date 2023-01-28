package pl.rstrzalkowski.syllabus.level.application;

import org.springframework.data.domain.Page;
import pl.rstrzalkowski.syllabus.level.application.query.GetActiveLevelsQuery;
import pl.rstrzalkowski.syllabus.level.application.query.GetArchivedLevelsQuery;
import pl.rstrzalkowski.syllabus.level.application.query.GetLevelByIdQuery;
import pl.rstrzalkowski.syllabus.level.application.query.GetLevelByValueQuery;
import pl.rstrzalkowski.syllabus.level.domain.model.Level;

public interface LevelQueryHandler {
    Page<Level> handle(GetActiveLevelsQuery query);

    Page<Level> handle(GetArchivedLevelsQuery query);

    Level handle(GetLevelByIdQuery query);

    Level handle(GetLevelByValueQuery query);
}
