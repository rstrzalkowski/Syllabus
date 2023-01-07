package pl.rstrzalkowski.syllabus.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.domain.Course;
import pl.rstrzalkowski.syllabus.dto.AddStudentsDTO;
import pl.rstrzalkowski.syllabus.dto.AddTeachersDTO;
import pl.rstrzalkowski.syllabus.dto.CreateCourseDTO;
import pl.rstrzalkowski.syllabus.service.CourseService;

import java.util.List;


@RestController
@RequestMapping(path = "courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

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

    @PutMapping("/students")
    public void addStudents(@Valid @RequestBody AddStudentsDTO dto) {
        courseService.addStudentsToCourse(dto);
    }

    @PutMapping("/teachers")
    public void addTeachers(@Valid @RequestBody AddTeachersDTO dto) {
        courseService.addTeachersToCourse(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        courseService.deleteById(id);
    }
}
