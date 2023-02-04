package pl.rstrzalkowski.syllabus.domain.adapter.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.application.command.post.ArchivePostCommand;
import pl.rstrzalkowski.syllabus.application.command.post.CreatePostCommand;
import pl.rstrzalkowski.syllabus.application.command.post.UpdatePostCommand;
import pl.rstrzalkowski.syllabus.application.handler.post.PostCommandHandler;
import pl.rstrzalkowski.syllabus.domain.service.post.PostCommandService;

@RequiredArgsConstructor
@Component
public class PostCommandHandlerImpl implements PostCommandHandler {

    private final PostCommandService postCommandService;

    @Override
    public void handle(CreatePostCommand command) {
        postCommandService.create(command);
    }

    @Override
    public void handle(UpdatePostCommand command) {
        postCommandService.update(command);
    }

    @Override
    public void handle(ArchivePostCommand command) {
        postCommandService.archiveById(command);
    }

}
