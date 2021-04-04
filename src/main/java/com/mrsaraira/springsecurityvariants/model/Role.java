package com.mrsaraira.springsecurityvariants.model;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Stream;

import static com.mrsaraira.springsecurityvariants.model.Permission.DEVELOPERS_READ;
import static java.util.stream.Collectors.toSet;

public enum Role {
    USER(Stream.of(DEVELOPERS_READ).collect(toSet())),
    ADMIN(Stream.of(DEVELOPERS_READ, Permission.DEVELOPERS_WRITE).collect(toSet()));

    @Getter
    private final Set<Permission> permissions;


    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }


    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissionName())).collect(toSet());
    }
}
