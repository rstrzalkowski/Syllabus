package pl.rstrzalkowski.syllabus.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rstrzalkowski.syllabus.domain.model.Grade;
import pl.rstrzalkowski.syllabus.domain.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    Page<Grade> findAllByArchivedAndStudentId(boolean archived, Long studentId, Pageable pageable);

    Optional<Grade> findByActivityIdAndStudentId(Long activityId, Long studentId);

    List<Grade> findAllByActivityRealisationIdAndStudent(Long realisationId, User student);
}
