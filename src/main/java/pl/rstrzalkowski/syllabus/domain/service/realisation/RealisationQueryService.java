package pl.rstrzalkowski.syllabus.domain.service.realisation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.rstrzalkowski.syllabus.domain.dto.AverageGradeDTO;
import pl.rstrzalkowski.syllabus.domain.exception.realisation.RealisationNotFoundException;
import pl.rstrzalkowski.syllabus.domain.exception.realisation.StudentNotInRealisationException;
import pl.rstrzalkowski.syllabus.domain.model.Grade;
import pl.rstrzalkowski.syllabus.domain.model.Realisation;
import pl.rstrzalkowski.syllabus.domain.model.User;
import pl.rstrzalkowski.syllabus.domain.repository.GradeRepository;
import pl.rstrzalkowski.syllabus.domain.repository.RealisationRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RealisationQueryService {

    private final RealisationRepository realisationRepository;
    private final GradeRepository gradeRepository;

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

    public AverageGradeDTO getRealisationAverageGrade(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Grade> grades = gradeRepository.findAllByActivityRealisationIdAndStudent(id, user);

        Realisation realisation = getById(id);
        if (!Objects.equals(realisation.getSchoolClass().getId(), user.getSchoolClass().getId())) {
            throw new StudentNotInRealisationException();
        }

        double sum = 0;
        double weights = 0;
        for (Grade grade : grades) {
            sum += (grade.getValue() * grade.getActivity().getWeight());
            weights += grade.getActivity().getWeight();
        }
        if (grades.size() == 0) {
            return new AverageGradeDTO(0.0);
        }
        return new AverageGradeDTO(sum / weights);
    }
}
