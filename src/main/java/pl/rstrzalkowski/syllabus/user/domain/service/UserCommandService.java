package pl.rstrzalkowski.syllabus.user.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rstrzalkowski.syllabus.user.application.command.ArchiveUserCommand;
import pl.rstrzalkowski.syllabus.user.application.command.CreateUserByAdminCommand;
import pl.rstrzalkowski.syllabus.user.application.command.UpdateUserCommand;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandService {


    public void create(CreateUserByAdminCommand command) {
    }

    public void update(UpdateUserCommand command) {
    }

    public void archiveById(ArchiveUserCommand command) {
    }
}
