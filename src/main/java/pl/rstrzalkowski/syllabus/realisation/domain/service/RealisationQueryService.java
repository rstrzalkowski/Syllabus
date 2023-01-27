package pl.rstrzalkowski.syllabus.realisation.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.rstrzalkowski.syllabus.realisation.domain.exception.RealisationNotFoundException;
import pl.rstrzalkowski.syllabus.realisation.domain.model.Realisation;
import pl.rstrzalkowski.syllabus.realisation.domain.repository.RealisationRepository;

@Service
@RequiredArgsConstructor
public class RealisationQueryService {

    private final RealisationRepository realisationRepository;

    public Page<Realisation> getAllActive(Pageable pageable) {
        return realisationRepository.findAllByArchived(false, pageable);
    }


    public Page<Realisation> getAllArchived(Pageable pageable) {
        return realisationRepository.findAllByArchived(true, pageable);
    }


    public Realisation getById(Long id) {
        return realisationRepository.findById(id)
                .orElseThrow(RealisationNotFoundException::new);
    }
}
