package pl.rstrzalkowski.syllabus.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "courses")
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany
    private Set<User> teachers = new HashSet<>();

    @OneToMany
    private Set<User> students = new HashSet<>();

    public void addStudent(User user) {
        if (!students.contains(user)) {
            students.add(user);
        }
    }

    public void addTeacher(User user) {
        if (!teachers.contains(user)) {
            teachers.add(user);
        }
    }
}
