package pl.rstrzalkowski.syllabus.activity.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.activity.application.ActivityQueryHandler;
import pl.rstrzalkowski.syllabus.activity.application.query.GetActivityByIdQuery;
import pl.rstrzalkowski.syllabus.activity.domain.model.Activity;

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
