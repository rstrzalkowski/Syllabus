package pl.rstrzalkowski.syllabus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rstrzalkowski.syllabus.domain.Role;
import pl.rstrzalkowski.syllabus.domain.User;
import pl.rstrzalkowski.syllabus.dto.create.CreateUserDTO;
import pl.rstrzalkowski.syllabus.dto.update.UpdateUserDTO;
import pl.rstrzalkowski.syllabus.exception.user.UserNotFoundException;
import pl.rstrzalkowski.syllabus.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        User student = new User();
        student.setPassword("pass");
        student.setEmail("student");
        student.setRole(Collections.singletonList(Role.STUDENT));

        User teacher = new User();
        teacher.setPassword("pass");
        teacher.setEmail("teacher");
        teacher.setRole(Collections.singletonList(Role.TEACHER));

        userRepository.save(student);
        userRepository.save(teacher);
    }

    private final UserRepository userRepository;


    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public User create(CreateUserDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return userRepository.save(user);
    }

    public User update(Long id, UpdateUserDTO dto) {
        User user = getById(id);
        user.setEmail(dto.getEmail() == null ? user.getEmail() : dto.getEmail());
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        try {
            User user = getById(id);
            userRepository.delete(user);
        } catch (UserNotFoundException ignored) {
        }
    }

    public List<User> getAllStudents() {
        return userRepository.findByRoleContains(Role.STUDENT);
    }

    public List<User> getAllTeachers() {
        return userRepository.findByRoleContains(Role.TEACHER);
    }

    public List<User> getAllAdmins() {
        return userRepository.findByRoleContains(Role.ADMIN);
    }

    public List<User> getAllOffices() {
        return userRepository.findByRoleContains(Role.OFFICE);
    }

    public List<User> getAllDirectors() {
        return userRepository.findByRoleContains(Role.DIRECTOR);
    }
}
