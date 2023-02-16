package pl.rstrzalkowski.syllabus.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String name;

    private String fullName;

    @ManyToOne
    private User supervisingTeacher;

    private boolean archived;

    @OneToMany(mappedBy = "schoolClass")
    private Set<User> students = new LinkedHashSet<>();

    @JsonIgnore
    public String getSchoolClassName() {
        return level.getValue() + " " + name;
    }
}
