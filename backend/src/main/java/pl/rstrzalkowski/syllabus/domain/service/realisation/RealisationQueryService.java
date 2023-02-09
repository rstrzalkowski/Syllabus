package pl.rstrzalkowski.syllabus.domain.service.realisation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.rstrzalkowski.syllabus.application.dto.AverageGradeDTO;
import pl.rstrzalkowski.syllabus.application.dto.RealisedSubjectDTO;
import pl.rstrzalkowski.syllabus.domain.exception.realisation.RealisationNotFoundException;
import pl.rstrzalkowski.syllabus.domain.exception.realisation.StudentNotInRealisationException;
import pl.rstrzalkowski.syllabus.domain.model.Grade;
import pl.rstrzalkowski.syllabus.domain.model.Realisation;
import pl.rstrzalkowski.syllabus.domain.model.Role;
import pl.rstrzalkowski.syllabus.domain.model.User;
import pl.rstrzalkowski.syllabus.infrastructure.repository.GradeRepository;
import pl.rstrzalkowski.syllabus.infrastructure.repository.RealisationRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Realisation realisation = realisationRepository.findById(id)
                .orElseThrow(RealisationNotFoundException::new);
        if (!Objects.equals(user.getSchoolClass().getId(), realisation.getSchoolClass().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return realisation;
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

    public List<RealisedSubjectDTO> getOwnActiveRealisations() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getRole() == Role.TEACHER) {
            List<Realisation> activeRealisationsBySchoolClass = realisationRepository.findAllByArchivedAndTeacherId(false, user.getId());
            return activeRealisationsBySchoolClass
                    .stream()
                    .map((realisation -> new RealisedSubjectDTO(realisation.getId(), realisation.getSubject().getName())))
                    .collect(Collectors.toList());
        } else {
            Long schoolClassId = user.getSchoolClass().getId();
            if (schoolClassId == null) {
                return List.of();
            }

            List<Realisation> activeRealisationsBySchoolClass = realisationRepository.findAllByArchivedAndSchoolClassId(false, schoolClassId);
            return activeRealisationsBySchoolClass
                    .stream()
                    .map((realisation -> new RealisedSubjectDTO(realisation.getId(), realisation.getSubject().getName())))
                    .collect(Collectors.toList());
        }
    }
}
