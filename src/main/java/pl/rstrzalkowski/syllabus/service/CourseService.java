package pl.rstrzalkowski.syllabus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rstrzalkowski.syllabus.domain.Course;
import pl.rstrzalkowski.syllabus.domain.user.Student;
import pl.rstrzalkowski.syllabus.domain.user.Teacher;
import pl.rstrzalkowski.syllabus.dto.add.AddStudentsDTO;
import pl.rstrzalkowski.syllabus.dto.add.AddTeachersDTO;
import pl.rstrzalkowski.syllabus.dto.create.CreateCourseDTO;
import pl.rstrzalkowski.syllabus.dto.update.UpdateCourseDTO;
import pl.rstrzalkowski.syllabus.exception.course.CourseNotFoundException;
import pl.rstrzalkowski.syllabus.exception.user.RoleMismatchException;
import pl.rstrzalkowski.syllabus.exception.user.UserNotFoundException;
import pl.rstrzalkowski.syllabus.repository.CourseRepository;
import pl.rstrzalkowski.syllabus.repository.UserRepository;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public List<Course> getAllActive() {
        return courseRepository.findAllByArchivedIsFalse();
    }

    public List<Course> getAllArchived() {
        return courseRepository.findAllByArchivedIsTrue();
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

    public Course update(Long id, UpdateCourseDTO dto) {
        Course course = getById(id);
        course.setName(dto.getName() == null ? course.getName() : dto.getName());
        course.setDescription(dto.getDescription() == null ? course.getDescription() : dto.getDescription());
        return courseRepository.save(course);
    }

    public void deleteById(Long id) {
        try {
            Course course = getById(id);
            course.setArchived(true);
            courseRepository.save(course);
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

    public Set<Student> getStudentsByCourseId(Long courseId) {
        Course course = getById(courseId);
        return course.getStudents();
    }

    public Set<Teacher> getTeachersByCourseId(Long courseId) {
        Course course = getById(courseId);
        return course.getTeachers();
    }

}
