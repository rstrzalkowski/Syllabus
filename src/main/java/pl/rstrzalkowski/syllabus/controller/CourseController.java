package pl.rstrzalkowski.syllabus.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.domain.Course;
import pl.rstrzalkowski.syllabus.domain.Post;
import pl.rstrzalkowski.syllabus.dto.AddStudentsDTO;
import pl.rstrzalkowski.syllabus.dto.AddTeachersDTO;
import pl.rstrzalkowski.syllabus.dto.CreateCourseDTO;
import pl.rstrzalkowski.syllabus.dto.CreatePostDTO;
import pl.rstrzalkowski.syllabus.service.CourseService;
import pl.rstrzalkowski.syllabus.service.PostService;

import java.util.List;


@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final PostService postService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Course createCourse(@Valid @RequestBody CreateCourseDTO dto) {
        return courseService.create(dto);
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable("id") Long id) {
        return courseService.getById(id);
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAll();
    }

    @GetMapping("/{id}/posts")
    public List<Post> getAllCoursePosts(@PathVariable("id") Long id) {
        return postService.getByCourseId(id);
    }

    @PutMapping("/{id}/students")
    public void addStudents(@PathVariable("id") Long id, @Valid @RequestBody AddStudentsDTO dto) {
        courseService.addStudentsToCourse(id, dto);
    }

    @PutMapping("/{id}/teachers")
    public void addTeachers(@PathVariable("id") Long id, @Valid @RequestBody AddTeachersDTO dto) {
        courseService.addTeachersToCourse(id, dto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}/posts")
    public void addPost(@PathVariable("id") Long id, @Valid @RequestBody CreatePostDTO dto) {
        postService.create(id, dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        courseService.deleteById(id);
    }
}
