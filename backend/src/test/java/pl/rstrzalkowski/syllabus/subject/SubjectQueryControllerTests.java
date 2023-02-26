package pl.rstrzalkowski.syllabus.subject;

import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import pl.rstrzalkowski.syllabus.application.command.subject.CreateSubjectCommand;
import pl.rstrzalkowski.syllabus.domain.service.subject.SubjectCommandService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class SubjectQueryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SubjectCommandService subjectCommandService;


    @PostConstruct
    void init() {
        subjectCommandService.create(new CreateSubjectCommand("testName", "TEST"));
    }


    //section /subjects
    @Test
    @WithUserDetails("office")
    void shouldReturnSubjectsAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/subjects"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("director")
    void shouldReturnSubjectsAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/subjects"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    void shouldReturnSubjectsAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/subjects"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningSubjectsAsStudentWithSC403() throws Exception {
        mockMvc.perform(get("/subjects"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningSubjectsAsTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/subjects"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailReturningSubjectsAsAnonymousWithSC403() throws Exception {
        mockMvc.perform(get("/subjects"))
                .andExpect(status().isForbidden());
    }
    //end section


    //section /subjects/archived
    @Test
    @WithUserDetails("office")
    void shouldReturnArchivedSubjectsAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/subjects/archived"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("director")
    void shouldReturnArchivedSubjectsAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/subjects/archived"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    void shouldReturnArchivedSubjectsAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/subjects/archived"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningArchivedSubjectsAsStudentWithSC403() throws Exception {
        mockMvc.perform(get("/subjects/archived"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningArchivedSubjectsAsTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/subjects/archived"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailReturningArchivedSubjectsAsAnonymousWithSC403() throws Exception {
        mockMvc.perform(get("/subjects/archived"))
                .andExpect(status().isForbidden());
    }

    //end section


    //section /subjects/{id}
    @Test
    @WithUserDetails("office")
    void shouldReturnSubjectByIdAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/subjects/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("director")
    void shouldReturnSubjectByIdAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/subjects/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    void shouldReturnSubjectByIdAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/subjects/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningSubjectByIdAsStudentWithSC403() throws Exception {
        mockMvc.perform(get("/subjects/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningSubjectByIdAsTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/subjects/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailReturningSubjectByIdAsAnonymousWithSC403() throws Exception {
        mockMvc.perform(get("/subjects/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("office")
    void shouldFailReturningNonExistentSubjectByIdWithSC404() throws Exception {
        mockMvc.perform(get("/subjects/9999"))
                .andExpect(status().isNotFound());
    }
    //end section
}
