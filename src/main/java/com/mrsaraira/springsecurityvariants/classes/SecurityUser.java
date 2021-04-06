package com.mrsaraira.springsecurityvariants.classes;

import com.mrsaraira.springsecurityvariants.model.Status;
import com.mrsaraira.springsecurityvariants.model.User;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Value
public class SecurityUser implements UserDetails {

    private String email;
    private String hashedPassword;
    private Set<SimpleGrantedAuthority> authorities;
    private boolean isActive;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    @Override
    public String getPassword() {
        return hashedPassword;
    }


    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }


    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }


    @Override
    public boolean isEnabled() {
        return isActive;
    }


    public static SecurityUser fromUser(User user) {
        return new SecurityUser(user.getEmail(), user.getHashedPassword(),
                user.getRole().getAuthorities(), Status.ACTIVE.equals(user.getStatus()));
    }

}
