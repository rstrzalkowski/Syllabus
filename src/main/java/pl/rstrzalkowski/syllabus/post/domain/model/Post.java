package pl.rstrzalkowski.syllabus.post.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.rstrzalkowski.syllabus.domain.AbstractEntity;
import pl.rstrzalkowski.syllabus.realisation.domain.model.Realisation;
import pl.rstrzalkowski.syllabus.user.domain.model.User;

@Entity
@Getter
@Setter
@Table(name = "POST")
@NoArgsConstructor
public class Post extends AbstractEntity {

    @JsonIgnore
    @ManyToOne
    private Realisation realisation;

    @ManyToOne
    private User teacher;

    private String content;

    private boolean edited;

    private boolean archived;
}
