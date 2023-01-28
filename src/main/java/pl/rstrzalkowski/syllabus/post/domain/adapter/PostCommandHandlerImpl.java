package pl.rstrzalkowski.syllabus.post.domain.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.post.application.PostCommandHandler;
import pl.rstrzalkowski.syllabus.post.application.command.ArchivePostCommand;
import pl.rstrzalkowski.syllabus.post.application.command.CreatePostCommand;
import pl.rstrzalkowski.syllabus.post.application.command.UpdatePostCommand;
import pl.rstrzalkowski.syllabus.post.domain.service.PostCommandService;

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
