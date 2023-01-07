package pl.rstrzalkowski.syllabus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rstrzalkowski.syllabus.domain.user.Admin;
import pl.rstrzalkowski.syllabus.domain.user.Student;
import pl.rstrzalkowski.syllabus.domain.user.Teacher;
import pl.rstrzalkowski.syllabus.domain.user.User;
import pl.rstrzalkowski.syllabus.dto.CreateUserDTO;
import pl.rstrzalkowski.syllabus.exception.UserNotFoundException;
import pl.rstrzalkowski.syllabus.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void createInitialUsers() {
        User student = new Student("student@wp.pl", "pass");
        Teacher teacher = new Teacher("teacher@wp.pl", "pass");
        Admin admin = new Admin("admin@wp.pl", "pass");

        userRepository.save(student);
        userRepository.save(teacher);
        userRepository.save(admin);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public User create(CreateUserDTO dto) {
        User user = new Student();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(User.Role.STUDENT);

        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllStudents() {
        return userRepository.findUserByRole(User.Role.STUDENT);
    }

    public List<User> getAllTeachers() {
        return userRepository.findUserByRole(User.Role.TEACHER);
    }

    public List<User> getAllAdmins() {
        return userRepository.findUserByRole(User.Role.ADMIN);
    }
}
