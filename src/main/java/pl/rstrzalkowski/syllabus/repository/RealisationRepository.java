package pl.rstrzalkowski.syllabus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rstrzalkowski.syllabus.domain.Realisation;

import java.util.List;

@Repository
public interface RealisationRepository extends JpaRepository<Realisation, Long> {
    List<Realisation> findAllByArchivedIsFalse();

    List<Realisation> findAllByArchivedIsTrue();
}
