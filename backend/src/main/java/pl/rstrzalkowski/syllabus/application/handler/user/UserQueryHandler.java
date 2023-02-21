package pl.rstrzalkowski.syllabus.application.handler.user;

import org.springframework.data.domain.Page;
import pl.rstrzalkowski.syllabus.application.dto.TokenDTO;
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
import pl.rstrzalkowski.syllabus.application.query.user.GetDirectorTokensQuery;
import pl.rstrzalkowski.syllabus.application.query.user.GetLoggedInUserQuery;
import pl.rstrzalkowski.syllabus.application.query.user.GetNotSupervisingActiveTeachersQuery;
import pl.rstrzalkowski.syllabus.application.query.user.GetOfficeTokensQuery;
import pl.rstrzalkowski.syllabus.application.query.user.GetStudentTokensQuery;
import pl.rstrzalkowski.syllabus.application.query.user.GetTeacherTokensQuery;
import pl.rstrzalkowski.syllabus.application.query.user.GetUnassignedStudentsQuery;
import pl.rstrzalkowski.syllabus.application.query.user.GetUserByIdQuery;
import pl.rstrzalkowski.syllabus.application.query.user.GetUserByKeywordQuery;
import pl.rstrzalkowski.syllabus.domain.model.User;

import java.util.List;

public interface UserQueryHandler {

    User handle(GetUserByIdQuery query);

    Page<User> handle(GetActiveUsersQuery getActiveUsersQuery);

    Page<User> handle(GetArchivedUsersQuery getArchivedUsersQuery);

    Page<User> handle(GetUserByKeywordQuery query);

    Page<User> handle(GetActiveStudentsQuery query);

    Page<User> handle(GetActiveTeachersQuery query);

    Page<User> handle(GetActiveOfficesQuery query);

    Page<User> handle(GetActiveDirectorsQuery query);

    Page<User> handle(GetArchivedStudentsQuery query);

    Page<User> handle(GetArchivedTeachersQuery query);

    Page<User> handle(GetArchivedOfficesQuery query);

    Page<User> handle(GetArchivedDirectorsQuery query);

    User handle(GetLoggedInUserQuery query);

    List<User> handle(GetNotSupervisingActiveTeachersQuery query);

    Page<TokenDTO> handle(GetStudentTokensQuery query);

    Page<TokenDTO> handle(GetTeacherTokensQuery query);

    Page<TokenDTO> handle(GetOfficeTokensQuery query);

    Page<TokenDTO> handle(GetDirectorTokensQuery query);

    Page<User> handle(GetUnassignedStudentsQuery query);
}
