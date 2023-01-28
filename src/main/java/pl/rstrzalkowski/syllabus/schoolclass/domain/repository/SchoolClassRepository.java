package pl.rstrzalkowski.syllabus.schoolclass.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rstrzalkowski.syllabus.schoolclass.domain.model.SchoolClass;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
    Page<SchoolClass> findAllByArchived(boolean archived, Pageable pageable);
}
