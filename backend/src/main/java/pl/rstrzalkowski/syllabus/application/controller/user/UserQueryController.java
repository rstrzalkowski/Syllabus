package pl.rstrzalkowski.syllabus.application.controller.user;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.application.handler.user.UserQueryHandler;
import pl.rstrzalkowski.syllabus.application.query.user.GetActiveDirectorsQuery;
import pl.rstrzalkowski.syllabus.application.query.user.GetActiveOfficesQuery;
import pl.rstrzalkowski.syllabus.application.query.user.GetActiveStudentsQuery;
import pl.rstrzalkowski.syllabus.application.query.user.GetActiveTeachersQuery;
import pl.rstrzalkowski.syllabus.application.query.user.GetActiveUsersQuery;
import pl.rstrzalkowski.syllabus.application.query.user.GetArchivedDirectorsQuery;
import pl.rstrzalkowski.syllabus.application.query.user.GetArchivedOfficesQuery;
import pl.rstrzalkowski.syllabus.application.query.user.GetArchivedStudentsQuery;
import pl.rstrzalkowski.syllabus.application.query.user.GetArchivedTeachersQuery;
import pl.rstrzalkowski.syllabus.application.query.user.GetArchivedUsersQuery;
import pl.rstrzalkowski.syllabus.application.query.user.GetLoggedInUserQuery;
import pl.rstrzalkowski.syllabus.application.query.user.GetNotSupervisingActiveTeachersQuery;
import pl.rstrzalkowski.syllabus.application.query.user.GetUserByEmailContainingQuery;
import pl.rstrzalkowski.syllabus.application.query.user.GetUserByIdQuery;
import pl.rstrzalkowski.syllabus.domain.model.User;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserQueryController {

    private final UserQueryHandler userQueryHandler;


    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userQueryHandler.handle(new GetUserByIdQuery(id));
    }

    @GetMapping("/me")
    @Secured({"STUDENT", "TEACHER", "PARENT", "OFFICE", "DIRECTOR", "ADMIN"})
    public User getLoggedInUser() {
        return userQueryHandler.handle(new GetLoggedInUserQuery());
    }

    @GetMapping("/search")
    public Page<User> getUserByEmailContaining(@PathParam("email") String email, Pageable pageable) {
        return userQueryHandler.handle(new GetUserByEmailContainingQuery(email, pageable));
    }

    @GetMapping
    @Secured("OFFICE")
    public Page<User> getAllActiveUsers(Pageable pageable) {
        return userQueryHandler.handle(new GetActiveUsersQuery(pageable));
    }

    @GetMapping("/archived")
    @Secured("STUDENT")
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

    @GetMapping("/teachers/free")
    public List<User> getAllNotSupervisingActiveTeachers(Pageable pageable) {
        return userQueryHandler.handle(new GetNotSupervisingActiveTeachersQuery(pageable));
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
