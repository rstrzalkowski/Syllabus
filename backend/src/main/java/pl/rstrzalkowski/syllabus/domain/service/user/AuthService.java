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
import pl.rstrzalkowski.syllabus.domain.model.RegistrationToken;
import pl.rstrzalkowski.syllabus.domain.model.Role;
import pl.rstrzalkowski.syllabus.domain.model.SchoolClass;
import pl.rstrzalkowski.syllabus.domain.model.User;
import pl.rstrzalkowski.syllabus.infrastructure.repository.TokenRepository;
import pl.rstrzalkowski.syllabus.infrastructure.repository.UserRepository;
import pl.rstrzalkowski.syllabus.infrastructure.security.JwtProvider;
import pl.rstrzalkowski.syllabus.infrastructure.security.JwtResponse;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

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
        return new JwtResponse(jwtProvider.generateJwt(user.getEmail(), user.getRole()));
    }

    public void register(RegisterCommand command) {
        RegistrationToken token = tokenRepository.findById(command.getRegistrationToken())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE));

        User user = new User();
        user.setEmail(command.getEmail());
        user.setFirstName(command.getFirstName());
        user.setLastName(command.getLastName());
        user.setPersonalId(command.getPersonalId());
        user.setPassword(passwordEncoder.encode(command.getPassword()));
        user.setRole(token.getRole());
        user.setImageUrl("https://cdn-icons-png.flaticon.com/512/149/149071.png");

        SchoolClass schoolClass = token.getSchoolClass();
        if (token.getRole() == Role.STUDENT && schoolClass != null) {
            user.setSchoolClass(schoolClass);
        }

        try {
            userRepository.save(user);
            tokenRepository.delete(token);
        } catch (Exception e) {
            throw new UserAlreadyRegisteredException();
        }
    }

}
