package pl.rstrzalkowski.syllabus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.rstrzalkowski.syllabus.domain.SchoolClass;
import pl.rstrzalkowski.syllabus.dto.create.CreateSchoolClassDTO;
import pl.rstrzalkowski.syllabus.dto.update.UpdateSchoolClassDTO;
import pl.rstrzalkowski.syllabus.exception.schoolClass.SchoolClassNotFoundException;
import pl.rstrzalkowski.syllabus.level.domain.model.Level;
import pl.rstrzalkowski.syllabus.level.domain.repository.LevelRepository;
import pl.rstrzalkowski.syllabus.repository.SchoolClassRepository;
import pl.rstrzalkowski.syllabus.user.domain.model.User;
import pl.rstrzalkowski.syllabus.user.domain.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SchoolClassService {

    private final SchoolClassRepository schoolClassRepository;
    private final LevelRepository levelRepository;
    private final UserRepository userRepository;


    public SchoolClass getById(Long id) {
        return schoolClassRepository.findById(id)
                .orElseThrow(SchoolClassNotFoundException::new);
    }

    public List<SchoolClass> getAllActive() {
        return schoolClassRepository.findAllByArchived(false);
    }

    public List<SchoolClass> getAllArchived() {
        return schoolClassRepository.findAllByArchived(true);
    }

    public SchoolClass create(CreateSchoolClassDTO dto) {
        SchoolClass schoolClass = new SchoolClass();

        Level level = levelRepository.findById(dto.getLevelId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        User teacher = userRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        schoolClass.setLevel(level);
        schoolClass.setSupervisingTeacher(teacher);

        return schoolClassRepository.save(schoolClass);
    }

    public SchoolClass update(Long id, UpdateSchoolClassDTO dto) {
        SchoolClass schoolClass = getById(id);

        if (dto.getLevelId() != null) {
            Level level = levelRepository.findById(dto.getLevelId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            schoolClass.setLevel(level);
        }

        if (dto.getTeacherId() != null) {
            User teacher = userRepository.findById(dto.getTeacherId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            schoolClass.setSupervisingTeacher(teacher);
        }
        return schoolClassRepository.save(schoolClass);
    }

    public void deleteById(Long id) {
        try {
            SchoolClass schoolClass = getById(id);
            schoolClass.setArchived(true);
            schoolClassRepository.save(schoolClass);
        } catch (SchoolClassNotFoundException ignored) {
        }
    }
}
