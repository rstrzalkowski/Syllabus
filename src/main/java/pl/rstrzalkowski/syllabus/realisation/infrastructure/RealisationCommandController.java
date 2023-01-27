package pl.rstrzalkowski.syllabus.realisation.infrastructure;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.realisation.application.RealisationCommandHandler;
import pl.rstrzalkowski.syllabus.realisation.application.command.ArchiveRealisationCommand;
import pl.rstrzalkowski.syllabus.realisation.application.command.CreateRealisationCommand;
import pl.rstrzalkowski.syllabus.realisation.application.command.UpdateRealisationCommand;

@RestController
@RequestMapping("/realisations")
@RequiredArgsConstructor
public class RealisationCommandController {

    private final RealisationCommandHandler realisationCommandHandler;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createSubject(@Valid @RequestBody CreateRealisationCommand command) {
        realisationCommandHandler.handle(command);
    }

    @PutMapping
    public void updateSubject(@Valid @RequestBody UpdateRealisationCommand command) {
        realisationCommandHandler.handle(command);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void archiveById(@PathVariable("id") Long id) {
        realisationCommandHandler.handle(new ArchiveRealisationCommand(id));
    }
}
