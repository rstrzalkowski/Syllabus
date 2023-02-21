package pl.rstrzalkowski.syllabus.application.controller.post;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.application.command.post.ArchivePostCommand;
import pl.rstrzalkowski.syllabus.application.command.post.CreatePostCommand;
import pl.rstrzalkowski.syllabus.application.command.post.UpdatePostCommand;
import pl.rstrzalkowski.syllabus.application.handler.post.PostCommandHandler;
import pl.rstrzalkowski.syllabus.domain.shared.AccessGuard;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostCommandController {

    private final PostCommandHandler postCommandHandler;
    private final AccessGuard accessGuard;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Secured({"TEACHER", "OFFICE", "DIRECTOR", "ADMIN"})
    public void createPost(@Valid @RequestBody CreatePostCommand command) {
        accessGuard.checkAccessToRealisation(command.getRealisationId());
        postCommandHandler.handle(command);
    }

    @PutMapping("/{id}")
    @Secured({"TEACHER", "OFFICE", "DIRECTOR", "ADMIN"})
    public void updatePost(@PathVariable("id") Long id, @Valid @RequestBody UpdatePostCommand command) {
        accessGuard.checkAccessToPost(id);
        command.setId(id);
        postCommandHandler.handle(command);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Secured({"TEACHER", "OFFICE", "DIRECTOR", "ADMIN"})
    public void archiveById(@PathVariable("id") Long id) {
        accessGuard.checkAccessToPost(id);
        postCommandHandler.handle(new ArchivePostCommand(id));
    }
}
