package pl.rstrzalkowski.syllabus.user.infrastructure;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.user.application.UserQueryHandler;
import pl.rstrzalkowski.syllabus.user.application.query.GetActiveDirectorsQuery;
import pl.rstrzalkowski.syllabus.user.application.query.GetActiveOfficesQuery;
import pl.rstrzalkowski.syllabus.user.application.query.GetActiveStudentsQuery;
import pl.rstrzalkowski.syllabus.user.application.query.GetActiveTeachersQuery;
import pl.rstrzalkowski.syllabus.user.application.query.GetActiveUsersQuery;
import pl.rstrzalkowski.syllabus.user.application.query.GetArchivedDirectorsQuery;
import pl.rstrzalkowski.syllabus.user.application.query.GetArchivedOfficesQuery;
import pl.rstrzalkowski.syllabus.user.application.query.GetArchivedStudentsQuery;
import pl.rstrzalkowski.syllabus.user.application.query.GetArchivedTeachersQuery;
import pl.rstrzalkowski.syllabus.user.application.query.GetArchivedUsersQuery;
import pl.rstrzalkowski.syllabus.user.application.query.GetUserByEmailContainingQuery;
import pl.rstrzalkowski.syllabus.user.application.query.GetUserByIdQuery;
import pl.rstrzalkowski.syllabus.user.domain.model.User;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserQueryController {

    private final UserQueryHandler userQueryHandler;


    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userQueryHandler.handle(new GetUserByIdQuery(id));
    }

    @GetMapping("/search")
    public Page<User> getUserByEmailContaining(@PathParam("email") String email, Pageable pageable) {
        return userQueryHandler.handle(new GetUserByEmailContainingQuery(email, pageable));
    }

    @GetMapping
    public Page<User> getAllActiveUsers(Pageable pageable) {
        return userQueryHandler.handle(new GetActiveUsersQuery(pageable));
    }

    @GetMapping("/archived")
    public Page<User> getAllArchivedUsers(Pageable pageable) {
        return userQueryHandler.handle(new GetArchivedUsersQuery(pageable));
    }

    @GetMapping("/students")
    public Page<User> getAllActiveStudents(Pageable pageable) {
        return userQueryHandler.handle(new GetActiveStudentsQuery(pageable));
    }

    @GetMapping("/teachers")
    public Page<User> getAllActiveTeachers(Pageable pageable) {
        return userQueryHandler.handle(new GetActiveTeachersQuery(pageable));
    }

    @GetMapping("/offices")
    public Page<User> getAllActiveOffices(Pageable pageable) {
        return userQueryHandler.handle(new GetActiveOfficesQuery(pageable));
    }

    @GetMapping("/directors")
    public Page<User> getAllActiveDirectors(Pageable pageable) {
        return userQueryHandler.handle(new GetActiveDirectorsQuery(pageable));
    }

    @GetMapping("/students/archived")
    public Page<User> getAllArchivedStudents(Pageable pageable) {
        return userQueryHandler.handle(new GetArchivedStudentsQuery(pageable));
    }

    @GetMapping("/teachers/archived")
    public Page<User> getAllArchivedTeachers(Pageable pageable) {
        return userQueryHandler.handle(new GetArchivedTeachersQuery(pageable));
    }

    @GetMapping("/offices/archived")
    public Page<User> getAllArchivedOffices(Pageable pageable) {
        return userQueryHandler.handle(new GetArchivedOfficesQuery(pageable));
    }

    @GetMapping("/directors/archived")
    public Page<User> getAllArchivedDirectors(Pageable pageable) {
        return userQueryHandler.handle(new GetArchivedDirectorsQuery(pageable));
    }
}
