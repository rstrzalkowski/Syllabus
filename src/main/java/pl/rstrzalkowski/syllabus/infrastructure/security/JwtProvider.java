package pl.rstrzalkowski.syllabus.infrastructure.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class JwtProvider {

    private final String secret = "fnuefu327t8h2739fhj73fj32jfef";

    public String generateJwt(String username, Collection<? extends GrantedAuthority> roles) {
        return "TO BE IMPLEMENTED";
    }
}
