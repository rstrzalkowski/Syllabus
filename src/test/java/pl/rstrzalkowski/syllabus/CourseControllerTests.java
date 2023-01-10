package pl.rstrzalkowski.syllabus;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldReturnCourseByIdWithSC200() throws Exception {
        mockMvc.perform(get("/courses/1").accept(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("Discrete mathematics"))
                .andExpect(jsonPath("$.description").value("Course of discrete mathematics"))
                .andExpect(jsonPath("$.abbreviation").value("DM2023"));
    }

    @Test
    void shouldFailReturnCourseByIdWithSC404() throws Exception {
        mockMvc.perform(get("/courses/999").accept(MediaType.parseMediaType("application/json")))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateCourseWithSC201() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "TEST NAME");
        jsonObject.put("description", "TEST DESCRIPTION");
        jsonObject.put("abbreviation", "TD2023");
        mockMvc.perform(post("/courses").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("TEST NAME"))
                .andExpect(jsonPath("$.description").value("TEST DESCRIPTION"))
                .andExpect(jsonPath("$.abbreviation").value("TD2023"));
    }

    @Test
    void shouldFailCreatingCourseWithSC400() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("description", "TEST DESCRIPTION");
        mockMvc.perform(post("/courses").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isBadRequest());

        jsonObject = new JSONObject();
        jsonObject.put("description", "TEST DESCRIPTION");
        mockMvc.perform(post("/courses").content(jsonObject.toString()).contentType(MediaType.parseMediaType("application/json")))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnAllCoursesWithSC200() throws Exception {
        mockMvc.perform(get("/courses").accept(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void shouldAddStudentToCourseWithSC200() throws Exception {
        mockMvc.perform(put("/courses/1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                { "studentIds": [1] }
                                """))
                .andExpect(status().isOk());
    }

}
