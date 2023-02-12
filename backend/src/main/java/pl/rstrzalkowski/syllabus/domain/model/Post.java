package pl.rstrzalkowski.syllabus.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(length = 40)
    private String title;

    @Column(length = 2000)
    private String content;

    private boolean edited;

    private boolean archived;

}
