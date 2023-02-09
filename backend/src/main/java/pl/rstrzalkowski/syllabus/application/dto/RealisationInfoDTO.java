package pl.rstrzalkowski.syllabus.application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealisationInfoDTO {
    private String subjectName;
    private Long teacherId;
    private String teacherFirstName;
    private String teacherLastName;
    private String schoolClassName;
    private String subjectAbbreviation;
}
