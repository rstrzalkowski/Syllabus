package pl.rstrzalkowski.syllabus.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.rstrzalkowski.syllabus.domain.model.RegistrationToken;
import pl.rstrzalkowski.syllabus.domain.model.Role;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {

    private UUID token;

    private Long userId;

    private Role role;

    private Timestamp createdAt;

    private String schoolClassName;

    public TokenDTO(RegistrationToken token) {
        this.token = token.getToken();
        this.role = token.getRole();
        this.createdAt = token.getCreatedAt();
        
        if (token.getUser() != null) {
            this.userId = token.getUser().getId();
        }

        if (token.getSchoolClass() != null) {
            this.schoolClassName = token.getSchoolClass().getSchoolClassName();
        }
    }
}
