package pl.rstrzalkowski.syllabus.level.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.rstrzalkowski.syllabus.shared.AbstractEntity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "LEVEL")
public class Level extends AbstractEntity {
    private Integer level;

    private boolean archived;
}
