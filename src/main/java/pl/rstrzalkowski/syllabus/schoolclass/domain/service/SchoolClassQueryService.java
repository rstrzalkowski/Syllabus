package pl.rstrzalkowski.syllabus.schoolclass.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.rstrzalkowski.syllabus.schoolclass.domain.exception.SchoolClassNotFoundException;
import pl.rstrzalkowski.syllabus.schoolclass.domain.model.SchoolClass;
import pl.rstrzalkowski.syllabus.schoolclass.domain.repository.SchoolClassRepository;

@Service
@RequiredArgsConstructor
public class SchoolClassQueryService {

    private final SchoolClassRepository schoolClassRepository;

    public Page<SchoolClass> getAllActive(Pageable pageable) {
        return schoolClassRepository.findAllByArchived(false, pageable);
    }


    public Page<SchoolClass> getAllArchived(Pageable pageable) {
        return schoolClassRepository.findAllByArchived(true, pageable);
    }


    public SchoolClass getById(Long id) {
        return schoolClassRepository.findById(id)
                .orElseThrow(SchoolClassNotFoundException::new);
    }
}
