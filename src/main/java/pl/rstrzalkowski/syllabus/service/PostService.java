package pl.rstrzalkowski.syllabus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.rstrzalkowski.syllabus.domain.Post;
import pl.rstrzalkowski.syllabus.domain.Realisation;
import pl.rstrzalkowski.syllabus.domain.User;
import pl.rstrzalkowski.syllabus.dto.create.CreatePostDTO;
import pl.rstrzalkowski.syllabus.dto.update.UpdatePostDTO;
import pl.rstrzalkowski.syllabus.exception.post.PostNotFoundException;
import pl.rstrzalkowski.syllabus.exception.realisation.RealisationNotFoundException;
import pl.rstrzalkowski.syllabus.repository.PostRepository;
import pl.rstrzalkowski.syllabus.repository.RealisationRepository;
import pl.rstrzalkowski.syllabus.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final RealisationRepository realisationRepository;
    private final UserRepository userRepository;

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Post getById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
    }

    public List<Post> getByCourseId(Long courseId) {
        return postRepository.findByRealisationId(courseId);
    }

    public Post create(Long courseId, CreatePostDTO dto) {
        Post post = new Post();
        post.setContent(dto.getContent());

        Realisation realisation = realisationRepository.findById(courseId)
                .orElseThrow(RealisationNotFoundException::new);

        User teacher = userRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        post.setRealisation(realisation);
        post.setTeacher(teacher);
        return postRepository.save(post);
    }

    public Post update(Long id, UpdatePostDTO dto) {
        Post post = getById(id);
        post.setContent(dto.getContent() == null ? post.getContent() : dto.getContent());
        return postRepository.save(post);
    }

    public void deleteById(Long id) {
        try {
            Post post = getById(id);
            postRepository.delete(post);
        } catch (PostNotFoundException ignored) {
        }
    }
}
