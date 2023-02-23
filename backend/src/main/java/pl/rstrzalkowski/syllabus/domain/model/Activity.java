package pl.rstrzalkowski.syllabus.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "ACTIVITY")
@NoArgsConstructor
public class Activity extends AbstractEntity {

    @JsonIgnore
    @ManyToOne
    private Realisation realisation;

    @ManyToOne
    private User teacher;

    private String name;

    private LocalDateTime date;

    private Integer weight;

    private String description;

    private boolean edited;

    private boolean archived;
}
