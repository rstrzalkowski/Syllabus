package pl.rstrzalkowski.syllabus.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rstrzalkowski.syllabus.domain.model.RegistrationToken;

import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<RegistrationToken, UUID> {
}
