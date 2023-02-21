package pl.rstrzalkowski.syllabus.domain.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.rstrzalkowski.syllabus.application.dto.TokenDTO;
import pl.rstrzalkowski.syllabus.domain.exception.user.UserNotFoundException;
import pl.rstrzalkowski.syllabus.domain.model.Role;
import pl.rstrzalkowski.syllabus.domain.model.SchoolClass;
import pl.rstrzalkowski.syllabus.domain.model.User;
import pl.rstrzalkowski.syllabus.infrastructure.repository.SchoolClassRepository;
import pl.rstrzalkowski.syllabus.infrastructure.repository.TokenRepository;
import pl.rstrzalkowski.syllabus.infrastructure.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final SchoolClassRepository schoolClassRepository;


    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public User getLoggedInUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Page<User> getAllActiveUsers(Pageable pageable) {
        return userRepository.findByArchivedAndRoleNot(false, Role.ADMIN, pageable);
    }

    public Page<User> getAllArchivedUsers(Pageable pageable) {
        return userRepository.findByArchivedAndRoleNot(true, Role.ADMIN, pageable);
    }

    public Page<User> getByKeywordContaining(String keyword, Pageable pageable) {
        return userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(keyword, keyword, pageable);
    }

    public Page<User> getAllActiveStudents(Pageable pageable) {
        return userRepository.findByRoleAndArchived(Role.STUDENT, false, pageable);
    }

    public Page<User> getAllActiveTeachers(Pageable pageable) {
        return userRepository.findByRoleAndArchived(Role.TEACHER, false, pageable);
    }

    public Page<User> getAllActiveOffices(Pageable pageable) {
        return userRepository.findByRoleAndArchived(Role.OFFICE, false, pageable);
    }

    public Page<User> getAllActiveDirectors(Pageable pageable) {
        return userRepository.findByRoleAndArchived(Role.DIRECTOR, false, pageable);
    }

    public Page<User> getAllArchiveStudents(Pageable pageable) {
        return userRepository.findByRoleAndArchived(Role.STUDENT, true, pageable);
    }

    public Page<User> getAllArchiveTeachers(Pageable pageable) {
        return userRepository.findByRoleAndArchived(Role.TEACHER, true, pageable);
    }

    public Page<User> getAllArchiveOffices(Pageable pageable) {
        return userRepository.findByRoleAndArchived(Role.OFFICE, true, pageable);
    }

    public Page<User> getAllArchiveDirectors(Pageable pageable) {
        return userRepository.findByRoleAndArchived(Role.DIRECTOR, true, pageable);
    }

    public List<User> getNotSupervisingActiveTeachers(Pageable pageable) {
        Page<User> teachers = getAllActiveTeachers(pageable);
        List<SchoolClass> classes = schoolClassRepository.findAllByArchived(false);
        return teachers.stream().filter(teacher -> classes.stream().noneMatch(schoolClass -> Objects.equals(schoolClass.getSupervisingTeacher().getId(), teacher.getId()))).collect(Collectors.toList());
    }

    public Page<TokenDTO> getTokensByRole(Role role, Pageable pageable) {
        return tokenRepository.findByRole(role, pageable).map(TokenDTO::new);
    }

    public Page<User> getUnassignedStudents(Pageable pageable) {
        return userRepository.findByArchivedAndRoleAndSchoolClassIsNull(false, Role.STUDENT, pageable);
    }
}
