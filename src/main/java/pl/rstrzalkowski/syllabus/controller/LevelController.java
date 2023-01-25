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
import pl.rstrzalkowski.syllabus.domain.Level;
import pl.rstrzalkowski.syllabus.dto.create.CreateLevelDTO;
import pl.rstrzalkowski.syllabus.dto.update.UpdateLevelDTO;
import pl.rstrzalkowski.syllabus.service.LevelService;

import java.util.List;


@RestController
@RequestMapping("/levels")
@RequiredArgsConstructor
public class LevelController {

    private final LevelService levelService;


    @PostMapping
    public Level createLevel(CreateLevelDTO dto) {
        return levelService.create(dto);
    }

    @GetMapping("/{id}")
    public Level getLevelById(@PathVariable("id") Long id) {
        return levelService.getById(id);
    }

    @GetMapping
    public List<Level> getAllLevels() {
        return levelService.getAll();
    }

    @PutMapping("/{id}")
    public Level updateLevel(@PathVariable("id") Long id, @Valid @RequestBody UpdateLevelDTO dto) {
        return levelService.update(id, dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        levelService.deleteById(id);
    }
}
