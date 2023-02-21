package pl.rstrzalkowski.syllabus.application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.rstrzalkowski.syllabus.domain.model.Realisation;

import java.time.Year;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealisationDTO {
    private Long id;
    private String subjectName;
    private Long teacherId;
    private Year year;
    private String teacherFirstName;
    private String teacherLastName;
    private String schoolClassName;
    private String subjectAbbreviation;
    private String imageUrl;

    public RealisationDTO(Realisation realisation) {
        this.id = realisation.getId();
        this.subjectName = realisation.getSubject().getName();
        this.year = realisation.getYear();
        this.teacherId = realisation.getTeacher().getId();
        this.teacherFirstName = realisation.getTeacher().getFirstName();
        this.teacherLastName = realisation.getTeacher().getLastName();
        this.schoolClassName = realisation.getSchoolClass().getSchoolClassName();
        this.subjectAbbreviation = realisation.getSubject().getAbbreviation();
        this.imageUrl = realisation.getSubject().getImageUrl();
    }
}
