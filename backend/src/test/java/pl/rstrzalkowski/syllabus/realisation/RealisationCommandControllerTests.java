package pl.rstrzalkowski.syllabus.realisation;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Sql("/test-data.sql")
public class RealisationCommandControllerTests {

    @Autowired
    private MockMvc mockMvc;


    //region POST /realisations
    @Test
    @WithUserDetails("office")
    void shouldCreateRealisationAsOfficeWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacherId", "3");
        jsonObject.put("classId", "1");
        jsonObject.put("year", "2023");
        jsonObject.put("subjectId", "8");
        mockMvc.perform(post("/realisations").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("director")
    void shouldCreateRealisationAsDirectorWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacherId", "3");
        jsonObject.put("classId", "1");
        jsonObject.put("year", "2029");
        jsonObject.put("subjectId", "9");
        mockMvc.perform(post("/realisations").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("admin")
    void shouldCreateRealisationAsAdminWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacherId", "3");
        jsonObject.put("classId", "1");
        jsonObject.put("year", "2023");
        jsonObject.put("subjectId", "10");
        mockMvc.perform(post("/realisations").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailCreatingRealisationAsStudentWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacherId", "3");
        jsonObject.put("classId", "1");
        jsonObject.put("year", "2023");
        jsonObject.put("subjectId", "11");
        mockMvc.perform(post("/realisations").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailCreatingSubjectAsTeacherWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacherId", "3");
        jsonObject.put("classId", "1");
        jsonObject.put("year", "2023");
        jsonObject.put("subjectId", "12");
        mockMvc.perform(post("/realisations").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailCreatingRealisationWithSC400() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacherId", "3");
        jsonObject.put("classId", "1");
        jsonObject.put("subjectId", "13");
        mockMvc.perform(post("/realisations").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isBadRequest());

        jsonObject = new JSONObject();
        jsonObject.put("classId", "1");
        jsonObject.put("subjectId", "13");
        jsonObject.put("year", "2023");
        mockMvc.perform(post("/realisations").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isBadRequest());

        jsonObject = new JSONObject();
        jsonObject.put("teacherId", "3");
        jsonObject.put("subjectId", "13");
        jsonObject.put("year", "2023");
        mockMvc.perform(post("/realisations").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isBadRequest());

        jsonObject = new JSONObject();
        jsonObject.put("classId", "1");
        jsonObject.put("teacherId", "3");
        jsonObject.put("year", "2023");
        mockMvc.perform(post("/realisations").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isBadRequest());
    }
    //endregion

    //region PUT /realisations/{id}
    @Test
    @WithUserDetails("office")
    void shouldUpdateRealisationYearAsOfficeWithSC200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("year", "2024");
        mockMvc.perform(put("/realisations/11").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());


        mockMvc.perform(get("/realisations/11"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value("2024"));
    }

    @Test
    @WithUserDetails("director")
    void shouldUpdateRealisationYearAsDirectorWithSC200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("year", "2025");
        mockMvc.perform(put("/realisations/12").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());


        mockMvc.perform(get("/realisations/12"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value("2025"));
    }

    @Test
    @WithUserDetails("admin")
    void shouldUpdateRealisationTeacherAsAdminWithSC200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacherId", "10");
        mockMvc.perform(put("/realisations/13").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());


        mockMvc.perform(get("/realisations/13"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherId").value("10"));
    }

    @Test
    @WithUserDetails("student")
    void shouldFailUpdatingRealisationYearAsStudentWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("year", "2026");
        mockMvc.perform(put("/realisations/3").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailUpdatingSubjectAsTeacherWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("year", "2026");
        mockMvc.perform(put("/realisations/4").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }
    //endregion

    //region DELETE /realisations/{id}
    @Test
    @WithUserDetails("director")
    void shouldArchiveRealisationAsDirectorWithSC200() throws Exception {
        mockMvc.perform(delete("/realisations/5"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails("admin")
    void shouldArchiveRealisationAsAdminWithSC200() throws Exception {
        mockMvc.perform(delete("/realisations/6"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailArchivingRealisationAsStudentWithSC403() throws Exception {
        mockMvc.perform(delete("/realisations/7"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailArchivingRealisationAsTeacherWithSC403() throws Exception {
        mockMvc.perform(delete("/realisations/8"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("office")
    void shouldFailArchivingRealisationAsOfficeWithSC403() throws Exception {
        mockMvc.perform(delete("/realisations/9"))
                .andExpect(status().isForbidden());
    }
    //endregion
}
