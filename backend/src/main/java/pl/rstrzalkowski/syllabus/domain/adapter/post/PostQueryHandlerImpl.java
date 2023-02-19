package pl.rstrzalkowski.syllabus.domain.adapter.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.application.dto.PostDTO;
import pl.rstrzalkowski.syllabus.application.handler.post.PostQueryHandler;
import pl.rstrzalkowski.syllabus.application.query.post.GetActivePostsByRealisationQuery;
import pl.rstrzalkowski.syllabus.application.query.post.GetArchivedPostsByRealisationQuery;
import pl.rstrzalkowski.syllabus.application.query.post.GetPostByIdQuery;
import pl.rstrzalkowski.syllabus.application.query.post.GetRecentActivePosts;
import pl.rstrzalkowski.syllabus.domain.model.Post;
import pl.rstrzalkowski.syllabus.domain.service.post.PostQueryService;

@RequiredArgsConstructor
@Component
public class PostQueryHandlerImpl implements PostQueryHandler {

    private final PostQueryService postQueryService;

    @Override
    public Page<PostDTO> handle(GetActivePostsByRealisationQuery query) {
        return postQueryService.getAllActiveByRealisation(query.realisationId(), query.pageable());
    }

    @Override
    public Page<Post> handle(GetArchivedPostsByRealisationQuery query) {
        return postQueryService.getAllArchivedByRealisation(query.realisationId(), query.pageable());
    }

    @Override
    public Post handle(GetPostByIdQuery query) {
        return postQueryService.getById(query.id());
    }

    @Override
    public Page<PostDTO> handle(GetRecentActivePosts query) {
        return postQueryService.getRecentActivePosts(query.pageable());
    }
}
