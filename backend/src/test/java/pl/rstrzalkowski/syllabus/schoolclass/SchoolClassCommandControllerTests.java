package pl.rstrzalkowski.syllabus.schoolclass;

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
public class SchoolClassCommandControllerTests {

    @Autowired
    private MockMvc mockMvc;


    //region POST /classes
    @Test
    @WithUserDetails("office")
    void shouldCreateSchoolClassAsOfficeWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacherId", "1");
        jsonObject.put("levelId", "6");
        jsonObject.put("shortName", "TC");
        jsonObject.put("fullName", "Test class");
        mockMvc.perform(post("/classes").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("director")
    void shouldCreateSchoolClassAsDirectorWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacherId", "2");
        jsonObject.put("levelId", "6");
        jsonObject.put("shortName", "TC2");
        jsonObject.put("fullName", "Test class2");
        mockMvc.perform(post("/classes").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("admin")
    void shouldCreateSchoolClassAsAdminWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacherId", "3");
        jsonObject.put("levelId", "6");
        jsonObject.put("shortName", "TC3");
        jsonObject.put("fullName", "Test class3");
        mockMvc.perform(post("/classes").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailCreatingSchoolClassAsStudentWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacherId", "4");
        jsonObject.put("levelId", "6");
        jsonObject.put("shortName", "TC4");
        jsonObject.put("fullName", "Test class4");
        mockMvc.perform(post("/classes").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailCreatingSchoolClassAsTeacherWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacherId", "1");
        jsonObject.put("levelId", "6");
        jsonObject.put("shortName", "TC5");
        jsonObject.put("fullName", "Test class5");
        mockMvc.perform(post("/classes").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("admin")
    void shouldFailCreatingSchoolClassWithSC400() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("levelId", "6");
        jsonObject.put("shortName", "TC6");
        jsonObject.put("fullName", "Test class6");
        mockMvc.perform(post("/classes").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isBadRequest());

        jsonObject = new JSONObject();
        jsonObject.put("teacherId", "1");
        jsonObject.put("shortName", "TC7");
        jsonObject.put("fullName", "Test class7");
        mockMvc.perform(post("/classes").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isBadRequest());

        jsonObject = new JSONObject();
        jsonObject.put("teacherId", "1");
        jsonObject.put("fullName", "Test class8");
        mockMvc.perform(post("/classes").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isBadRequest());
    }
    //endregion

    //region PUT /classes/{id}
    @Test
    @WithUserDetails("office")
    void shouldUpdateSchoolClassAsOfficeWithSC200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fullName", "Test class9");
        mockMvc.perform(put("/classes/1").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());

        mockMvc.perform(get("/classes/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("Test class9"));
    }

    @Test
    @WithUserDetails("director")
    void shouldUpdateSchoolClassAsDirectorWithSC200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fullName", "Test class10");
        mockMvc.perform(put("/classes/2").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());

        mockMvc.perform(get("/classes/2"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("Test class10"));
    }

    @Test
    @WithUserDetails("admin")
    void shouldUpdateSchoolClassAsAdminWithSC200() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shortName", "TC11");
        mockMvc.perform(put("/classes/2").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());

        mockMvc.perform(get("/classes/2"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("TC11"));
    }

    @Test
    @WithUserDetails("student")
    void shouldFailUpdatingSchoolClassAsStudentWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fullName", "Test class12");
        mockMvc.perform(put("/classes/2").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailUpdatingSchoolClassAsTeacherWithSC403() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fullName", "Test class12");
        mockMvc.perform(put("/classes/3").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isForbidden());
    }
    //endregion

    //region DELETE /classes/{id}
    @Test
    @WithUserDetails("director")
    void shouldArchiveSchoolClassAsDirectorWithSC204() throws Exception {
        mockMvc.perform(delete("/classes/3"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails("admin")
    void shouldArchiveSchoolClassAsAdminWithSC204() throws Exception {
        mockMvc.perform(delete("/classes/3"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailArchivingSchoolClassAsStudentWithSC403() throws Exception {
        mockMvc.perform(delete("/classes/3"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailArchivingSchoolClassAsTeacherWithSC403() throws Exception {
        mockMvc.perform(delete("/classes/3"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("office")
    void shouldFailArchivingSchoolClassAsOfficeWithSC403() throws Exception {
        mockMvc.perform(delete("/classes/3"))
                .andExpect(status().isForbidden());
    }
    //endregion
}
