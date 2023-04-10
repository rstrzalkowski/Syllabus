package pl.rstrzalkowski.syllabus.subject;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SubjectCommandControllerTests {

    @Autowired
    private MockMvc mockMvc;


    //region POST /subjects
    @Test
    @WithUserDetails("office")
    void shouldCreateSubjectAsOfficeWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "TEST1");
        jsonObject.put("abbreviation", "T1");
        mockMvc.perform(post("/subjects").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("director")
    void shouldCreateSubjectAsDirectorWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "TEST2");
        jsonObject.put("abbreviation", "T2");
        mockMvc.perform(post("/subjects").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("admin")
    void shouldCreateSubjectAsAdminWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "TEST3");
        jsonObject.put("abbreviation", "T3");
        mockMvc.perform(post("/subjects").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailCreatingSubjectAsStudentWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "TEST4");
        jsonObject.put("abbreviation", "T4");
        mockMvc.perform(post("/subjects").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailCreatingSubjectAsTeacherWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "TEST5");
        jsonObject.put("abbreviation", "T5");
        mockMvc.perform(post("/subjects").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailCreatingSubjectWithSC400() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("abbreviation", "T6");
        mockMvc.perform(post("/subjects").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isBadRequest());

        jsonObject = new JSONObject();
        jsonObject.put("name", "TEST7");
        mockMvc.perform(post("/subjects").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isBadRequest());
    }
    //endregion

    //region PUT /subjects/{id}
    @Test
    @WithUserDetails("office")
    void shouldUpdateSubjectAsOfficeWithSC200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("abbreviation", "NA1");
        mockMvc.perform(put("/subjects/1").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("director")
    void shouldUpdateSubjectAsDirectorWithSC200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("abbreviation", "NA2");
        mockMvc.perform(put("/subjects/1").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    void shouldUpdateSubjectAsAdminWithSC200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("abbreviation", "NA3");
        mockMvc.perform(put("/subjects/1").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailUpdatingSubjectAsStudentWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("abbreviation", "NA4");
        mockMvc.perform(put("/subjects/1").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailUpdatingSubjectAsTeacherWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("abbreviation", "NA5");
        mockMvc.perform(put("/subjects/1").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }
    //endregion

    //region DELETE /subjects/{id}
    @Test
    @WithUserDetails("director")
    void shouldArchiveSubjectAsDirectorWithSC204() throws Exception {
        mockMvc.perform(delete("/subjects/2"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails("admin")
    void shouldArchiveSubjectAsAdminWithSC204() throws Exception {
        mockMvc.perform(delete("/subjects/3"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailArchivingSubjectAsStudentWithSC403() throws Exception {
        mockMvc.perform(delete("/subjects/4"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailArchivingSubjectAsTeacherWithSC403() throws Exception {
        mockMvc.perform(delete("/subjects/4"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("office")
    void shouldFailArchivingSubjectAsOfficeWithSC403() throws Exception {
        mockMvc.perform(delete("/subjects/4"))
                .andExpect(status().isForbidden());
    }
    //endregion
}
