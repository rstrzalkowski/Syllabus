package pl.rstrzalkowski.syllabus.post.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rstrzalkowski.syllabus.post.domain.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByRealisationIdAndArchived(Long realisationId, boolean archived, Pageable pageable);
}
