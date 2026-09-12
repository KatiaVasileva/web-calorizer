package com.vasileva.calorizer.model.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
    ADMIN,
    USER;

    public SimpleGrantedAuthority toAuthority() {
        return new SimpleGrantedAuthority("ROLE_" + this.name());
    }
}
