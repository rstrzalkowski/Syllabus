package pl.rstrzalkowski.syllabus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rstrzalkowski.syllabus.domain.user.Course;
import pl.rstrzalkowski.syllabus.domain.user.User;
import pl.rstrzalkowski.syllabus.dto.AddStudentsDTO;
import pl.rstrzalkowski.syllabus.dto.AddTeachersDTO;
import pl.rstrzalkowski.syllabus.dto.CreateCourseDTO;
import pl.rstrzalkowski.syllabus.exception.CourseNotFoundException;
import pl.rstrzalkowski.syllabus.exception.RoleMismatchException;
import pl.rstrzalkowski.syllabus.exception.UserNotFoundException;
import pl.rstrzalkowski.syllabus.repository.CourseRepository;
import pl.rstrzalkowski.syllabus.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course getById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(CourseNotFoundException::new);
    }

    public Course create(CreateCourseDTO dto) {
        Course course = new Course();
        course.setName(dto.getName());
        course.setDescription(dto.getDescription());

        return courseRepository.save(course);
    }

    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    public void addStudentsToCourse(AddStudentsDTO dto) {
        Course course = getById(dto.getCourseId());
        dto.getStudentIds().forEach((id) -> {
            User user = userRepository.findById(id)
                    .orElseThrow(UserNotFoundException::new);
            if (user.getRole() != User.Role.STUDENT) {
                throw new RoleMismatchException();
            }
            course.addStudent(user);
        });
        courseRepository.save(course);
    }

    public void addTeachersToCourse(AddTeachersDTO dto) {
        Course course = getById(dto.getCourseId());
        dto.getTeacherIds().forEach((id) -> {
            User user = userRepository.findById(id)
                    .orElseThrow(UserNotFoundException::new);
            if (user.getRole() != User.Role.TEACHER) {
                throw new RoleMismatchException();
            }
            course.addTeacher(user);
        });
        courseRepository.save(course);
    }
}
