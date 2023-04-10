package pl.rstrzalkowski.syllabus.grade;

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
public class GradeCommandControllerTests {

    @Autowired
    private MockMvc mockMvc;


    //region POST /grades
    @Test
    @WithUserDetails("office")
    void shouldCreateGradeAsOfficeWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("studentId", "16");
        jsonObject.put("activityId", "7");
        jsonObject.put("value", "4");
        mockMvc.perform(post("/grades").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("director")
    void shouldCreateGradeAsDirectorWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("studentId", "17");
        jsonObject.put("activityId", "7");
        jsonObject.put("value", "5");
        mockMvc.perform(post("/grades").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("admin")
    void shouldCreateGradeAsAdminWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("studentId", "18");
        jsonObject.put("activityId", "7");
        jsonObject.put("value", "2");
        mockMvc.perform(post("/grades").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailCreatingGradeAsStudentWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("studentId", "15");
        jsonObject.put("activityId", "7");
        jsonObject.put("value", "4");
        mockMvc.perform(post("/grades").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailCreatingGradeAsTeacherWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("studentId", "12");
        jsonObject.put("activityId", "7");
        jsonObject.put("value", "4");
        mockMvc.perform(post("/grades").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("admin")
    void shouldFailCreatingPostWithSC400() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("activityId", "7");
        jsonObject.put("value", "4");
        mockMvc.perform(post("/grades").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isBadRequest());

        jsonObject = new JSONObject();
        jsonObject.put("studentId", "11");
        jsonObject.put("value", "4");
        mockMvc.perform(post("/grades").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isBadRequest());

        jsonObject = new JSONObject();
        jsonObject.put("studentId", "11");
        jsonObject.put("activityId", "7");
        mockMvc.perform(post("/grades").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isBadRequest());
    }
    //endregion

    //region PUT /grades/{id}
    @Test
    @WithUserDetails("office")
    void shouldUpdateGradeAsOfficeWithSC200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", "5");
        mockMvc.perform(put("/grades/1").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());

        mockMvc.perform(get("/grades/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("5"));
    }

    @Test
    @WithUserDetails("director")
    void shouldUpdatePostAsDirectorWithSC200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", "3");
        mockMvc.perform(put("/grades/2").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());

        mockMvc.perform(get("/grades/2"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("3"));
    }

    @Test
    @WithUserDetails("admin")
    void shouldUpdatePostAsAdminWithSC200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", "3");
        mockMvc.perform(put("/grades/4").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());

        mockMvc.perform(get("/grades/4"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("3"));
    }

    @Test
    @WithUserDetails("student")
    void shouldFailUpdatingGradeAsStudentWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", "5");
        mockMvc.perform(put("/grades/10").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailUpdatingGradeAsTeacherWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("value", "2");
        mockMvc.perform(put("/grades/12").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());
    }
    //endregion

    //region DELETE /grades/{id}
    @Test
    @WithUserDetails("director")
    void shouldArchiveGradeAsDirectorWithSC204() throws Exception {
        mockMvc.perform(delete("/grades/20"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails("admin")
    void shouldArchivePostAsAdminWithSC204() throws Exception {
        mockMvc.perform(delete("/grades/21"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailArchivingPostAsStudentWithSC403() throws Exception {
        mockMvc.perform(delete("/grades/22"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailArchivingPostAsNotAffiliatedTeacherWithSC403() throws Exception {
        mockMvc.perform(delete("/grades/23"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("office")
    void shouldArchivePostAsOfficeWithSC204() throws Exception {
        mockMvc.perform(delete("/grades/24"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldArchivePostAsAffiliatedTeacherWithSC204() throws Exception {
        mockMvc.perform(delete("/grades/10"))
                .andExpect(status().isNoContent());
    }
    //endregion
}
