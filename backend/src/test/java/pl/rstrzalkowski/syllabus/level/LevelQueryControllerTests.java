package pl.rstrzalkowski.syllabus.level;

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
public class LevelQueryControllerTests {

    @Autowired
    private MockMvc mockMvc;


    //region GET /levels/{id}
    @Test
    @WithUserDetails("office")
    void shouldReturnLevelByIdAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/levels/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("director")
    void shouldReturnLevelByIdAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/levels/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    void shouldReturnLevelByIdAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/levels/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningLevelByIdAsStudentWithSC403() throws Exception {
        mockMvc.perform(get("/levels/6"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningLevelByIdAsTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/levels/6"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailReturningLevelByIdAsAnonymousWithSC403() throws Exception {
        mockMvc.perform(get("/levels/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("office")
    void shouldFailReturningNonExistentLevelByIdWithSC404() throws Exception {
        mockMvc.perform(get("/levels/9999"))
                .andExpect(status().isNotFound());
    }
    //endregion

    //region GET /levels
    @Test
    @WithUserDetails("office")
    void shouldReturnLevelsAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/levels"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("director")
    void shouldReturnLevelsAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/levels"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    void shouldReturnLevelsAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/levels"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningLevelsAsStudentWithSC403() throws Exception {
        mockMvc.perform(get("/levels"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningLevelsAsTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/levels"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailReturningLevelsAsAnonymousWithSC403() throws Exception {
        mockMvc.perform(get("/levels"))
                .andExpect(status().isForbidden());
    }
    //endregion
}
