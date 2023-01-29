package pl.rstrzalkowski.syllabus.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "APP_USER")
@NoArgsConstructor
public class User extends AbstractEntity {

    boolean archived;
    @Column(unique = true)
    private String email;
    @ManyToOne
    private SchoolClass schoolClass;
    @JsonIgnore
    private String password;
    @ElementCollection
    @JoinTable(name = "APP_USER_ROLE", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Role> role;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
