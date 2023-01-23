package pl.rstrzalkowski.syllabus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rstrzalkowski.syllabus.domain.Subject;
import pl.rstrzalkowski.syllabus.dto.create.CreateSubjectDTO;
import pl.rstrzalkowski.syllabus.dto.update.UpdateSubjectDTO;
import pl.rstrzalkowski.syllabus.exception.subject.SubjectNotFoundException;
import pl.rstrzalkowski.syllabus.repository.SubjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public List<Subject> getAllActive() {
        return subjectRepository.findAllByArchivedIsFalse();
    }


    public List<Subject> getAllArchived() {
        return subjectRepository.findAllByArchivedIsTrue();
    }

    public Subject getById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(SubjectNotFoundException::new);
    }

    public Subject create(CreateSubjectDTO dto) {
        Subject subject = new Subject();
        subject.setName(dto.getName());
        subject.setAbbreviation(dto.getAbbreviation());

        return subjectRepository.save(subject);
    }

    public Subject update(Long id, UpdateSubjectDTO dto) {
        Subject subject = getById(id);
        subject.setName(dto.getName() == null ? subject.getName() : dto.getName());
        subject.setAbbreviation(dto.getAbbreviation() == null ? subject.getAbbreviation() : dto.getAbbreviation());
        return subjectRepository.save(subject);
    }

    public void deleteById(Long id) {
        try {
            Subject subject = getById(id);
            subject.setArchived(true);
            subjectRepository.save(subject);
        } catch (SubjectNotFoundException ignored) {
        }
    }

}
