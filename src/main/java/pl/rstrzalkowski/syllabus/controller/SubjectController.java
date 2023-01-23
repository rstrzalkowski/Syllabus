package pl.rstrzalkowski.syllabus.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.domain.Subject;
import pl.rstrzalkowski.syllabus.dto.create.CreateSubjectDTO;
import pl.rstrzalkowski.syllabus.dto.update.UpdateSubjectDTO;
import pl.rstrzalkowski.syllabus.service.ActivityService;
import pl.rstrzalkowski.syllabus.service.GradeService;
import pl.rstrzalkowski.syllabus.service.PostService;
import pl.rstrzalkowski.syllabus.service.SubjectService;

import java.util.List;


@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;
    private final GradeService gradeService;
    private final PostService postService;
    private final ActivityService activityService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Subject createSubject(@Valid @RequestBody CreateSubjectDTO dto) {
        return subjectService.create(dto);
    }


    @GetMapping("/{id}")
    public Subject getSubjectById(@PathVariable("id") Long id) {
        return subjectService.getById(id);
    }

    @GetMapping
    public List<Subject> getActiveSubjects() {
        return subjectService.getAllActive();
    }

    @GetMapping("/archived")
    public List<Subject> getArchivedSubjects() {
        return subjectService.getAllArchived();
    }

//    @GetMapping("/{id}/posts")
//    public List<Post> getCoursePosts(@PathVariable("id") Long id) {
//        return postService.getByCourseId(id);
//    }

//    @GetMapping("/{id}/activities")
//    public List<Activity> getCourseActivities(@PathVariable("id") Long id) {
//        return activityService.getByCourseId(id);
//    }
//
//    @GetMapping("/{id}/students")
//    public List<Student> getCourseStudents(@PathVariable("id") Long id) {
//        return new ArrayList<>(subjectService.getStudentsByCourseId(id));
//    }
//
//    @GetMapping("/{id}/teachers")
//    public List<Teacher> getCourseTeachers(@PathVariable("id") Long id) {
//        return new ArrayList<>(subjectService.getTeachersByCourseId(id));
//    }

    //    @ResponseStatus(HttpStatus.CREATED)
//    @PutMapping("/{id}/posts")
//    public void addPost(@PathVariable("id") Long id, @Valid @RequestBody CreatePostDTO dto) {
//        postService.create(id, dto);
//    }
//
//    @ResponseStatus(HttpStatus.CREATED)
//    @PutMapping("/{id}/activities")
//    public void addActivity(@PathVariable("id") Long id, @Valid @RequestBody CreateActivityDTO dto) {
//        activityService.create(id, dto);
//    }
//

    @PutMapping("/{id}")
    public Subject updateSubject(@PathVariable("id") Long id, @Valid @RequestBody UpdateSubjectDTO dto) {
        return subjectService.update(id, dto);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        subjectService.deleteById(id);
    }
}
