package pl.rstrzalkowski.syllabus.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.domain.Activity;
import pl.rstrzalkowski.syllabus.dto.update.UpdateActivityDTO;
import pl.rstrzalkowski.syllabus.service.ActivityService;

import java.util.List;


@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;


    @GetMapping("/{id}")
    public Activity getActivityById(@PathVariable("id") Long id) {
        return activityService.getById(id);
    }

    @GetMapping
    public List<Activity> getAllActivities() {
        return activityService.getAll();
    }

    @PutMapping("/{id}")
    public Activity updateActivity(@PathVariable("id") Long id, @Valid @RequestBody UpdateActivityDTO dto) {
        return activityService.update(id, dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        activityService.deleteById(id);
    }
}
