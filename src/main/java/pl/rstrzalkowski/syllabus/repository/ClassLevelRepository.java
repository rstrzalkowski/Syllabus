package pl.rstrzalkowski.syllabus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rstrzalkowski.syllabus.domain.ClassLevel;

@Repository
public interface ClassLevelRepository extends JpaRepository<ClassLevel, Long> {
}
