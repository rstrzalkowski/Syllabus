package pl.rstrzalkowski.syllabus.application.handler.post;

import pl.rstrzalkowski.syllabus.application.command.post.ArchivePostCommand;
import pl.rstrzalkowski.syllabus.application.command.post.CreatePostCommand;
import pl.rstrzalkowski.syllabus.application.command.post.UpdatePostCommand;

public interface PostCommandHandler {
    void handle(CreatePostCommand command);

    void handle(UpdatePostCommand command);

    void handle(ArchivePostCommand command);
}
