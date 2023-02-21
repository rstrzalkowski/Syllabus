package pl.rstrzalkowski.syllabus.domain.adapter.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.application.command.user.ArchiveUserCommand;
import pl.rstrzalkowski.syllabus.application.command.user.AssignCommand;
import pl.rstrzalkowski.syllabus.application.command.user.ChangePasswordCommand;
import pl.rstrzalkowski.syllabus.application.command.user.GenerateRegistrationTokensCommand;
import pl.rstrzalkowski.syllabus.application.command.user.UnassignCommand;
import pl.rstrzalkowski.syllabus.application.command.user.UpdateDescriptionCommand;
import pl.rstrzalkowski.syllabus.application.command.user.UpdateProfileImageCommand;
import pl.rstrzalkowski.syllabus.application.command.user.UpdateUserCommand;
import pl.rstrzalkowski.syllabus.application.handler.user.UserCommandHandler;
import pl.rstrzalkowski.syllabus.domain.model.RegistrationToken;
import pl.rstrzalkowski.syllabus.domain.service.user.UserCommandService;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserCommandHandlerImpl implements UserCommandHandler {

    private final UserCommandService userCommandService;


    @Override
    public void handle(UpdateUserCommand command) {
        userCommandService.update(command);
    }

    @Override
    public void handle(ArchiveUserCommand command) {
        userCommandService.archiveById(command);
    }

    @Override
    public List<RegistrationToken> handle(GenerateRegistrationTokensCommand command) {
        return userCommandService.generateRegistrationTokens(command);
    }

    @Override
    public void handle(UpdateDescriptionCommand command) {
        userCommandService.updateDescription(command);
    }

    @Override
    public void handle(ChangePasswordCommand command) {
        userCommandService.changePassword(command);
    }

    @Override
    public void handle(UnassignCommand command) {
        userCommandService.unassignStudent(command);
    }

    @Override
    public void handle(AssignCommand command) {
        userCommandService.assignStudent(command);
    }

    @Override
    public void handle(UpdateProfileImageCommand command) {
        userCommandService.updateProfileImage(command);
    }
}
