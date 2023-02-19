package pl.rstrzalkowski.syllabus.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rstrzalkowski.syllabus.domain.model.Activity;

import java.time.LocalDateTime;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Page<Activity> findByRealisationIdAndArchived(Long realisationId, boolean archived, Pageable pageable);

    Page<Activity> findByRealisationIdAndArchivedAndDateAfter(Long realisationId, boolean archived, LocalDateTime date, Pageable pageable);

    Page<Activity> findByRealisation_SchoolClass_Students_IdAndArchivedAndDateGreaterThanEqual(Long studentId, boolean archived, LocalDateTime date, Pageable pageable);
}
