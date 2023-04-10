package pl.rstrzalkowski.syllabus.level;

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
public class LevelCommandControllerTests {

    @Autowired
    private MockMvc mockMvc;


    //region POST /levels
    @Test
    @WithUserDetails("office")
    void shouldCreateLevelAsOfficeWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("level", "9");
        mockMvc.perform(post("/levels").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("director")
    void shouldCreateLevelAsDirectorWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("level", "10");
        mockMvc.perform(post("/levels").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("admin")
    void shouldCreateLevelAsAdminWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("level", "11");
        mockMvc.perform(post("/levels").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailCreatingLevelAsStudentWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("level", "12");
        mockMvc.perform(post("/levels").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailCreatingLevelAsTeacherWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("level", "13");
        mockMvc.perform(post("/levels").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("office")
    void shouldFailCreatingLevelWithSC409() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("level", "5");
        mockMvc.perform(post("/levels").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isConflict());


    }

    @Test
    void shouldFailCreatingLevelWithSC400() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("level", "-5");
        mockMvc.perform(post("/levels").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isBadRequest());
    }
    //endregion

    //region PUT /levels/{id}
    @Test
    @WithUserDetails("office")
    void shouldUpdateLevelAsOfficeWithSC200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "3");
        jsonObject.put("level", "14");
        mockMvc.perform(put("/levels").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());

        mockMvc.perform(get("/levels/3"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("14"));
    }

    @Test
    @WithUserDetails("director")
    void shouldUpdateLevelAsDirectorWithSC200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "4");
        jsonObject.put("level", "15");
        mockMvc.perform(put("/levels").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());

        mockMvc.perform(get("/levels/4"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("15"));
    }

    @Test
    @WithUserDetails("admin")
    void shouldUpdateLevelAsAdminWithSC200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "1");
        jsonObject.put("level", "16");
        mockMvc.perform(put("/levels").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());

        mockMvc.perform(get("/levels/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("16"));
    }

    @Test
    @WithUserDetails("student")
    void shouldFailUpdatingLevelAsStudentWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "2");
        jsonObject.put("level", "17");
        mockMvc.perform(put("/levels").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailUpdatingLevelAsTeacherWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "2");
        jsonObject.put("level", "18");
        mockMvc.perform(put("/levels").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }
    //endregion

    //region DELETE /levels/{id}
    @Test
    @WithUserDetails("director")
    void shouldArchiveLevelAsDirectorWithSC204() throws Exception {
        mockMvc.perform(delete("/levels/3"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails("admin")
    void shouldArchiveLevelAsAdminWithSC204() throws Exception {
        mockMvc.perform(delete("/levels/3"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailArchivingLevelAsStudentWithSC403() throws Exception {
        mockMvc.perform(delete("/levels/3"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailArchivingLevelAsNotAffiliatedTeacherWithSC403() throws Exception {
        mockMvc.perform(delete("/levels/3"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailArchivingLevelAsTeacherWithSC403() throws Exception {
        mockMvc.perform(delete("/levels/3"))
                .andExpect(status().isForbidden());
    }
    //endregion
}
