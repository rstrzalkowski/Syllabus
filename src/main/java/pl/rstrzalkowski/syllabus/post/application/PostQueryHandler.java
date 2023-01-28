package pl.rstrzalkowski.syllabus.post.application;

import org.springframework.data.domain.Page;
import pl.rstrzalkowski.syllabus.post.application.query.GetActivePostsByRealisationQuery;
import pl.rstrzalkowski.syllabus.post.application.query.GetArchivedPostsByRealisationQuery;
import pl.rstrzalkowski.syllabus.post.application.query.GetPostByIdQuery;
import pl.rstrzalkowski.syllabus.post.domain.model.Post;

public interface PostQueryHandler {
    Page<Post> handle(GetActivePostsByRealisationQuery query);

    Page<Post> handle(GetArchivedPostsByRealisationQuery query);

    Post handle(GetPostByIdQuery query);
}
