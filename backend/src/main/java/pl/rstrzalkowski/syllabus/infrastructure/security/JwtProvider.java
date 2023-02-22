package pl.rstrzalkowski.syllabus.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.rstrzalkowski.syllabus.domain.model.Role;

import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;

    public String generateJwt(String username, Role role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMillis))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Jws<Claims> parseJWT(String jwt) throws SignatureException {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwt);
    }

    public boolean validateToken(String jwt) {
        parseJWT(jwt);
        return true;
    }

    public String getToken(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }
}
