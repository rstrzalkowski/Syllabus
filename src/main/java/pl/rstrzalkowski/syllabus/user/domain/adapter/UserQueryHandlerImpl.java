package pl.rstrzalkowski.syllabus.user.domain.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
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
import pl.rstrzalkowski.syllabus.user.domain.service.UserQueryService;

@RequiredArgsConstructor
@Component
public class UserQueryHandlerImpl implements UserQueryHandler {

    private final UserQueryService userQueryService;


    @Override
    public User handle(GetUserByIdQuery query) {
        return userQueryService.getById(query.id());
    }

    @Override
    public Page<User> handle(GetActiveUsersQuery query) {
        return userQueryService.getAllActiveUsers(query.pageable());
    }

    @Override
    public Page<User> handle(GetArchivedUsersQuery query) {
        return userQueryService.getAllArchivedUsers(query.pageable());
    }

    @Override
    public Page<User> handle(GetUserByEmailContainingQuery query) {
        return userQueryService.getByEmailContaining(query.email(), query.pageable());
    }

    @Override
    public Page<User> handle(GetActiveStudentsQuery query) {
        return userQueryService.getAllActiveStudents(query.pageable());
    }

    @Override
    public Page<User> handle(GetActiveTeachersQuery query) {
        return userQueryService.getAllActiveTeachers(query.pageable());
    }

    @Override
    public Page<User> handle(GetActiveOfficesQuery query) {
        return userQueryService.getAllActiveOffices(query.pageable());
    }

    @Override
    public Page<User> handle(GetActiveDirectorsQuery query) {
        return userQueryService.getAllActiveDirectors(query.pageable());
    }

    @Override
    public Page<User> handle(GetArchivedStudentsQuery query) {
        return userQueryService.getAllArchiveStudents(query.pageable());
    }

    @Override
    public Page<User> handle(GetArchivedTeachersQuery query) {
        return userQueryService.getAllArchiveTeachers(query.pageable());
    }

    @Override
    public Page<User> handle(GetArchivedOfficesQuery query) {
        return userQueryService.getAllArchiveOffices(query.pageable());
    }

    @Override
    public Page<User> handle(GetArchivedDirectorsQuery query) {
        return userQueryService.getAllArchiveDirectors(query.pageable());
    }
}
