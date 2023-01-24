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
    private Level level;

    @ManyToOne
    private User supervisingTeacher;

    private boolean archived;

    @OneToMany(mappedBy = "schoolClass")
    private Set<User> students = new LinkedHashSet<>();
}
