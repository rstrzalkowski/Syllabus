package pl.rstrzalkowski.syllabus.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.rstrzalkowski.syllabus.domain.user.Student;
import pl.rstrzalkowski.syllabus.domain.user.Teacher;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "courses")
@NoArgsConstructor
public class Course extends AbstractEntity {

    private String name;

    private String description;

    private boolean archived;

    @ManyToMany(mappedBy = "courses")
    private Set<Teacher> teachers = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new LinkedHashSet<>();

    @OneToMany(mappedBy = "course")
    private Set<Post> posts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "course")
    private Set<Activity> activities = new LinkedHashSet<>();

    public void addStudent(Student student) {
        students.add(student);
        student.addCourse(this);
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
        teacher.addCourse(this);
    }
}
