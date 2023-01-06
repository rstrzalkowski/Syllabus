package pl.rstrzalkowski.syllabus.domain.user;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
@Data
public class Admin extends User {
    public Admin(String email, String password) {
        super(email, password, Role.ADMIN);
    }
}
