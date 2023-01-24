package pl.rstrzalkowski.syllabus.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.domain.User;
import pl.rstrzalkowski.syllabus.dto.create.CreateUserDTO;
import pl.rstrzalkowski.syllabus.dto.update.UpdateUserDTO;
import pl.rstrzalkowski.syllabus.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public User createUser(@Valid @RequestBody CreateUserDTO dto) {
        return userService.create(dto);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/students")
    public List<User> getAllStudents() {
        return userService.getAllStudents();
    }

    @GetMapping("/teachers")
    public List<User> getAllTeachers() {
        return userService.getAllTeachers();
    }

    @GetMapping("/admins")
    public List<User> getAllAdmins() {
        return userService.getAllAdmins();
    }

    @GetMapping("/offices")
    public List<User> getAllOffices() {
        return userService.getAllOffices();
    }

    @GetMapping("/directors")
    public List<User> getAllDirectors() {
        return userService.getAllDirectors();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @Valid @RequestBody UpdateUserDTO dto) {
        return userService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }
}
