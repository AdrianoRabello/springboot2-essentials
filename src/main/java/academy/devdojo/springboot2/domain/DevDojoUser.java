package academy.devdojo.springboot2.domain;

import lombok.*;
import net.bytebuddy.description.type.PackageDescription;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @autor adriano rabello 15/07/2021 5:00 PM
 **/

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class DevDojoUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;
    private String authorities;


    @Override
   public Collection<? extends GrantedAuthority> getAuthorities(){

        return Arrays.stream(authorities.split(","))
                .map(authorities -> new SimpleGrantedAuthority(authorities)).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }
}
