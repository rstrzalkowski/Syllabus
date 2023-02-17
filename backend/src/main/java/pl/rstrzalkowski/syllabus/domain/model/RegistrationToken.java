package pl.rstrzalkowski.syllabus.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "TOKEN")
public class RegistrationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID token;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    private SchoolClass schoolClass;

    @OneToOne
    private User user;


    @CreationTimestamp
    private Timestamp createdAt;
}
