package pl.rstrzalkowski.syllabus.post.domain.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.post.application.PostQueryHandler;
import pl.rstrzalkowski.syllabus.post.application.query.GetActivePostsByRealisationQuery;
import pl.rstrzalkowski.syllabus.post.application.query.GetArchivedPostsByRealisationQuery;
import pl.rstrzalkowski.syllabus.post.application.query.GetPostByIdQuery;
import pl.rstrzalkowski.syllabus.post.domain.model.Post;
import pl.rstrzalkowski.syllabus.post.domain.service.PostQueryService;

@RequiredArgsConstructor
@Component
public class PostQueryHandlerImpl implements PostQueryHandler {

    private final PostQueryService postQueryService;

    @Override
    public Page<Post> handle(GetActivePostsByRealisationQuery query) {
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
}
