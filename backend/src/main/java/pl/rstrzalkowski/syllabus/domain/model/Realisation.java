package pl.rstrzalkowski.syllabus.domain.model;

import jakarta.persistence.Column;
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
@Table(name = "REALISATION")
public class Realisation extends AbstractEntity {
    @ManyToOne
    private Subject subject;

    @ManyToOne
    private SchoolClass schoolClass;

    @ManyToOne
    private User teacher;

    @Column(name = "realisation_year")
    private Year year;

    private boolean archived;

    @OneToMany(mappedBy = "realisation")
    private Set<Post> posts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "realisation")
    private Set<Activity> activities = new LinkedHashSet<>();
}
