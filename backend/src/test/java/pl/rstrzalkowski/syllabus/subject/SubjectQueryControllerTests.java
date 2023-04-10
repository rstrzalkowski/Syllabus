package pl.rstrzalkowski.syllabus.subject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SubjectQueryControllerTests {

    @Autowired
    private MockMvc mockMvc;


    //region GET /subjects
    @Test
    @WithUserDetails("office")
    void shouldReturnSubjectsAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/subjects"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("director")
    void shouldReturnSubjectsAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/subjects"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    void shouldReturnSubjectsAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/subjects"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningSubjectsAsStudentWithSC403() throws Exception {
        mockMvc.perform(get("/subjects"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningSubjectsAsTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/subjects"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailReturningSubjectsAsAnonymousWithSC403() throws Exception {
        mockMvc.perform(get("/subjects"))
                .andExpect(status().isForbidden());
    }
    //endregion

    //region GET /subjects/archived
    @Test
    @WithUserDetails("office")
    void shouldReturnArchivedSubjectsAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/subjects/archived"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("director")
    void shouldReturnArchivedSubjectsAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/subjects/archived"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    void shouldReturnArchivedSubjectsAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/subjects/archived"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningArchivedSubjectsAsStudentWithSC403() throws Exception {
        mockMvc.perform(get("/subjects/archived"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningArchivedSubjectsAsTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/subjects/archived"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailReturningArchivedSubjectsAsAnonymousWithSC403() throws Exception {
        mockMvc.perform(get("/subjects/archived"))
                .andExpect(status().isForbidden());
    }

    //endregion

    //region GET /subjects/{id}
    @Test
    @WithUserDetails("office")
    void shouldReturnSubjectByIdAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/subjects/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("director")
    void shouldReturnSubjectByIdAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/subjects/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    void shouldReturnSubjectByIdAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/subjects/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningSubjectByIdAsStudentWithSC403() throws Exception {
        mockMvc.perform(get("/subjects/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningSubjectByIdAsTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/subjects/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailReturningSubjectByIdAsAnonymousWithSC403() throws Exception {
        mockMvc.perform(get("/subjects/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("office")
    void shouldFailReturningNonExistentSubjectByIdWithSC404() throws Exception {
        mockMvc.perform(get("/subjects/9999"))
                .andExpect(status().isNotFound());
    }
    //endregion
}
