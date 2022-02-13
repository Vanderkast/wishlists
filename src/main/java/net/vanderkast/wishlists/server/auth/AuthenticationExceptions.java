package net.vanderkast.wishlists.server.auth;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationExceptions {
    public static final AuthenticationException EMAIL_NOT_SPECIFIED = new AuthenticationException("Email must be specified") {
    };
    public static final AuthenticationException PASSWORD_NOT_SPECIFIED = new AuthenticationException("Password must be specified") {
    };
    public static final AuthenticationException PASSWORD_DOES_NOT_MATCH = new AuthenticationException("Password does not match.") {
    };

    private AuthenticationExceptions() {
    }
}
