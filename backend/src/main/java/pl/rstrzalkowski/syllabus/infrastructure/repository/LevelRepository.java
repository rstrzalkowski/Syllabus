package pl.rstrzalkowski.syllabus.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rstrzalkowski.syllabus.domain.model.Level;

import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    Page<Level> findByArchived(boolean archived, Pageable pageable);

    Optional<Level> findByValue(int level);

    Optional<Level> findByArchivedAndValue(boolean archived, int level);
}
