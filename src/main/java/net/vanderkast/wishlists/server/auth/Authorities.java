package net.vanderkast.wishlists.server.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class Authorities {
    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final List<GrantedAuthority> USER_AUTHORITIES = List.of(new SimpleGrantedAuthority(ROLE_USER));
    public static final List<GrantedAuthority> ADMIN_AUTHORITIES = List.of(new SimpleGrantedAuthority(ROLE_ADMIN));
    private Authorities() {
    }
}
