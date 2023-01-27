package pl.rstrzalkowski.syllabus.realisation.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rstrzalkowski.syllabus.realisation.domain.model.Realisation;

@Repository
public interface RealisationRepository extends JpaRepository<Realisation, Long> {
    Page<Realisation> findAllByArchived(boolean archived, Pageable pageable);
}
