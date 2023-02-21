package pl.rstrzalkowski.syllabus.domain.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.rstrzalkowski.syllabus.application.command.user.ArchiveUserCommand;
import pl.rstrzalkowski.syllabus.application.command.user.AssignCommand;
import pl.rstrzalkowski.syllabus.application.command.user.ChangePasswordCommand;
import pl.rstrzalkowski.syllabus.application.command.user.GenerateRegistrationTokensCommand;
import pl.rstrzalkowski.syllabus.application.command.user.UnassignCommand;
import pl.rstrzalkowski.syllabus.application.command.user.UpdateDescriptionCommand;
import pl.rstrzalkowski.syllabus.application.command.user.UpdateProfileImageCommand;
import pl.rstrzalkowski.syllabus.application.command.user.UpdateUserCommand;
import pl.rstrzalkowski.syllabus.domain.exception.schoolclass.SchoolClassNotFoundException;
import pl.rstrzalkowski.syllabus.domain.exception.user.InvalidPasswordException;
import pl.rstrzalkowski.syllabus.domain.exception.user.PasswordNotAcceptableException;
import pl.rstrzalkowski.syllabus.domain.exception.user.UserNotFoundException;
import pl.rstrzalkowski.syllabus.domain.model.RegistrationToken;
import pl.rstrzalkowski.syllabus.domain.model.Role;
import pl.rstrzalkowski.syllabus.domain.model.SchoolClass;
import pl.rstrzalkowski.syllabus.domain.model.User;
import pl.rstrzalkowski.syllabus.domain.shared.ImageService;
import pl.rstrzalkowski.syllabus.infrastructure.repository.SchoolClassRepository;
import pl.rstrzalkowski.syllabus.infrastructure.repository.TokenRepository;
import pl.rstrzalkowski.syllabus.infrastructure.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandService {


    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final ImageService imageService;
    private final PasswordEncoder passwordEncoder;


    public void update(UpdateUserCommand command) {

    }

    public List<RegistrationToken> generateRegistrationTokens(GenerateRegistrationTokensCommand command) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (command.getRole() == Role.OFFICE) {
            if (user.getRole() != Role.DIRECTOR && user.getRole() != Role.ADMIN) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        }

        if (command.getRole() == Role.DIRECTOR || command.getRole() == Role.ADMIN) {
            if (user.getRole() != Role.ADMIN) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        }

        List<RegistrationToken> generatedTokens = new ArrayList<>();
        for (int i = 0; i < command.getAmount(); i++) {
            RegistrationToken token = new RegistrationToken();
            token.setRole(command.getRole());

            Long schoolClassId = command.getSchoolClassId();
            if (schoolClassId != null) {
                SchoolClass schoolClass = schoolClassRepository.findById(schoolClassId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
                token.setSchoolClass(schoolClass);
            }
            tokenRepository.save(token);
            generatedTokens.add(token);
        }
        return generatedTokens;
    }

    public void archiveById(ArchiveUserCommand command) {
        User user = userRepository.findById(command.id())
                .orElseThrow(UserNotFoundException::new);

        user.setSchoolClass(null);
        user.setArchived(true);

        userRepository.save(user);
    }

    public void updateDescription(UpdateDescriptionCommand command) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setDescription(command.getDescription());
        userRepository.save(user);
    }

    public void changePassword(ChangePasswordCommand command) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!passwordEncoder.matches(command.getOldPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        String newPassword = command.getNewPassword();
        if (newPassword.length() < 8 || newPassword.length() > 20) {
            throw new PasswordNotAcceptableException();
        }

        String newPasswordEncoded = passwordEncoder.encode(newPassword);
        user.setPassword(newPasswordEncoded);
        userRepository.save(user);
    }

    public void unassignStudent(UnassignCommand command) {
        User user = userRepository.findById(command.id())
                .orElseThrow(UserNotFoundException::new);

        if (user.getRole() != Role.STUDENT || user.isArchived()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        user.setSchoolClass(null);

        userRepository.save(user);
    }

    public void assignStudent(AssignCommand command) {
        User user = userRepository.findById(command.getStudentId())
                .orElseThrow(UserNotFoundException::new);

        SchoolClass schoolClass = schoolClassRepository.findById(command.getClassId())
                .orElseThrow(SchoolClassNotFoundException::new);

        if (user.getRole() != Role.STUDENT || user.isArchived()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        user.setSchoolClass(schoolClass);

        userRepository.save(user);
    }

    public void updateProfileImage(UpdateProfileImageCommand command) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        try {
            String imageUrl = imageService.saveImage(command.getImage());
            user.setImageUrl(imageUrl);
            userRepository.save(user);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
