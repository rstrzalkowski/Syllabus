package pl.rstrzalkowski.syllabus.domain.service.realisation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.rstrzalkowski.syllabus.application.dto.AverageGradeDTO;
import pl.rstrzalkowski.syllabus.application.dto.RealisationDTO;
import pl.rstrzalkowski.syllabus.application.dto.SubjectDTO;
import pl.rstrzalkowski.syllabus.domain.exception.realisation.RealisationNotFoundException;
import pl.rstrzalkowski.syllabus.domain.model.Grade;
import pl.rstrzalkowski.syllabus.domain.model.Realisation;
import pl.rstrzalkowski.syllabus.domain.model.Role;
import pl.rstrzalkowski.syllabus.domain.model.User;
import pl.rstrzalkowski.syllabus.infrastructure.repository.GradeRepository;
import pl.rstrzalkowski.syllabus.infrastructure.repository.RealisationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RealisationQueryService {

    private final RealisationRepository realisationRepository;
    private final GradeRepository gradeRepository;

    public Page<RealisationDTO> getAllActive(Pageable pageable) {
        return realisationRepository.findAllByArchived(false, pageable).map(RealisationDTO::new);
    }


    public Page<RealisationDTO> getAllArchived(Pageable pageable) {
        return realisationRepository.findAllByArchived(true, pageable).map(RealisationDTO::new);
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
        List<Grade> grades = gradeRepository.findAllByArchivedAndActivityArchivedAndActivityRealisationIdAndStudent(false, false, id, user);

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

    public List<SubjectDTO> getOwnActiveRealisations() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Realisation> realisations = new ArrayList<>();
        if (user.getRole() == Role.TEACHER) {
            realisations = realisationRepository.findAllByArchivedAndTeacherId(false, user.getId());
        } else if (user.getRole() == Role.STUDENT) {
            Long schoolClassId = user.getSchoolClass().getId();
            if (schoolClassId == null) {
                return List.of();
            }
            realisations = realisationRepository.findAllByArchivedAndSchoolClassId(false, schoolClassId);
        }
        return realisations.stream()
                .map(SubjectDTO::new)
                .collect(Collectors.toList());
    }

    public RealisationDTO getInfoById(Long id) {
        Realisation realisation = realisationRepository.findById(id)
                .orElseThrow(RealisationNotFoundException::new);
        return new RealisationDTO(realisation);
    }
}
