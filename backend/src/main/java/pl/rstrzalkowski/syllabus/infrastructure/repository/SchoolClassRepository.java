package pl.rstrzalkowski.syllabus.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rstrzalkowski.syllabus.domain.model.SchoolClass;

import java.util.List;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
    Page<SchoolClass> findAllByArchived(boolean archived, Pageable pageable);

    SchoolClass findByArchivedAndNameAndLevelId(boolean archived, String name, Long levelId);

    Integer countByArchivedAndLevelId(boolean archived, Long levelId);

    List<SchoolClass> findAllByArchived(boolean archived);
}
