package net.vanderkast.wishlists.server.auth;

import lombok.AllArgsConstructor;
import net.vanderkast.wishlists.server.database.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
public class BasicAuthenticationProvider implements AuthenticationProvider {
    private final CredentialService credentialsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var email = authentication.getName();
        if (email == null)
            throw AuthenticationExceptions.EMAIL_NOT_SPECIFIED;
        var password = authentication.getCredentials().toString();
        if (password == null)
            throw AuthenticationExceptions.PASSWORD_NOT_SPECIFIED;
        var passHash = PasswordHash.hash(password);
        if (!credentialsService.findCredentialsByEmail(email)
                .map(creds -> creds.getPassword().equals(passHash))
                .orElse(false))
            throw AuthenticationExceptions.PASSWORD_DOES_NOT_MATCH;
        return new UsernamePasswordAuthenticationToken(email, passHash, Authorities.USER_AUTHORITIES);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
