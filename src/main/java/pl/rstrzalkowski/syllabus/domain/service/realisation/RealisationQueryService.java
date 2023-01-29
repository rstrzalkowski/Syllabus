package pl.rstrzalkowski.syllabus.domain.service.realisation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.rstrzalkowski.syllabus.domain.exception.realisation.RealisationNotFoundException;
import pl.rstrzalkowski.syllabus.domain.model.Realisation;
import pl.rstrzalkowski.syllabus.domain.repository.RealisationRepository;

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
