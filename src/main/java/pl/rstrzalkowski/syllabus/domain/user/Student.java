package pl.rstrzalkowski.syllabus.domain.user;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
@Data
public class Student extends User {
    public Student(String email, String password) {
        super(email, password, Role.STUDENT);
    }
}
