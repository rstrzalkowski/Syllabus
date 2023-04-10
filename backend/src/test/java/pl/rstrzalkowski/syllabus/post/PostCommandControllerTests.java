package pl.rstrzalkowski.syllabus.post;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PostCommandControllerTests {

    @Autowired
    private MockMvc mockMvc;


    //region POST /posts
    @Test
    @WithUserDetails("office")
    void shouldCreatePostAsOfficeWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("realisationId", "16");
        jsonObject.put("title", "TEST");
        jsonObject.put("content", "CONTENT");
        mockMvc.perform(post("/posts").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("director")
    void shouldCreatePostAsDirectorWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("realisationId", "17");
        jsonObject.put("title", "TEST");
        jsonObject.put("content", "CONTENT");
        mockMvc.perform(post("/posts").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("admin")
    void shouldCreatePostAsAdminWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("realisationId", "18");
        jsonObject.put("title", "TEST");
        jsonObject.put("content", "CONTENT");
        mockMvc.perform(post("/posts").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailCreatingPostAsStudentWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("realisationId", "16");
        jsonObject.put("title", "TEST");
        jsonObject.put("content", "CONTENT");
        mockMvc.perform(post("/posts").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailCreatingPostAsTeacherWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("realisationId", "16");
        jsonObject.put("title", "TEST");
        jsonObject.put("content", "CONTENT");
        mockMvc.perform(post("/posts").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailCreatingPostWithSC400() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "TEST");
        jsonObject.put("content", "CONTENT");
        mockMvc.perform(post("/posts").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isBadRequest());

        jsonObject = new JSONObject();
        jsonObject.put("realisationId", "16");
        jsonObject.put("content", "CONTENT");
        mockMvc.perform(post("/posts").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isBadRequest());

        jsonObject = new JSONObject();
        jsonObject.put("realisationId", "16");
        jsonObject.put("title", "TEST");
        mockMvc.perform(post("/posts").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isBadRequest());
    }
    //endregion

    //region PUT /posts/{id}
    @Test
    @WithUserDetails("office")
    void shouldUpdatePostAsOfficeWithSC200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "new title1");
        mockMvc.perform(put("/posts/1").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());

        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("new title1"));
    }

    @Test
    @WithUserDetails("director")
    void shouldUpdatePostAsDirectorWithSC200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", "new content1");
        mockMvc.perform(put("/posts/2").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());

        mockMvc.perform(get("/posts/2"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("new content1"));
    }

    @Test
    @WithUserDetails("admin")
    void shouldUpdatePostAsAdminWithSC200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "new title3");
        mockMvc.perform(put("/posts/3").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());

        mockMvc.perform(get("/posts/3"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("new title3"));
    }

    @Test
    @WithUserDetails("student")
    void shouldFailUpdatingPostAsStudentWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", "new content");
        mockMvc.perform(put("/posts/1").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailUpdatingPostAsTeacherWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", "new content");
        mockMvc.perform(put("/posts/7").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }
    //endregion

    //region DELETE /subjects/{id}
    @Test
    @WithUserDetails("director")
    void shouldArchivePostAsDirectorWithSC204() throws Exception {
        mockMvc.perform(delete("/posts/10"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails("admin")
    void shouldArchivePostAsAdminWithSC204() throws Exception {
        mockMvc.perform(delete("/posts/11"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailArchivingPostAsStudentWithSC403() throws Exception {
        mockMvc.perform(delete("/posts/12"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailArchivingPostAsNotAffiliatedTeacherWithSC403() throws Exception {
        mockMvc.perform(delete("/posts/10"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("office")
    void shouldArchivePostAsOfficeWithSC204() throws Exception {
        mockMvc.perform(delete("/posts/10"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldArchivePostAsAffiliatedTeacherWithSC204() throws Exception {
        mockMvc.perform(delete("/posts/3"))
                .andExpect(status().isNoContent());
    }
    //endregion
}
