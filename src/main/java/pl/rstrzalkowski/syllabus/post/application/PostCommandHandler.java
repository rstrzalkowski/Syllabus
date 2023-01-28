package pl.rstrzalkowski.syllabus.post.application;

import pl.rstrzalkowski.syllabus.post.application.command.ArchivePostCommand;
import pl.rstrzalkowski.syllabus.post.application.command.CreatePostCommand;
import pl.rstrzalkowski.syllabus.post.application.command.UpdatePostCommand;

public interface PostCommandHandler {
    void handle(CreatePostCommand command);

    void handle(UpdatePostCommand command);

    void handle(ArchivePostCommand command);
}
