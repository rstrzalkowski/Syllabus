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
import pl.rstrzalkowski.syllabus.domain.Activity;
import pl.rstrzalkowski.syllabus.domain.Post;
import pl.rstrzalkowski.syllabus.domain.Realisation;
import pl.rstrzalkowski.syllabus.dto.create.CreateActivityDTO;
import pl.rstrzalkowski.syllabus.dto.create.CreatePostDTO;
import pl.rstrzalkowski.syllabus.dto.create.CreateRealisationDTO;
import pl.rstrzalkowski.syllabus.dto.update.UpdateRealisationDTO;
import pl.rstrzalkowski.syllabus.service.ActivityService;
import pl.rstrzalkowski.syllabus.service.PostService;
import pl.rstrzalkowski.syllabus.service.RealisationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/realisations")
public class RealisationController {
    
    private final RealisationService realisationService;
    private final PostService postService;
    private final ActivityService activityService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Realisation createRealisation(@Valid @RequestBody CreateRealisationDTO dto) {
        return realisationService.create(dto);
    }

    @GetMapping("/{id}")
    public Realisation getSubjectById(@PathVariable("id") Long id) {
        return realisationService.getById(id);
    }

    @GetMapping
    public List<Realisation> getActiveRealisations() {
        return realisationService.getAllActive();
    }

    @GetMapping("/archived")
    public List<Realisation> getArchivedRealisations() {
        return realisationService.getAllArchived();
    }

    @GetMapping("/{id}/posts")
    public List<Post> getRealisationPosts(@PathVariable("id") Long id) {
        return postService.getByRealisationId(id);
    }

    @GetMapping("/{id}/activities")
    public List<Activity> getRealisationActivities(@PathVariable("id") Long id) {
        return activityService.getByRealisationId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}/posts")
    public void addPost(@PathVariable("id") Long id, @Valid @RequestBody CreatePostDTO dto) {
        postService.create(id, dto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}/activities")
    public void addActivity(@PathVariable("id") Long id, @Valid @RequestBody CreateActivityDTO dto) {
        activityService.create(id, dto);
    }


    @PutMapping("/{id}")
    public Realisation updateRealisation(@PathVariable("id") Long id, @Valid @RequestBody UpdateRealisationDTO dto) {
        return realisationService.update(id, dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        realisationService.deleteById(id);
    }
}
