package pl.rstrzalkowski.syllabus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rstrzalkowski.syllabus.domain.User;
import pl.rstrzalkowski.syllabus.dto.create.CreateUserDTO;
import pl.rstrzalkowski.syllabus.dto.update.UpdateUserDTO;
import pl.rstrzalkowski.syllabus.exception.user.UserNotFoundException;
import pl.rstrzalkowski.syllabus.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

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

//    public List<User> getAllStudents() {
//        return userRepository.findByRoleName(RoleEnum.STUDENT);
//    }
//
//    public List<User> getAllTeachers() {
//        return userRepository.findByRoleName(RoleEnum.TEACHER);
//    }
//
//    public List<User> getAllAdmins() {
//        return userRepository.findByRoleName(RoleEnum.ADMIN);
//    }
//
//    public List<User> getAllOffices() {
//        return userRepository.findByRoleName(RoleEnum.OFFICE);
//    }
//
//    public List<User> getAllDirectors() {
//        return userRepository.findByRoleName(RoleEnum.DIRECTOR);
//    }
}
