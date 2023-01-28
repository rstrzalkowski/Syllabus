package pl.rstrzalkowski.syllabus.post.infrastructure;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.post.application.PostCommandHandler;
import pl.rstrzalkowski.syllabus.post.application.command.ArchivePostCommand;
import pl.rstrzalkowski.syllabus.post.application.command.CreatePostCommand;
import pl.rstrzalkowski.syllabus.post.application.command.UpdatePostCommand;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostCommandController {

    private final PostCommandHandler postCommandHandler;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createPost(@Valid @RequestBody CreatePostCommand command) {
        postCommandHandler.handle(command);
    }

    @PutMapping
    public void updatePost(@Valid @RequestBody UpdatePostCommand command) {
        postCommandHandler.handle(command);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void archiveById(@PathVariable("id") Long id) {
        postCommandHandler.handle(new ArchivePostCommand(id));
    }
}
