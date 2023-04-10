package pl.rstrzalkowski.syllabus.activity;

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
public class ActivityQueryControllerTests {

    @Autowired
    private MockMvc mockMvc;


    //region GET /activities/{id}
    @Test
    @WithUserDetails("office")
    void shouldReturnActivityByIdAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/activities/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("director")
    void shouldReturnActivityByIdAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/activities/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    void shouldReturnActivityByIdAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/activities/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldReturnActivityByIdAsAuthorTeacherWithSC200() throws Exception {
        mockMvc.perform(get("/activities/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningActivityByIdAsStudentWithSC403() throws Exception {
        mockMvc.perform(get("/activities/6"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningActivityByIdAsTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/activities/7"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailReturningActivityByIdAsAnonymousWithSC403() throws Exception {
        mockMvc.perform(get("/activities/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("office")
    void shouldFailReturningNonExistentActivityByIdWithSC404() throws Exception {
        mockMvc.perform(get("/activities/9999"))
                .andExpect(status().isNotFound());
    }
    //endregion

    //region GET /activities/{id}/grades
    @Test
    @WithUserDetails("office")
    void shouldReturnGradesOfActivityByIdAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/activities/1/grades"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("director")
    void shouldReturnGradesOfActivityByIdAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/activities/1/grades"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    void shouldReturnGradesOfActivityByIdAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/activities/1/grades"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldReturnGradesOfActivityByIdAsAuthorTeacherWithSC200() throws Exception {
        mockMvc.perform(get("/activities/1/grades"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningGradesOfActivityByIdAsStudentWithSC403() throws Exception {
        mockMvc.perform(get("/activities/6/grades"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningGradesOfActivityByIdAsTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/activities/7/grades"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailReturningGradesOfActivityByIdAsAnonymousWithSC403() throws Exception {
        mockMvc.perform(get("/activities/1/grades"))
                .andExpect(status().isForbidden());
    }
    //endregion

    //region GET /activities/incoming
    @Test
    @WithUserDetails("student")
    void shouldReturnIncomingActivitiesAsStudentWithSC200() throws Exception {
        mockMvc.perform(get("/activities/incoming"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("director")
    void shouldFailReturningIncomingActivitiesAsDirectorWithSC403() throws Exception {
        mockMvc.perform(get("/activities/incoming"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("office")
    void shouldFailReturningIncomingActivitiesAsOfficeWithSC403() throws Exception {
        mockMvc.perform(get("/activities/incoming"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningIncomingActivitiesAsTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/activities/incoming"))
                .andExpect(status().isForbidden());
    }
    //endregion
}
