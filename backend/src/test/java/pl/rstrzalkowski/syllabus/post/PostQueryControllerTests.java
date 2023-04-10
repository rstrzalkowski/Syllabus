package pl.rstrzalkowski.syllabus.post;

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
public class PostQueryControllerTests {

    @Autowired
    private MockMvc mockMvc;


    //region GET /posts/{id}
    @Test
    @WithUserDetails("office")
    void shouldReturnPostByIdAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("director")
    void shouldReturnPostByIdAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    void shouldReturnPostByIdAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningPostByIdAsStudentWithSC403() throws Exception {
        mockMvc.perform(get("/posts/6"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningPostByIdAsTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/posts/6"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailReturningPostByIdAsAnonymousWithSC403() throws Exception {
        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("office")
    void shouldFailReturningNonExistentPostByIdWithSC404() throws Exception {
        mockMvc.perform(get("/posts/9999"))
                .andExpect(status().isNotFound());
    }
    //endregion
}
