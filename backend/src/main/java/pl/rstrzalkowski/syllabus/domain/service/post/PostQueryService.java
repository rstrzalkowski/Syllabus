package pl.rstrzalkowski.syllabus.domain.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.rstrzalkowski.syllabus.application.dto.RealisationPostDTO;
import pl.rstrzalkowski.syllabus.domain.exception.post.PostNotFoundException;
import pl.rstrzalkowski.syllabus.domain.model.Post;
import pl.rstrzalkowski.syllabus.infrastructure.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostQueryService {

    private final PostRepository postRepository;

    public Page<RealisationPostDTO> getAllActiveByRealisation(Long realisationId, Pageable pageable) {
        Page<Post> posts = postRepository.findByRealisationIdAndArchived(realisationId, false, pageable);
        return posts.map((RealisationPostDTO::new));
    }


    public Page<Post> getAllArchivedByRealisation(Long realisationId, Pageable pageable) {
        return postRepository.findByRealisationIdAndArchived(realisationId, true, pageable);
    }


    public Post getById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
    }
}
