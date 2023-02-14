package pl.rstrzalkowski.syllabus.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.rstrzalkowski.syllabus.domain.model.User;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeOfActivityDTO {

    private String studentFirstName;

    private String studentLastName;

    private String studentPersonalId;

    private Long studentId;

    private Long activityId;

    private Integer grade;

    private String comment;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public GradeOfActivityDTO(User student, Long activityId) {
        this.studentId = student.getId();
        this.studentFirstName = student.getFirstName();
        this.studentLastName = student.getLastName();
        this.studentPersonalId = student.getPersonalId();
        this.activityId = activityId;
    }
}
