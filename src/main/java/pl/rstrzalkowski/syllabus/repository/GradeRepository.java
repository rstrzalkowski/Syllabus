package pl.rstrzalkowski.syllabus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rstrzalkowski.syllabus.domain.Grade;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByActivityIdAndStudentId(Long activityId, Long studentId);

    List<Grade> findByActivityId(Long activityId);

    List<Grade> findByActivityRealisationIdAndStudentId(Long courseId, Long studentId);
}
