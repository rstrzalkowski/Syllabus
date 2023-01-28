package pl.rstrzalkowski.syllabus.realisation.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.rstrzalkowski.syllabus.activity.domain.model.Activity;
import pl.rstrzalkowski.syllabus.domain.AbstractEntity;
import pl.rstrzalkowski.syllabus.post.domain.model.Post;
import pl.rstrzalkowski.syllabus.schoolclass.domain.model.SchoolClass;
import pl.rstrzalkowski.syllabus.subject.domain.model.Subject;
import pl.rstrzalkowski.syllabus.user.domain.model.User;

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

    private Year year;

    private boolean archived;

    @OneToMany(mappedBy = "realisation")
    private Set<Post> posts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "realisation")
    private Set<Activity> activities = new LinkedHashSet<>();
}
