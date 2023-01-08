package pl.rstrzalkowski.syllabus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.rstrzalkowski.syllabus.domain.Course;
import pl.rstrzalkowski.syllabus.domain.Post;
import pl.rstrzalkowski.syllabus.domain.user.Teacher;
import pl.rstrzalkowski.syllabus.dto.create.CreatePostDTO;
import pl.rstrzalkowski.syllabus.dto.update.UpdatePostDTO;
import pl.rstrzalkowski.syllabus.exception.CourseNotFoundException;
import pl.rstrzalkowski.syllabus.exception.PostNotFoundException;
import pl.rstrzalkowski.syllabus.exception.RoleMismatchException;
import pl.rstrzalkowski.syllabus.repository.CourseRepository;
import pl.rstrzalkowski.syllabus.repository.PostRepository;
import pl.rstrzalkowski.syllabus.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Post getById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
    }

    public List<Post> getByCourseId(Long courseId) {
        return postRepository.findByCourseId(courseId);
    }

    public Post create(Long courseId, CreatePostDTO dto) {
        Post post = new Post();
        post.setContent(dto.getContent());

        Teacher teacher;
        Course course;
        try {
            course = courseRepository.findById(courseId)
                    .orElseThrow(CourseNotFoundException::new);
            teacher = (Teacher) userRepository.findById(dto.getTeacherId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        } catch (ClassCastException e) {
            throw new RoleMismatchException();
        }
        post.setTeacher(teacher);
        post.setCourse(course);
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
