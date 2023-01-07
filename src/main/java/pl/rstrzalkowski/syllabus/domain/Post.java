package pl.rstrzalkowski.syllabus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.rstrzalkowski.syllabus.domain.user.Teacher;

@Entity
@Getter
@Setter
@Table(name = "posts")
@NoArgsConstructor
public class Post extends AbstractEntity {

    @JsonIgnore
    @ManyToOne
    private Course course;

    @ManyToOne
    private Teacher teacher;

    private String content;
}
