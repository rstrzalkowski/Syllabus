package pl.rstrzalkowski.syllabus.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.rstrzalkowski.syllabus.domain.model.Activity;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDTO {

    private Long activityId;
    private Integer weight;
    private String name;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ActivityDTO(Activity activity) {
        this.activityId = activity.getId();
        this.weight = activity.getWeight();
        this.name = activity.getName();
        this.description = activity.getDescription();
        this.createdAt = activity.getCreatedAt();
        this.updatedAt = activity.getUpdatedAt();
    }
}
