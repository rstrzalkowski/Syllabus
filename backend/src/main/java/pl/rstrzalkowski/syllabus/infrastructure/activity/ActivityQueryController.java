package pl.rstrzalkowski.syllabus.infrastructure.activity;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.application.handler.activity.ActivityQueryHandler;
import pl.rstrzalkowski.syllabus.application.query.activity.GetActivityByIdQuery;
import pl.rstrzalkowski.syllabus.domain.model.Activity;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
public class ActivityQueryController {

    private final ActivityQueryHandler activityQueryHandler;

    @GetMapping("/{id}")
    public Activity getActivityById(@PathVariable("id") Long id) {
        return activityQueryHandler.handle(new GetActivityByIdQuery(id));
    }
}
