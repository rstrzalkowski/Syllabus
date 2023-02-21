package pl.rstrzalkowski.syllabus.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rstrzalkowski.syllabus.domain.model.Role;
import pl.rstrzalkowski.syllabus.domain.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByRoleAndArchived(Role role, boolean archived, Pageable pageable);

    Page<User> findByArchivedAndRoleNot(boolean archived, Role role, Pageable pageable);

    Page<User> findByArchivedAndRoleAndSchoolClassIsNull(boolean archived, Role role, Pageable pageable);

    Page<User> findByEmail(String email, Pageable pageable);

    Page<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String keyword1, String keyword2, Pageable pageable);

    Optional<User> findByEmail(String email);
}
