package pl.rstrzalkowski.syllabus.realisation.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.rstrzalkowski.syllabus.domain.SchoolClass;
import pl.rstrzalkowski.syllabus.realisation.application.command.ArchiveRealisationCommand;
import pl.rstrzalkowski.syllabus.realisation.application.command.CreateRealisationCommand;
import pl.rstrzalkowski.syllabus.realisation.application.command.UpdateRealisationCommand;
import pl.rstrzalkowski.syllabus.realisation.domain.exception.RealisationNotFoundException;
import pl.rstrzalkowski.syllabus.realisation.domain.exception.RealisationUpdateException;
import pl.rstrzalkowski.syllabus.realisation.domain.model.Realisation;
import pl.rstrzalkowski.syllabus.realisation.domain.repository.RealisationRepository;
import pl.rstrzalkowski.syllabus.repository.SchoolClassRepository;
import pl.rstrzalkowski.syllabus.subject.domain.model.Subject;
import pl.rstrzalkowski.syllabus.subject.domain.repository.SubjectRepository;
import pl.rstrzalkowski.syllabus.user.domain.model.User;
import pl.rstrzalkowski.syllabus.user.domain.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class RealisationCommandService {

    private final RealisationRepository realisationRepository;
    private final SubjectRepository subjectRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final UserRepository userRepository;


    public void create(CreateRealisationCommand command) {
        Realisation realisation = new Realisation();
        realisation.setYear(command.getYear());

        Subject subject = subjectRepository.findById(command.getSubjectId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        SchoolClass schoolClass = schoolClassRepository.findById(command.getClassId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        User teacher = userRepository.findById(command.getTeacherId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        realisation.setSubject(subject);
        realisation.setSchoolClass(schoolClass);
        realisation.setTeacher(teacher);

        realisationRepository.save(realisation);
    }


    public void archiveById(ArchiveRealisationCommand command) {
        Realisation realisation = realisationRepository.findById(command.id())
                .orElseThrow(RealisationNotFoundException::new);

        if (realisation.isArchived()) {
            return;
        }
        realisation.setArchived(true);
        realisationRepository.save(realisation);
    }


    public void update(UpdateRealisationCommand command) {
        Realisation realisation = realisationRepository.findById(command.getId())
                .orElseThrow(RealisationNotFoundException::new);

        if (command.getTeacherId() != null) {
            User teacher = userRepository.findById(command.getTeacherId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            realisation.setTeacher(teacher);
        }
        realisation.setYear(command.getYear() == null ? realisation.getYear() : command.getYear());

        try {
            realisationRepository.save(realisation);
        } catch (Exception e) {
            throw new RealisationUpdateException();
        }
    }
}
