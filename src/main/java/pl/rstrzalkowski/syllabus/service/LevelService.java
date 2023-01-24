package pl.rstrzalkowski.syllabus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.rstrzalkowski.syllabus.domain.Level;
import pl.rstrzalkowski.syllabus.dto.create.CreateLevelDTO;
import pl.rstrzalkowski.syllabus.dto.update.UpdateLevelDTO;
import pl.rstrzalkowski.syllabus.exception.LevelAlreadyExistsException;
import pl.rstrzalkowski.syllabus.exception.LevelNotFoundException;
import pl.rstrzalkowski.syllabus.exception.schoolClass.SchoolClassNotFoundException;
import pl.rstrzalkowski.syllabus.repository.LevelRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class LevelService {

    private final LevelRepository levelRepository;

    public Level getById(Long id) {
        return levelRepository.findById(id)
                .orElseThrow(LevelNotFoundException::new);
    }

    public List<Level> getAll() {
        return levelRepository.findAll();
    }

    public Level create(CreateLevelDTO dto) {
        Level level = new Level();
        level.setLevel(dto.getLevel());
        try {
            level = levelRepository.save(level);
        } catch (Exception e) {
            throw new LevelAlreadyExistsException();
        }
        return level;
    }

    public Level update(Long id, UpdateLevelDTO dto) {
        Level level = getById(id);

        if (Objects.equals(level.getLevel(), dto.getLevel())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        level.setLevel(dto.getLevel());
        return levelRepository.save(level);
    }

    public void deleteById(Long id) {
        try {
            Level level = getById(id);
            level.setArchived(true);
            levelRepository.save(level);
        } catch (SchoolClassNotFoundException ignored) {
        }
    }
}
