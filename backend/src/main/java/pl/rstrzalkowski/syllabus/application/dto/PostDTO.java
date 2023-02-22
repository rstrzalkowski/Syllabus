package pl.rstrzalkowski.syllabus.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.rstrzalkowski.syllabus.domain.model.Post;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Long postId;
    private Long authorId;
    private Long realisationId;
    private String title;
    private String content;
    private String authorFirstName;
    private String authorLastName;
    private String authorImageUrl;
    private String subjectName;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private boolean edited;

    public PostDTO(Post post) {
        this.postId = post.getId();
        this.authorId = post.getTeacher().getId();
        this.content = post.getContent();
        this.authorFirstName = post.getTeacher().getFirstName();
        this.authorLastName = post.getTeacher().getLastName();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.edited = post.isEdited();
        this.title = post.getTitle();
        this.realisationId = post.getRealisation().getId();
        this.subjectName = post.getRealisation().getSubject().getName();
        this.authorImageUrl = post.getTeacher().getImageUrl();
    }
}
