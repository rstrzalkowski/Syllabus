package pl.rstrzalkowski.syllabus.application.handler.post;

import org.springframework.data.domain.Page;
import pl.rstrzalkowski.syllabus.application.dto.PostDTO;
import pl.rstrzalkowski.syllabus.application.query.post.GetActivePostsByRealisationQuery;
import pl.rstrzalkowski.syllabus.application.query.post.GetArchivedPostsByRealisationQuery;
import pl.rstrzalkowski.syllabus.application.query.post.GetPostByIdQuery;
import pl.rstrzalkowski.syllabus.application.query.post.GetRecentActivePosts;
import pl.rstrzalkowski.syllabus.domain.model.Post;

public interface PostQueryHandler {
    Page<PostDTO> handle(GetActivePostsByRealisationQuery query);

    Page<Post> handle(GetArchivedPostsByRealisationQuery query);

    Post handle(GetPostByIdQuery query);

    Page<PostDTO> handle(GetRecentActivePosts query);
}
