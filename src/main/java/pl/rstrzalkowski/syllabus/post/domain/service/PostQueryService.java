package pl.rstrzalkowski.syllabus.post.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.rstrzalkowski.syllabus.post.domain.exception.PostNotFoundException;
import pl.rstrzalkowski.syllabus.post.domain.model.Post;
import pl.rstrzalkowski.syllabus.post.domain.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostQueryService {

    private final PostRepository postRepository;

    public Page<Post> getAllActiveByRealisation(Long realisationId, Pageable pageable) {
        return postRepository.findByRealisationIdAndArchived(realisationId, false, pageable);
    }


    public Page<Post> getAllArchivedByRealisation(Long realisationId, Pageable pageable) {
        return postRepository.findByRealisationIdAndArchived(realisationId, true, pageable);
    }


    public Post getById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
    }
}
