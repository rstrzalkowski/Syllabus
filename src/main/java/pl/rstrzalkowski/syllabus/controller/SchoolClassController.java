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
import pl.rstrzalkowski.syllabus.domain.SchoolClass;
import pl.rstrzalkowski.syllabus.dto.create.CreateSchoolClassDTO;
import pl.rstrzalkowski.syllabus.dto.update.UpdateSchoolClassDTO;
import pl.rstrzalkowski.syllabus.service.SchoolClassService;

import java.util.List;


@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
public class SchoolClassController {

    private final SchoolClassService schoolClassService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SchoolClass createClass(@Valid @RequestBody CreateSchoolClassDTO dto) {
        return schoolClassService.create(dto);
    }
    
    @GetMapping("/{id}")
    public SchoolClass getClassById(@PathVariable("id") Long id) {
        return schoolClassService.getById(id);
    }

    @GetMapping
    public List<SchoolClass> getActiveClasses() {
        return schoolClassService.getAllActive();
    }

    @GetMapping("/archived")
    public List<SchoolClass> getArchivedClasses() {
        return schoolClassService.getAllArchived();
    }

    @PutMapping("/{id}")
    public SchoolClass updateClass(@PathVariable("id") Long id, @Valid @RequestBody UpdateSchoolClassDTO dto) {
        return schoolClassService.update(id, dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        schoolClassService.deleteById(id);
    }
}
