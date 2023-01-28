package pl.rstrzalkowski.syllabus.grade.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.rstrzalkowski.syllabus.activity.domain.model.Activity;
import pl.rstrzalkowski.syllabus.shared.AbstractEntity;
import pl.rstrzalkowski.syllabus.user.domain.model.User;

@Entity
@Getter
@Setter
@Table(name = "GRADE")
@NoArgsConstructor
public class Grade extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    @Max(5)
    private Integer value;

    @ManyToOne
    private User student;

    @ManyToOne
    private User teacher;

    @ManyToOne
    private Activity activity;

    private boolean edited;

    private boolean archived;
}