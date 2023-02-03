package pl.rstrzalkowski.syllabus.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rstrzalkowski.syllabus.domain.model.Role;
import pl.rstrzalkowski.syllabus.domain.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByRolesContainsAndArchived(Role role, boolean archived, Pageable pageable);

    Page<User> findByArchived(boolean archived, Pageable pageable);

    Page<User> findByUsername(String email, Pageable pageable);

    Optional<User> findByUsername(String email);
}