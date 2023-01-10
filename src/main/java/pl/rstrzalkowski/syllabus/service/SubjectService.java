package pl.rstrzalkowski.syllabus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rstrzalkowski.syllabus.repository.SubjectRepository;
import pl.rstrzalkowski.syllabus.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectService {

    private final SubjectRepository courseRepository;
    private final UserRepository userRepository;

//    public List<Course> getAllActive() {
//        return courseRepository.findAllByArchivedIsFalse();
//    }
//
//    public List<Course> getAllArchived() {
//        return courseRepository.findAllByArchivedIsTrue();
//    }
//
//    public Course getById(Long id) {
//        return courseRepository.findById(id)
//                .orElseThrow(CourseNotFoundException::new);
//    }
//
//    public Course create(CreateCourseDTO dto) {
//        Course course = new Course();
//        course.setName(dto.getName());
//        course.setDescription(dto.getDescription());
//        course.setAbbreviation(dto.getAbbreviation());
//
//        return courseRepository.save(course);
//    }
//
//    public Course update(Long id, UpdateCourseDTO dto) {
//        Course course = getById(id);
//        course.setName(dto.getName() == null ? course.getName() : dto.getName());
//        course.setDescription(dto.getDescription() == null ? course.getDescription() : dto.getDescription());
//        return courseRepository.save(course);
//    }
//
//    public void deleteById(Long id) {
//        try {
//            Course course = getById(id);
//            course.setArchived(true);
//            courseRepository.save(course);
//        } catch (CourseNotFoundException ignored) {
//        }
//    }
//
//    public Set<Student> getStudentsByCourseId(Long courseId) {
//        Course course = getById(courseId);
//        return course.getStudents();
//    }
//
//    public Set<Teacher> getTeachersByCourseId(Long courseId) {
//        Course course = getById(courseId);
//        return course.getTeachers();
//    }

}
