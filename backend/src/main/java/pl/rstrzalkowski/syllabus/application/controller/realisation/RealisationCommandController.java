package pl.rstrzalkowski.syllabus.application.controller.realisation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.application.command.realisation.ArchiveRealisationCommand;
import pl.rstrzalkowski.syllabus.application.command.realisation.CreateRealisationCommand;
import pl.rstrzalkowski.syllabus.application.command.realisation.UpdateRealisationCommand;
import pl.rstrzalkowski.syllabus.application.handler.realisation.RealisationCommandHandler;

@RestController
@RequestMapping("/realisations")
@RequiredArgsConstructor
public class RealisationCommandController {

    private final RealisationCommandHandler realisationCommandHandler;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Secured({"DIRECTOR", "OFFICE", "ADMIN"})
    public void createRealisation(@Valid @RequestBody CreateRealisationCommand command) {
        realisationCommandHandler.handle(command);
    }

    @PutMapping("/{id}")
    @Secured({"DIRECTOR", "OFFICE", "ADMIN"})
    public void updateRealisation(@PathVariable("id") Long id, @Valid @RequestBody UpdateRealisationCommand command) {
        command.setId(id);
        realisationCommandHandler.handle(command);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Secured({"DIRECTOR", "ADMIN"})
    public void archiveById(@PathVariable("id") Long id) {
        realisationCommandHandler.handle(new ArchiveRealisationCommand(id));
    }
}
