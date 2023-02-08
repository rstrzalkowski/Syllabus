package pl.rstrzalkowski.syllabus.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@Table(name = "APP_USER")
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity implements UserDetails {


    @Column(unique = true)
    private String username;

    @JsonIgnore
    private String password;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String personalId;

    private String description = "";

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private SchoolClass schoolClass;

    @OneToOne
    private User child;

    private boolean locked;

    boolean archived;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return !archived;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
