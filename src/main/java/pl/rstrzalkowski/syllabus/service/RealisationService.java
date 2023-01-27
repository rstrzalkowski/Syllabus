package pl.rstrzalkowski.syllabus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.rstrzalkowski.syllabus.domain.Realisation;
import pl.rstrzalkowski.syllabus.domain.SchoolClass;
import pl.rstrzalkowski.syllabus.domain.User;
import pl.rstrzalkowski.syllabus.dto.create.CreateRealisationDTO;
import pl.rstrzalkowski.syllabus.dto.update.UpdateRealisationDTO;
import pl.rstrzalkowski.syllabus.exception.realisation.RealisationNotFoundException;
import pl.rstrzalkowski.syllabus.repository.RealisationRepository;
import pl.rstrzalkowski.syllabus.repository.SchoolClassRepository;
import pl.rstrzalkowski.syllabus.repository.UserRepository;
import pl.rstrzalkowski.syllabus.subject.domain.model.Subject;
import pl.rstrzalkowski.syllabus.subject.domain.repository.SubjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RealisationService {

    private final RealisationRepository realisationRepository;
    private final SubjectRepository subjectRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final UserRepository userRepository;


    public List<Realisation> getAllActive() {
        return realisationRepository.findAll();
    }

    public List<Realisation> getAllArchived() {
        return realisationRepository.findAll();
    }


    public Realisation getById(Long id) {
        return realisationRepository.findById(id)
                .orElseThrow(RealisationNotFoundException::new);
    }

    public Realisation create(CreateRealisationDTO dto) {
        Realisation realisation = new Realisation();
        realisation.setYear(dto.getYear());

        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        SchoolClass schoolClass = schoolClassRepository.findById(dto.getClassId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        User teacher = userRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        realisation.setSubject(subject);
        realisation.setSchoolClass(schoolClass);
        realisation.setTeacher(teacher);

        return realisationRepository.save(realisation);
    }

    public Realisation update(Long id, UpdateRealisationDTO dto) {
        Realisation realisation = getById(id);

        if (dto.getTeacherId() != null) {
            User teacher = userRepository.findById(dto.getTeacherId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            realisation.setTeacher(teacher);
        }
        realisation.setYear(dto.getYear() == null ? realisation.getYear() : dto.getYear());

        return realisationRepository.save(realisation);
    }

    public void deleteById(Long id) {
        try {
            Realisation realisation = getById(id);
            realisation.setArchived(true);
            realisationRepository.save(realisation);
        } catch (RealisationNotFoundException ignored) {
        }
    }

}
