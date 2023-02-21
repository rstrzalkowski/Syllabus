package pl.rstrzalkowski.syllabus.application.controller.user;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.rstrzalkowski.syllabus.application.command.user.ArchiveUserCommand;
import pl.rstrzalkowski.syllabus.application.command.user.AssignCommand;
import pl.rstrzalkowski.syllabus.application.command.user.ChangePasswordCommand;
import pl.rstrzalkowski.syllabus.application.command.user.GenerateRegistrationTokensCommand;
import pl.rstrzalkowski.syllabus.application.command.user.UnassignCommand;
import pl.rstrzalkowski.syllabus.application.command.user.UpdateDescriptionCommand;
import pl.rstrzalkowski.syllabus.application.command.user.UpdateProfileImageCommand;
import pl.rstrzalkowski.syllabus.application.handler.user.UserCommandHandler;
import pl.rstrzalkowski.syllabus.domain.model.RegistrationToken;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserCommandController {

    private final UserCommandHandler userCommandHandler;


    @PostMapping("/tokens")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public List<RegistrationToken> generateRegistrationTokens(@Valid @RequestBody GenerateRegistrationTokensCommand command) {
        return userCommandHandler.handle(command);
    }

    @PutMapping("/me/description")
    @Secured({"STUDENT", "TEACHER", "PARENT", "OFFICE", "DIRECTOR", "ADMIN"})
    public void updateOwnDescription(@Valid @RequestBody UpdateDescriptionCommand command) {
        userCommandHandler.handle(command);
    }

    @PutMapping("/me/password")
    @Secured({"STUDENT", "TEACHER", "PARENT", "OFFICE", "DIRECTOR", "ADMIN"})
    public void changePassword(@Valid @RequestBody ChangePasswordCommand command) {
        userCommandHandler.handle(command);
    }

    @PutMapping("/me/image")
    @Secured({"STUDENT", "TEACHER", "PARENT", "OFFICE", "DIRECTOR", "ADMIN"})
    public void changeImage(@Valid @RequestParam("image") MultipartFile image) {
        userCommandHandler.handle(new UpdateProfileImageCommand(image));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Secured({"DIRECTOR", "ADMIN"})
    public void archiveById(@PathVariable("id") Long id) {
        userCommandHandler.handle(new ArchiveUserCommand(id));
    }

    @PutMapping("/{id}/unassign")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public void unassignStudent(@PathVariable("id") Long id) {
        userCommandHandler.handle(new UnassignCommand(id));
    }

    @PutMapping("/{id}/assign")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public void assignStudent(@PathVariable("id") Long id, @Valid @RequestBody AssignCommand command) {
        command.setStudentId(id);
        userCommandHandler.handle(command);
    }
}
