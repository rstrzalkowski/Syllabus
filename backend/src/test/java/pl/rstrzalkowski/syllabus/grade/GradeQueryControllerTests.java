package pl.rstrzalkowski.syllabus.grade;

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
public class GradeQueryControllerTests {

    @Autowired
    private MockMvc mockMvc;


    //region GET /grades/{id}
    @Test
    @WithUserDetails("office")
    void shouldReturnGradeByIdAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/grades/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("director")
    void shouldReturnGradeByIdAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/grades/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    void shouldReturnGradeByIdAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/grades/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldReturnGradeByIdAsAuthorTeacherWithSC200() throws Exception {
        mockMvc.perform(get("/grades/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningGradeByIdAsStudentWithSC403() throws Exception {
        mockMvc.perform(get("/grades/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningGradeByIdAsTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/grades/41"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailReturningGradeByIdAsAnonymousWithSC403() throws Exception {
        mockMvc.perform(get("/grades/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("office")
    void shouldFailReturningNonExistentGradeByIdWithSC404() throws Exception {
        mockMvc.perform(get("/grades/9999"))
                .andExpect(status().isNotFound());
    }
    //endregion

    //region GET /grades/own
    @Test
    @WithUserDetails("student")
    void shouldReturnOwnGradesAsStudentWithSC200() throws Exception {
        mockMvc.perform(get("/grades/own"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningOwnGradesAsTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/grades/own"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("office")
    void shouldFailReturningOwnGradesAsOfficeWithSC403() throws Exception {
        mockMvc.perform(get("/grades/own"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("director")
    void shouldFailReturningOwnGradesAsDirectorWithSC403() throws Exception {
        mockMvc.perform(get("/grades/own"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("admin")
    void shouldFailReturningOwnGradesAsAdminWithSC403() throws Exception {
        mockMvc.perform(get("/grades/own"))
                .andExpect(status().isForbidden());
    }
    //endregion
}
