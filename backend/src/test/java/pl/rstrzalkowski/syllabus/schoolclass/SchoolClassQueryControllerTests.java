package pl.rstrzalkowski.syllabus.schoolclass;

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
public class SchoolClassQueryControllerTests {

    @Autowired
    private MockMvc mockMvc;


    //region GET /classes/{id}
    @Test
    @WithUserDetails("office")
    void shouldReturnSchoolClassByIdAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/classes/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("director")
    void shouldReturnSchoolClassByIdAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/classes/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    void shouldReturnSchoolClassByIdAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/classes/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningSchoolClassByIdAsStudentWithSC403() throws Exception {
        mockMvc.perform(get("/classes/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningSchoolClassByIdAsTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/classes/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailReturningSchoolClassByIdAsAnonymousWithSC403() throws Exception {
        mockMvc.perform(get("/classes/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("office")
    void shouldFailReturningNonExistentSchoolClassByIdWithSC404() throws Exception {
        mockMvc.perform(get("/classes/9999"))
                .andExpect(status().isNotFound());
    }
    //endregion

    //region GET /classes
    @Test
    @WithUserDetails("office")
    void shouldReturnSchoolClassesAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/classes"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("director")
    void shouldReturnSchoolClassesAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/classes"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    void shouldReturnSchoolClassesAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/classes"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningSchoolClassesStudentWithSC403() throws Exception {
        mockMvc.perform(get("/classes"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningSchoolClassesAsTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/classes"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailReturningSchoolClassesAsAnonymousWithSC403() throws Exception {
        mockMvc.perform(get("/classes"))
                .andExpect(status().isForbidden());
    }
    //endregion

    //region GET /classes/archived
    @Test
    @WithUserDetails("office")
    void shouldReturnArchivedSchoolClassesAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/classes/archived"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("director")
    void shouldReturnArchivedSchoolClassesAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/classes/archived"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    void shouldReturnArchivedSchoolClassesAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/classes/archived"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningArchivedSchoolClassesStudentWithSC403() throws Exception {
        mockMvc.perform(get("/classes/archived"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningArchivedSchoolClassesAsTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/classes/archived"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailReturningArchivedSchoolClassesAsAnonymousWithSC403() throws Exception {
        mockMvc.perform(get("/classes/archived"))
                .andExpect(status().isForbidden());
    }
    //endregion
}
