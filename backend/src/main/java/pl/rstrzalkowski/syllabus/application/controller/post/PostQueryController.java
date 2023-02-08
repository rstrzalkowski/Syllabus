package pl.rstrzalkowski.syllabus.application.controller.post;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.application.handler.post.PostQueryHandler;
import pl.rstrzalkowski.syllabus.application.query.post.GetPostByIdQuery;
import pl.rstrzalkowski.syllabus.domain.model.Post;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostQueryController {

    private final PostQueryHandler postQueryHandler;

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable("id") Long id) {
        return postQueryHandler.handle(new GetPostByIdQuery(id));
    }
}
