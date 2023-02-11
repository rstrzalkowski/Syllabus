package pl.rstrzalkowski.syllabus.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.rstrzalkowski.syllabus.domain.model.Activity;
import pl.rstrzalkowski.syllabus.domain.model.Grade;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeDTO {

    private ActivityDTO activityDTO;

    private Integer grade;

    private Timestamp createdAt;

    public GradeDTO(Activity activity, Grade grade) {
        this.activityDTO = new ActivityDTO(activity);
        if (grade != null) {
            this.grade = grade.getValue();
            this.createdAt = grade.getCreatedAt();
        }
    }
}
