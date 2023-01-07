package pl.rstrzalkowski.syllabus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rstrzalkowski.syllabus.domain.Course;
import pl.rstrzalkowski.syllabus.domain.user.Student;
import pl.rstrzalkowski.syllabus.domain.user.Teacher;
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
        try {
            Course course = getById(id);
            courseRepository.delete(course);
        } catch (CourseNotFoundException ignored) {
        }
    }

    public void addStudentsToCourse(Long id, AddStudentsDTO dto) {
        Course course = getById(id);
        dto.getStudentIds().forEach((studentId) -> {
            try {
                Student student = (Student) userRepository.findById(studentId)
                        .orElseThrow(UserNotFoundException::new);
                course.addStudent(student);
            } catch (ClassCastException e) {
                throw new RoleMismatchException();
            }
        });
        courseRepository.save(course);
    }

    public void addTeachersToCourse(Long id, AddTeachersDTO dto) {
        Course course = getById(id);
        dto.getTeacherIds().forEach((teacherId) -> {
            try {
                Teacher teacher = (Teacher) userRepository.findById(teacherId)
                        .orElseThrow(UserNotFoundException::new);
                course.addTeacher(teacher);
            } catch (ClassCastException e) {
                throw new RoleMismatchException();
            }
        });
        courseRepository.save(course);
    }
}
