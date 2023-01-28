package pl.rstrzalkowski.syllabus.grade.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.rstrzalkowski.syllabus.activity.domain.model.Activity;
import pl.rstrzalkowski.syllabus.activity.domain.repository.ActivityRepository;
import pl.rstrzalkowski.syllabus.grade.application.command.ArchiveGradeCommand;
import pl.rstrzalkowski.syllabus.grade.application.command.CreateGradeCommand;
import pl.rstrzalkowski.syllabus.grade.application.command.UpdateGradeCommand;
import pl.rstrzalkowski.syllabus.grade.domain.exception.GradeNotFoundException;
import pl.rstrzalkowski.syllabus.grade.domain.model.Grade;
import pl.rstrzalkowski.syllabus.grade.domain.repository.GradeRepository;
import pl.rstrzalkowski.syllabus.user.domain.model.User;
import pl.rstrzalkowski.syllabus.user.domain.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class GradeCommandService {

    private final GradeRepository gradeRepository;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;


    public Grade create(CreateGradeCommand command) {
        Grade grade = new Grade();
        grade.setValue(command.getValue());

        Activity activity = activityRepository.findById(command.getActivityId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        User student = userRepository.findById(command.getStudentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        User teacher = userRepository.findById(command.getTeacherId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        grade.setActivity(activity);
        grade.setStudent(student);
        grade.setTeacher(teacher);

        return gradeRepository.save(grade);
    }

    public Grade update(UpdateGradeCommand command) {
        Grade grade = gradeRepository.findById(command.getGradeId())
                .orElseThrow(GradeNotFoundException::new);

        grade.setValue(command.getValue() == null ? grade.getValue() : command.getValue());
        grade.setEdited(true);

        return gradeRepository.save(grade);
    }

    public void archiveById(ArchiveGradeCommand command) {
        Grade grade = gradeRepository.findById(command.id())
                .orElseThrow(GradeNotFoundException::new);

        grade.setArchived(true);
        gradeRepository.save(grade);
    }
}
