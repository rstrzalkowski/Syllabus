package pl.rstrzalkowski.syllabus.user.application;

import org.springframework.data.domain.Page;
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

public interface UserQueryHandler {

    User handle(GetUserByIdQuery query);

    Page<User> handle(GetActiveUsersQuery getActiveUsersQuery);

    Page<User> handle(GetArchivedUsersQuery getArchivedUsersQuery);

    Page<User> handle(GetUserByEmailContainingQuery query);

    Page<User> handle(GetActiveStudentsQuery query);

    Page<User> handle(GetActiveTeachersQuery query);

    Page<User> handle(GetActiveOfficesQuery query);

    Page<User> handle(GetActiveDirectorsQuery query);

    Page<User> handle(GetArchivedStudentsQuery query);

    Page<User> handle(GetArchivedTeachersQuery query);

    Page<User> handle(GetArchivedOfficesQuery query);

    Page<User> handle(GetArchivedDirectorsQuery query);
}
