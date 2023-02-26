package pl.rstrzalkowski.syllabus.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "GRADE")
@NoArgsConstructor
public class Grade extends AbstractEntity {

    @Min(1)
    @Max(5)
    @Column(name = "grade_value")
    private Integer value;

    @ManyToOne
    private User student;

    @ManyToOne
    private User teacher;

    @ManyToOne
    private Activity activity;

    private String comment;

    private boolean edited;

    private boolean archived;
}
