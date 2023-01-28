package pl.rstrzalkowski.syllabus.schoolclass.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.rstrzalkowski.syllabus.level.domain.model.Level;
import pl.rstrzalkowski.syllabus.level.domain.repository.LevelRepository;
import pl.rstrzalkowski.syllabus.schoolclass.application.command.ArchiveSchoolClassCommand;
import pl.rstrzalkowski.syllabus.schoolclass.application.command.CreateSchoolClassCommand;
import pl.rstrzalkowski.syllabus.schoolclass.application.command.UpdateSchoolClassCommand;
import pl.rstrzalkowski.syllabus.schoolclass.domain.exception.SchoolClassNotFoundException;
import pl.rstrzalkowski.syllabus.schoolclass.domain.model.SchoolClass;
import pl.rstrzalkowski.syllabus.schoolclass.domain.repository.SchoolClassRepository;
import pl.rstrzalkowski.syllabus.user.domain.model.User;
import pl.rstrzalkowski.syllabus.user.domain.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class SchoolClassCommandService {

    private final SchoolClassRepository schoolClassRepository;
    private final LevelRepository levelRepository;
    private final UserRepository userRepository;


    public SchoolClass create(CreateSchoolClassCommand command) {
        SchoolClass schoolClass = new SchoolClass();

        Level level = levelRepository.findById(command.getLevelId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        User teacher = userRepository.findById(command.getTeacherId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        schoolClass.setLevel(level);
        schoolClass.setSupervisingTeacher(teacher);

        return schoolClassRepository.save(schoolClass);
    }

    public SchoolClass update(UpdateSchoolClassCommand command) {
        SchoolClass schoolClass = schoolClassRepository.findById(command.getId())
                .orElseThrow(SchoolClassNotFoundException::new);

        if (command.getLevelId() != null) {
            Level level = levelRepository.findById(command.getLevelId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            schoolClass.setLevel(level);
        }

        if (command.getTeacherId() != null) {
            User teacher = userRepository.findById(command.getTeacherId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            schoolClass.setSupervisingTeacher(teacher);
        }
        return schoolClassRepository.save(schoolClass);
    }

    public void archiveById(ArchiveSchoolClassCommand command) {
        SchoolClass schoolClass = schoolClassRepository.findById(command.id())
                .orElseThrow(SchoolClassNotFoundException::new);

        schoolClass.setArchived(true);
        schoolClassRepository.save(schoolClass);
    }
}
