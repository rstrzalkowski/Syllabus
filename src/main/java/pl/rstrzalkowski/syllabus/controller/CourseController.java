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
import pl.rstrzalkowski.syllabus.domain.Activity;
import pl.rstrzalkowski.syllabus.domain.Course;
import pl.rstrzalkowski.syllabus.domain.Post;
import pl.rstrzalkowski.syllabus.domain.user.Student;
import pl.rstrzalkowski.syllabus.domain.user.Teacher;
import pl.rstrzalkowski.syllabus.dto.add.AddStudentsDTO;
import pl.rstrzalkowski.syllabus.dto.add.AddTeachersDTO;
import pl.rstrzalkowski.syllabus.dto.create.CreateActivityDTO;
import pl.rstrzalkowski.syllabus.dto.create.CreateCourseDTO;
import pl.rstrzalkowski.syllabus.dto.create.CreatePostDTO;
import pl.rstrzalkowski.syllabus.dto.update.UpdateCourseDTO;
import pl.rstrzalkowski.syllabus.service.ActivityService;
import pl.rstrzalkowski.syllabus.service.CourseService;
import pl.rstrzalkowski.syllabus.service.PostService;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final PostService postService;
    private final ActivityService activityService;

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
    public List<Course> getActiveCourses() {
        return courseService.getAllActive();
    }

    @GetMapping("/archived")
    public List<Course> getArchivedCourses() {
        return courseService.getAllArchived();
    }

    @GetMapping("/{id}/posts")
    public List<Post> getAllCoursePosts(@PathVariable("id") Long id) {
        return postService.getByCourseId(id);
    }

    @GetMapping("/{id}/activities")
    public List<Activity> getAllCourseActivities(@PathVariable("id") Long id) {
        return activityService.getByCourseId(id);
    }

    @GetMapping("/{id}/students")
    public Set<Student> getAllCourseStudents(@PathVariable("id") Long id) {
        return courseService.getStudentsByCourseId(id);
    }

    @GetMapping("/{id}/teachers")
    public Set<Teacher> getAllCourseTeachers(@PathVariable("id") Long id) {
        return courseService.getTeachersByCourseId(id);
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

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}/activities")
    public void addActivity(@PathVariable("id") Long id, @Valid @RequestBody CreateActivityDTO dto) {
        activityService.create(id, dto);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable("id") Long id, @Valid @RequestBody UpdateCourseDTO dto) {
        return courseService.update(id, dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        courseService.deleteById(id);
    }
}
