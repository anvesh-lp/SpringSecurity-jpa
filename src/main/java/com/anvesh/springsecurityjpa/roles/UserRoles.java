package com.anvesh.springsecurityjpa.roles;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.anvesh.springsecurityjpa.roles.UserPermissions.*;


// Add required  roles if needed
public enum UserRoles {
    ADMIN(Set.of(READ, MODIFY, DELETE, ADD)),
    moderator(Set.of(READ, MODIFY)),
    USER(Set.of()),
    ;

    private final Set<UserPermissions> permissions;

    UserRoles(Set<UserPermissions> perm) {
        this.permissions = perm;
    }

//    Return a set of granted authorities to users

    public Set<GrantedAuthority> getAuthorities() {
        return permissions.stream().map(x -> new SimpleGrantedAuthority(x.name())).collect(Collectors.toSet());
    }
}
