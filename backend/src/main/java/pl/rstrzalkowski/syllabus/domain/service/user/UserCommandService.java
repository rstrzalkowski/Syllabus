package pl.rstrzalkowski.syllabus.domain.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.rstrzalkowski.syllabus.application.command.GenerateRegistrationTokensCommand;
import pl.rstrzalkowski.syllabus.application.command.user.ArchiveUserCommand;
import pl.rstrzalkowski.syllabus.application.command.user.UpdateUserCommand;
import pl.rstrzalkowski.syllabus.domain.model.RegistrationToken;
import pl.rstrzalkowski.syllabus.domain.model.SchoolClass;
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


    public void update(UpdateUserCommand command) {
    }

    public List<RegistrationToken> generateRegistrationTokens(GenerateRegistrationTokensCommand command) {
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
    }
}
