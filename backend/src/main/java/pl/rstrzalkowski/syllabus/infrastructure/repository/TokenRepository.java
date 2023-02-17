package pl.rstrzalkowski.syllabus.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rstrzalkowski.syllabus.domain.model.RegistrationToken;
import pl.rstrzalkowski.syllabus.domain.model.Role;

import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<RegistrationToken, UUID> {
    Page<RegistrationToken> findByRole(Role role, Pageable pageable);
}
