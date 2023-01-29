package pl.rstrzalkowski.syllabus.domain.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.rstrzalkowski.syllabus.application.command.user.LoginCommand;
import pl.rstrzalkowski.syllabus.application.command.user.RegisterCommand;
import pl.rstrzalkowski.syllabus.domain.exception.user.UserAlreadyRegisteredException;
import pl.rstrzalkowski.syllabus.domain.model.Role;
import pl.rstrzalkowski.syllabus.domain.model.User;
import pl.rstrzalkowski.syllabus.domain.repository.UserRepository;
import pl.rstrzalkowski.syllabus.infrastructure.security.JwtProvider;
import pl.rstrzalkowski.syllabus.infrastructure.security.JwtResponse;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public JwtResponse login(LoginCommand command) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(command.getUsername(), command.getPassword()));
        } catch (LockedException le) {
            throw new ResponseStatusException(HttpStatus.LOCKED);
        } catch (AccountExpiredException aee) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        } catch (AuthenticationException ae) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        User user = (User) authentication.getPrincipal();
        return new JwtResponse(jwtProvider.generateJwt(user.getUsername(), user.getAuthorities()));
    }

    public void register(RegisterCommand command) {
        User user = new User();
        user.setUsername(command.getUsername());
        user.setFirstName(command.getFirstName());
        user.setLastName(command.getLastName());
        user.setPersonalId(command.getPersonalId());

        //TODO set role and class according to code
        user.setRoles(Collections.singletonList(Role.STUDENT));
        user.setPassword(passwordEncoder.encode(command.getPassword()));

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserAlreadyRegisteredException();
        }
    }

}
