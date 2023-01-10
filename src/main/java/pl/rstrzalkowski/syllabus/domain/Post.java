package pl.rstrzalkowski.syllabus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private SubjectRealisation subjectRealisation;

    @ManyToOne
    private User teacher;

    private String content;
}
