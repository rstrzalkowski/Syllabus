package pl.rstrzalkowski.syllabus.user.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.rstrzalkowski.syllabus.domain.Role;
import pl.rstrzalkowski.syllabus.exception.user.UserNotFoundException;
import pl.rstrzalkowski.syllabus.user.domain.model.User;
import pl.rstrzalkowski.syllabus.user.domain.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final UserRepository userRepository;


    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public Page<User> getAllActiveUsers(Pageable pageable) {
        return userRepository.findByArchived(false, pageable);
    }

    public Page<User> getAllArchivedUsers(Pageable pageable) {
        return userRepository.findByArchived(false, pageable);
    }

    public Page<User> getByEmailContaining(String email, Pageable pageable) {
        return userRepository.findByEmail(email, pageable);
    }

    public Page<User> getAllActiveStudents(Pageable pageable) {
        return userRepository.findByRoleContainsAndArchived(Role.STUDENT, false, pageable);
    }

    public Page<User> getAllActiveTeachers(Pageable pageable) {
        return userRepository.findByRoleContainsAndArchived(Role.TEACHER, false, pageable);
    }

    public Page<User> getAllActiveOffices(Pageable pageable) {
        return userRepository.findByRoleContainsAndArchived(Role.OFFICE, false, pageable);
    }

    public Page<User> getAllActiveDirectors(Pageable pageable) {
        return userRepository.findByRoleContainsAndArchived(Role.DIRECTOR, false, pageable);
    }

    public Page<User> getAllArchiveStudents(Pageable pageable) {
        return userRepository.findByRoleContainsAndArchived(Role.STUDENT, true, pageable);
    }

    public Page<User> getAllArchiveTeachers(Pageable pageable) {
        return userRepository.findByRoleContainsAndArchived(Role.TEACHER, true, pageable);
    }

    public Page<User> getAllArchiveOffices(Pageable pageable) {
        return userRepository.findByRoleContainsAndArchived(Role.OFFICE, true, pageable);
    }

    public Page<User> getAllArchiveDirectors(Pageable pageable) {
        return userRepository.findByRoleContainsAndArchived(Role.DIRECTOR, true, pageable);
    }
}
