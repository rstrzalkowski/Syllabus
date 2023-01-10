package pl.rstrzalkowski.syllabus.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "SUBJECT_REALISATION")
public class SubjectRealisation extends AbstractEntity {
    @ManyToOne
    private Subject subject;

    @ManyToOne
    private SchoolClass schoolClass;

    @ManyToOne
    private User teacher;

    private Year year;

    @OneToMany(mappedBy = "subjectRealisation")
    private Set<Post> posts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "subjectRealisation")
    private Set<Activity> activities = new LinkedHashSet<>();
}
