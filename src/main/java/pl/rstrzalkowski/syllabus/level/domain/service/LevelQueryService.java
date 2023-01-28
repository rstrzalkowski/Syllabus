package pl.rstrzalkowski.syllabus.level.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.rstrzalkowski.syllabus.level.domain.exception.LevelNotFoundException;
import pl.rstrzalkowski.syllabus.level.domain.model.Level;
import pl.rstrzalkowski.syllabus.level.domain.repository.LevelRepository;

@Service
@RequiredArgsConstructor
public class LevelQueryService {

    private final LevelRepository levelRepository;

    public Page<Level> getAllActive(Pageable pageable) {
        return levelRepository.findByArchived(false, pageable);
    }


    public Page<Level> getAllArchived(Pageable pageable) {
        return levelRepository.findByArchived(true, pageable);
    }


    public Level getById(Long id) {
        return levelRepository.findById(id)
                .orElseThrow(LevelNotFoundException::new);
    }

    public Level getByValue(int level) {
        return levelRepository.findByLevel(level)
                .orElseThrow(LevelNotFoundException::new);
    }
}
