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
import pl.rstrzalkowski.syllabus.domain.Grade;
import pl.rstrzalkowski.syllabus.dto.update.UpdateGradeDTO;
import pl.rstrzalkowski.syllabus.service.GradeService;

import java.util.List;


@RestController
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;


    @GetMapping("/{id}")
    public Grade getGradeById(@PathVariable("id") Long id) {
        return gradeService.getById(id);
    }

    @GetMapping
    public List<Grade> getAllGrades() {
        return gradeService.getAll();
    }

    @PutMapping("/{id}")
    public Grade updateGrade(@PathVariable("id") Long id, @Valid @RequestBody UpdateGradeDTO dto) {
        return gradeService.update(id, dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        gradeService.deleteById(id);
    }
}
