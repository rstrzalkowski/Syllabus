package pl.rstrzalkowski.syllabus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.rstrzalkowski.syllabus.domain.Activity;
import pl.rstrzalkowski.syllabus.domain.Grade;
import pl.rstrzalkowski.syllabus.domain.user.Student;
import pl.rstrzalkowski.syllabus.domain.user.Teacher;
import pl.rstrzalkowski.syllabus.dto.create.CreateGradeDTO;
import pl.rstrzalkowski.syllabus.dto.update.UpdateGradeDTO;
import pl.rstrzalkowski.syllabus.exception.grade.GradeNotFoundException;
import pl.rstrzalkowski.syllabus.exception.post.PostNotFoundException;
import pl.rstrzalkowski.syllabus.exception.user.RoleMismatchException;
import pl.rstrzalkowski.syllabus.repository.ActivityRepository;
import pl.rstrzalkowski.syllabus.repository.GradeRepository;
import pl.rstrzalkowski.syllabus.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GradeService {

    private final GradeRepository gradeRepository;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;


    public List<Grade> getAll() {
        return gradeRepository.findAll();
    }

    public Grade getById(Long id) {
        return gradeRepository.findById(id)
                .orElseThrow(GradeNotFoundException::new);
    }

    public List<Grade> getStudentActivityGrades(Long activityId, Long studentId) {
        return gradeRepository.findByActivityIdAndStudentId(activityId, studentId);
    }

    public List<Grade> getStudentCourseGrades(Long courseId, Long studentId) {
        return gradeRepository.findByActivityCourseIdAndStudentId(courseId, studentId);
    }

    public List<Grade> getActivityGrades(Long activityId) {
        return gradeRepository.findByActivityId(activityId);
    }

    public Grade create(Long activityId, CreateGradeDTO dto) {
        Grade grade = new Grade();
        grade.setValue(dto.getValue());

        Activity activity;
        Student student;
        Teacher teacher;
        try {
            activity = activityRepository.findById(activityId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            student = (Student) userRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            teacher = (Teacher) userRepository.findById(dto.getTeacherId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        } catch (ClassCastException e) {
            throw new RoleMismatchException();
        }
        grade.setActivity(activity);
        grade.setStudent(student);
        grade.setTeacher(teacher);
        return gradeRepository.save(grade);
    }

    public Grade update(Long id, UpdateGradeDTO dto) {
        Grade grade = getById(id);
        grade.setValue(dto.getValue() == null ? grade.getValue() : dto.getValue());
        return gradeRepository.save(grade);
    }

    public void deleteById(Long id) {
        try {
            Grade grade = getById(id);
            gradeRepository.delete(grade);
        } catch (PostNotFoundException ignored) {
        }
    }
}
