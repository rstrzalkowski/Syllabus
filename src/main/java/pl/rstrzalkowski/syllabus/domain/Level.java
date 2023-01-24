package pl.rstrzalkowski.syllabus.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private Integer level;

    private boolean archived;
}
