package pl.rstrzalkowski.syllabus.level.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.rstrzalkowski.syllabus.level.application.command.ArchiveLevelCommand;
import pl.rstrzalkowski.syllabus.level.application.command.CreateLevelCommand;
import pl.rstrzalkowski.syllabus.level.application.command.UpdateLevelCommand;
import pl.rstrzalkowski.syllabus.level.domain.exception.LevelAlreadyExistsException;
import pl.rstrzalkowski.syllabus.level.domain.exception.LevelNotFoundException;
import pl.rstrzalkowski.syllabus.level.domain.model.Level;
import pl.rstrzalkowski.syllabus.level.domain.repository.LevelRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class LevelCommandService {

    private final LevelRepository levelRepository;


    public void create(CreateLevelCommand command) {
        Level level = new Level();
        level.setLevel(command.getLevel());

        if (levelRepository.findByLevel(level.getLevel()).isPresent()) {
            throw new LevelAlreadyExistsException();
        }

        levelRepository.save(level);
    }

    public void update(UpdateLevelCommand command) {
        Level level = levelRepository.findById(command.getId())
                .orElseThrow(LevelNotFoundException::new);

        if (Objects.equals(level.getLevel(), command.getLevel())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        level.setLevel(command.getLevel());
        levelRepository.save(level);
    }

    public void archiveById(ArchiveLevelCommand command) {
        Level level = levelRepository.findById(command.id())
                .orElseThrow(LevelNotFoundException::new);

        level.setArchived(true);
        levelRepository.save(level);
    }
}
