package pl.rstrzalkowski.syllabus.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.rstrzalkowski.syllabus.domain.user.User;

@Entity
@AllArgsConstructor
@Data
public class Teacher extends User {
    public Teacher(String email, String password) {
        super(email, password, Role.TEACHER);
    }
}
