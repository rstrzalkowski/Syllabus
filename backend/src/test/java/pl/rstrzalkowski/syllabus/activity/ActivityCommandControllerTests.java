package pl.rstrzalkowski.syllabus.activity;

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

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ActivityCommandControllerTests {

    @Autowired
    private MockMvc mockMvc;


    //region POST /activities
    @Test
    @WithUserDetails("office")
    void shouldCreateActivityAsOfficeWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "test");
        jsonObject.put("weight", "5");
        jsonObject.put("description", "description test");
        jsonObject.put("realisationId", "1");
        jsonObject.put("date", LocalDateTime.now().plusDays(4));
        mockMvc.perform(post("/activities").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("director")
    void shouldCreateActivityAsDirectorWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "test");
        jsonObject.put("weight", "5");
        jsonObject.put("description", "description test");
        jsonObject.put("realisationId", "2");
        jsonObject.put("date", LocalDateTime.now().plusDays(4));
        mockMvc.perform(post("/activities").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldCreateActivityAsAffiliatedTeacherWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "test");
        jsonObject.put("weight", "5");
        jsonObject.put("description", "description test");
        jsonObject.put("realisationId", "1");
        jsonObject.put("date", LocalDateTime.now().plusDays(4));
        mockMvc.perform(post("/activities").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("admin")
    void shouldCreateActivityAsAdminWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "test");
        jsonObject.put("weight", "5");
        jsonObject.put("description", "description test");
        jsonObject.put("realisationId", "3");
        jsonObject.put("date", LocalDateTime.now().plusDays(4));
        mockMvc.perform(post("/activities").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailCreatingActivityAsStudentWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "test");
        jsonObject.put("weight", "5");
        jsonObject.put("description", "description test");
        jsonObject.put("realisationId", "4");
        jsonObject.put("date", LocalDateTime.now().plusDays(4));
        mockMvc.perform(post("/activities").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailCreatingActivityAsNotAffiliatedTeacherWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "test");
        jsonObject.put("weight", "5");
        jsonObject.put("description", "description test");
        jsonObject.put("realisationId", "5");
        jsonObject.put("date", LocalDateTime.now().plusDays(4));
        mockMvc.perform(post("/activities").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("admin")
    void shouldFailCreatingActivityWithSC400() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "test");
        jsonObject.put("description", "description test");
        jsonObject.put("realisationId", "5");
        jsonObject.put("date", LocalDateTime.now().plusDays(4));
        mockMvc.perform(post("/activities").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isBadRequest());

        jsonObject = new JSONObject();
        jsonObject.put("weight", "5");
        jsonObject.put("description", "description test");
        jsonObject.put("realisationId", "5");
        jsonObject.put("date", LocalDateTime.now().plusDays(4));
        mockMvc.perform(post("/activities").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isBadRequest());

        jsonObject = new JSONObject();
        jsonObject.put("name", "test");
        jsonObject.put("weight", "5");
        jsonObject.put("description", "description test");
        jsonObject.put("date", LocalDateTime.now().plusDays(4));
        mockMvc.perform(post("/activities").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isBadRequest());

        jsonObject = new JSONObject();
        jsonObject.put("name", "test");
        jsonObject.put("weight", "5");
        jsonObject.put("description", "description test");
        jsonObject.put("realisationId", "5");
        mockMvc.perform(post("/activities").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isBadRequest());
    }
    //endregion

    //region PUT /activities/{id}
    @Test
    @WithUserDetails("office")
    void shouldUpdateActivityAsOfficeWithSC200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("weight", "17");
        mockMvc.perform(put("/activities/1").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());

        mockMvc.perform(get("/activities/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weight").value("17"));
    }

    @Test
    @WithUserDetails("director")
    void shouldUpdateActivityAsDirectorWithSC200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("weight", "14");
        mockMvc.perform(put("/activities/2").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());

        mockMvc.perform(get("/activities/2"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weight").value("14"));
    }

    @Test
    @WithUserDetails("admin")
    void shouldUpdateActivityAsAdminWithSC200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("weight", "13");
        mockMvc.perform(put("/activities/3").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());

        mockMvc.perform(get("/activities/3"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weight").value("13"));
    }

    @Test
    @WithUserDetails("student")
    void shouldFailUpdatingActivityAsStudentWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("weight", "13");
        mockMvc.perform(put("/activities/5").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailUpdatingActivityAsNotAffiliatedTeacherWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("weight", "8");
        mockMvc.perform(put("/activities/16").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }
    //endregion

    //region DELETE /activities/{id}
    @Test
    @WithUserDetails("director")
    void shouldArchiveActivityAsDirectorWithSC204() throws Exception {
        mockMvc.perform(delete("/activities/10"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails("admin")
    void shouldArchiveActivityAsAdminWithSC204() throws Exception {
        mockMvc.perform(delete("/activities/11"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailArchivingActivityAsStudentWithSC403() throws Exception {
        mockMvc.perform(delete("/activities/12"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailArchivingActivityAsNotAffiliatedTeacherWithSC403() throws Exception {
        mockMvc.perform(delete("/activities/13"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("office")
    void shouldFailArchivingActivityAsOfficeWithSC403() throws Exception {
        mockMvc.perform(delete("/activities/14"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldArchiveActivityAsAffiliatedTeacherWithSC204() throws Exception {
        mockMvc.perform(delete("/activities/3"))
                .andExpect(status().isNoContent());
    }
    //endregion
}
