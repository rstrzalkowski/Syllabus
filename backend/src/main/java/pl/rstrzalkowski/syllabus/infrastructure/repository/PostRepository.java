package pl.rstrzalkowski.syllabus.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rstrzalkowski.syllabus.domain.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByRealisationIdAndArchived(Long realisationId, boolean archived, Pageable pageable);

    Page<Post> findByRealisation_SchoolClass_Students_IdAndArchived(Long studentId, boolean archived, Pageable pageable);
}
