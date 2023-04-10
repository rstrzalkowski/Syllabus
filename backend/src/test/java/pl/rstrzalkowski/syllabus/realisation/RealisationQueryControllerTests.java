package pl.rstrzalkowski.syllabus.realisation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RealisationQueryControllerTests {

    @Autowired
    private MockMvc mockMvc;


    //region GET /realisations
    @Test
    @WithUserDetails("office")
    void shouldReturnRealisationsAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/realisations"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("director")
    void shouldReturnRealisationsAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/realisations"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    void shouldReturnRealisationsAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/realisations"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningRealisationsAsStudentWithSC403() throws Exception {
        mockMvc.perform(get("/realisations"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningRealisationsAsTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/realisations"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailReturningRealisationsAsAnonymousWithSC403() throws Exception {
        mockMvc.perform(get("/realisations"))
                .andExpect(status().isForbidden());
    }
    //endregion

    //region GET /realisations/archived
    @Test
    @WithUserDetails("office")
    void shouldReturnArchivedRealisationsAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/archived"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("director")
    void shouldReturnArchivedRealisationsAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/archived"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    void shouldReturnArchivedRealisationsAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/archived"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningArchivedRealisationsAsStudentWithSC403() throws Exception {
        mockMvc.perform(get("/realisations/archived"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningArchivedRealisationsAsTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/realisations/archived"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailReturningArchivedRealisationAsAnonymousWithSC403() throws Exception {
        mockMvc.perform(get("/realisations/archived"))
                .andExpect(status().isForbidden());
    }

    //endregion

    //region GET /realisations/{id}
    @Test
    @WithUserDetails("office")
    void shouldReturnRealisationByIdAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subjectName").value("Mathematics"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value("2023"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherFirstName").value("Daniel"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherLastName").value("Ansel"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.schoolClassName").value("1 IT"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subjectAbbreviation").value("MATH"));
    }

    @Test
    @WithUserDetails("director")
    void shouldReturnRealisationByIdAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subjectName").value("Mathematics"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value("2023"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherFirstName").value("Daniel"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherLastName").value("Ansel"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.schoolClassName").value("1 IT"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subjectAbbreviation").value("MATH"));
    }

    @Test
    @WithUserDetails("admin")
    void shouldReturnRealisationByIdAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subjectName").value("Mathematics"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value("2023"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherFirstName").value("Daniel"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherLastName").value("Ansel"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.schoolClassName").value("1 IT"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subjectAbbreviation").value("MATH"));
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningRealisationByIdAsNotAffiliatedStudentWithSC403() throws Exception {
        mockMvc.perform(get("/realisations/12"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningRealisationByIdAsNotAffiliatedTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/realisations/17"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailReturningRealisationByIdAsAnonymousWithSC403() throws Exception {
        mockMvc.perform(get("/realisations/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("office")
    void shouldFailReturningNonExistentRealisationByIdWithSC404() throws Exception {
        mockMvc.perform(get("/realisations/9999"))
                .andExpect(status().isNotFound());
    }
    //endregion

    //region GET /realisations/{id}/posts
    @Test
    @WithUserDetails("office")
    void shouldReturnPostsOfRealisationAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/3/posts"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("director")
    void shouldReturnPostsOfRealisationAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/3/posts"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    void shouldReturnPostsOfRealisationAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/3/posts"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldReturnPostsOfRealisationAsAffiliatedTeacherWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/8/posts"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldReturnPostsOfRealisationAsAffiliatedStudentWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/3/posts"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningPostsOfRealisationAsNotAffiliatedStudentWithSC403() throws Exception {
        mockMvc.perform(get("/realisations/13/posts"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningPostsOfRealisationAsNotAffiliatedTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/realisations/3/posts"))
                .andExpect(status().isForbidden());
    }
    //endregion

    //region GET /realisations/{id}/activities
    @Test
    @WithUserDetails("office")
    void shouldReturnActivitiesOfRealisationAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/3/activities"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("director")
    void shouldReturnActivitiesOfRealisationAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/3/activities"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    void shouldReturnActivitiesOfRealisationAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/3/activities"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldReturnActivitiesOfRealisationAsAffiliatedTeacherWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/8/activities"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldReturnActivitiesOfRealisationAsAffiliatedStudentWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/3/activities"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningActivitiesOfRealisationAsNotAffiliatedStudentWithSC403() throws Exception {
        mockMvc.perform(get("/realisations/13/activities"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningActivitiesOfRealisationAsNotAffiliatedTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/realisations/3/activities"))
                .andExpect(status().isForbidden());
    }
    //endregion

    //region GET /realisations/{id}/activities/incoming
    @Test
    @WithUserDetails("office")
    void shouldReturnIncomingActivitiesOfRealisationAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/3/activities/incoming"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("director")
    void shouldReturnIncomingActivitiesOfRealisationAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/3/activities/incoming"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    void shouldReturnIncomingActivitiesOfRealisationAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/3/activities/incoming"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldReturnIncomingActivitiesOfRealisationAsAffiliatedTeacherWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/8/activities/incoming"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldReturnIncomingActivitiesOfRealisationAsAffiliatedStudentWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/3/activities/incoming"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningIncomingActivitiesOfRealisationAsNotAffiliatedStudentWithSC403() throws Exception {
        mockMvc.perform(get("/realisations/13/activities/incoming"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningIncomingActivitiesOfRealisationAsNotAffiliatedTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/realisations/3/activities/incoming"))
                .andExpect(status().isForbidden());
    }
    //endregion

    //region GET /realisations/{id}/grades
    @Test
    @WithUserDetails("student")
    void shouldReturnGradesOfRealisationByIdAsAffiliatedStudentWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/1/grades"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("office")
    void shouldFailReturningGradesOfRealisationByIdAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/1/grades"))
                .andExpect(status().isForbidden());
    }


    @Test
    @WithUserDetails("director")
    void shouldFailReturningGradesOfRealisationByIdAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/1/grades"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("admin")
    void shouldFailReturningGradesOfRealisationByIdAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/1/grades"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningGradesOfRealisationByIdAsNotAffiliatedStudentWithSC403() throws Exception {
        mockMvc.perform(get("/realisations/12/grades"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningGradesOfRealisationByIdAsNotAffiliatedTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/realisations/17/grades"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailReturningGradesOfRealisationByIdAsAnonymousWithSC403() throws Exception {
        mockMvc.perform(get("/realisations/1/grades"))
                .andExpect(status().isForbidden());
    }
    //endregion

    //region GET /realisations/{id}/average
    @Test
    @WithUserDetails("student")
    void shouldReturnAverageGradeOfRealisationByIdAsAffiliatedStudentWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/4/average"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.average").value("4.666666666666667"));
    }

    @Test
    @WithUserDetails("office")
    void shouldFailReturningAverageGradeOfRealisationByIdAsOfficeWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/1/average"))
                .andExpect(status().isForbidden());
    }


    @Test
    @WithUserDetails("director")
    void shouldFailReturningAverageGradeOfRealisationByIdAsDirectorWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/1/average"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("admin")
    void shouldFailReturningAverageGradeOfRealisationByIdAsAdminWithSC200() throws Exception {
        mockMvc.perform(get("/realisations/1/average"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("student")
    void shouldFailReturningAverageGradeOfRealisationByIdAsNotAffiliatedStudentWithSC403() throws Exception {
        mockMvc.perform(get("/realisations/12/average"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("teacher")
    void shouldFailReturningAverageGradeOfRealisationByIdAsNotAffiliatedTeacherWithSC403() throws Exception {
        mockMvc.perform(get("/realisations/17/average"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldFailReturningAverageGradeOfRealisationByIdAsAnonymousWithSC403() throws Exception {
        mockMvc.perform(get("/realisations/1/average"))
                .andExpect(status().isForbidden());
    }
    //endregion
}
