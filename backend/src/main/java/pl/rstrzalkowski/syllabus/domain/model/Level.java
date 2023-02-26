package pl.rstrzalkowski.syllabus.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "LEVEL")
public class Level extends AbstractEntity {

    @Column(name = "level_value")
    private Integer value;

    private boolean archived;

    @Transient
    private Integer activeSchoolClasses;
}
