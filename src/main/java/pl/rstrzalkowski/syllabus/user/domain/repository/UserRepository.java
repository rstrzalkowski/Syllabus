package pl.rstrzalkowski.syllabus.user.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rstrzalkowski.syllabus.user.domain.model.Role;
import pl.rstrzalkowski.syllabus.user.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByRoleContainsAndArchived(Role role, boolean archived, Pageable pageable);

    Page<User> findByArchived(boolean archived, Pageable pageable);

    Page<User> findByEmail(String email, Pageable pageable);
}
