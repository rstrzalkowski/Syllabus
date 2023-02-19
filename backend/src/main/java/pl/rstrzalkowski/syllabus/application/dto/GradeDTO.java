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

    private Long realisationId;

    private Integer grade;

    private String comment;

    private Timestamp createdAt;

    public GradeDTO(Activity activity, Grade grade) {
        this.activityDTO = new ActivityDTO(activity);
        this.realisationId = activity.getRealisation().getId();
        if (grade != null) {
            this.grade = grade.getValue();
            this.createdAt = grade.getCreatedAt();
            this.comment = grade.getComment();
        }
    }
}
