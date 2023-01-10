package pl.rstrzalkowski.syllabus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rstrzalkowski.syllabus.domain.SubjectRealisation;

@Repository
public interface SubjectRealisationRepository extends JpaRepository<SubjectRealisation, Long> {
}
