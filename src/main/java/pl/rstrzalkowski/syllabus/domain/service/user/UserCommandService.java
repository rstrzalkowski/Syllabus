package pl.rstrzalkowski.syllabus.domain.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rstrzalkowski.syllabus.application.command.user.ArchiveUserCommand;
import pl.rstrzalkowski.syllabus.application.command.user.CreateUserByAdminCommand;
import pl.rstrzalkowski.syllabus.application.command.user.UpdateUserCommand;
import pl.rstrzalkowski.syllabus.domain.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandService {


    private final UserRepository userRepository;

    public void create(CreateUserByAdminCommand command) {
    }

    public void update(UpdateUserCommand command) {
    }

    public void archiveById(ArchiveUserCommand command) {
    }
}
