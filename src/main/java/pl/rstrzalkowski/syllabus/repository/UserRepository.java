package pl.rstrzalkowski.syllabus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rstrzalkowski.syllabus.domain.Role;
import pl.rstrzalkowski.syllabus.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRoleContains(Role role);
}
