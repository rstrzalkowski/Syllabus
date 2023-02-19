package pl.rstrzalkowski.syllabus.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.rstrzalkowski.syllabus.domain.model.Activity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDTO {

    private Long activityId;
    private Integer weight;
    private String name;
    private String description;
    private LocalDateTime date;
    private String subjectName;
    private Long realisationId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ActivityDTO(Activity activity) {
        this.activityId = activity.getId();
        this.weight = activity.getWeight();
        this.name = activity.getName();
        this.description = activity.getDescription();
        this.date = activity.getDate();
        this.createdAt = activity.getCreatedAt();
        this.updatedAt = activity.getUpdatedAt();
        this.subjectName = activity.getRealisation().getSubject().getName();
        this.realisationId = activity.getRealisation().getId();
    }
}
