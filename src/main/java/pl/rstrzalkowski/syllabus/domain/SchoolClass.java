package pl.rstrzalkowski.syllabus.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "SCHOOL_CLASS")
public class SchoolClass extends AbstractEntity {

    @ManyToOne
    private ClassLevel classLevel;

    @ManyToOne
    private User supervisingTeacher;

    @OneToMany(mappedBy = "schoolClass")
    private Set<User> students = new LinkedHashSet<>();
}
