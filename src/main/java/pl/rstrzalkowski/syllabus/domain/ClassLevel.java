package pl.rstrzalkowski.syllabus.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "CLASS_LEVEL")
public class ClassLevel extends AbstractEntity {
    private Integer level;
}
