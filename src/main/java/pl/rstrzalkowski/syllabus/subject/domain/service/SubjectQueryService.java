package pl.rstrzalkowski.syllabus.subject.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.rstrzalkowski.syllabus.subject.domain.exception.SubjectNotFoundException;
import pl.rstrzalkowski.syllabus.subject.domain.model.Subject;
import pl.rstrzalkowski.syllabus.subject.domain.repository.SubjectRepository;

@Service
@RequiredArgsConstructor
public class SubjectQueryService {

    private final SubjectRepository subjectRepository;

    public Page<Subject> getAllActive(Pageable pageable) {
        return subjectRepository.findAllByArchived(false, pageable);
    }


    public Page<Subject> getAllArchived(Pageable pageable) {
        return subjectRepository.findAllByArchived(true, pageable);
    }


    public Page<Subject> getByNameContaining(String name, Pageable pageable) {
        return subjectRepository.findSubjectByNameContainingIgnoreCase(name, pageable);
    }


    public Subject getById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(SubjectNotFoundException::new);
    }
}
