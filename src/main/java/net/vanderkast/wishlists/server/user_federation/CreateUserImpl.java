package net.vanderkast.wishlists.server.user_federation;

import lombok.AllArgsConstructor;
import net.vanderkast.wishlists.server.auth.PasswordHash;
import net.vanderkast.wishlists.server.contract.Id;
import net.vanderkast.wishlists.server.contract.OrError;
import net.vanderkast.wishlists.server.database.service.CredentialService;
import net.vanderkast.wishlists.server.dto.CredentialsDto;
import net.vanderkast.wishlists.server.entity.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CreateUserImpl implements CreateUser {
    private static final Predicate<String> INVALID_EMAIL // [2+ symbols]@[2+ symbols].[2 or 3 symbols] ~ so@so.so
            = Pattern.compile("^[a-z0-9.-]{2,}@[a-z0-9-.]{2,}.{2,3}$").asPredicate().negate();
    private static final Predicate<String> INVALID_PASSWORD // just 5 symbols at least
            = Pattern.compile("^[.\\S]{5,}$").asPredicate().negate();

    private final CredentialService credentialsService;

    @Override
    @Transactional
    public OrError<Id> createUser(Credentials credentials) {
        if (credentials.getEmail() == null || credentials.getPassword() == null)
            return OrError.error("Email or password is not specified.");
        if (INVALID_EMAIL.test(credentials.getEmail()))
            return OrError.error("Email is invalid.");
        if (INVALID_PASSWORD.test(credentials.getPassword()))
            return OrError.error("Password should contain at least 5 symbols without whitespaces.");
        if (credentialsService.findCredentialsByEmail(credentials.getEmail()).isPresent())
            return OrError.error("User with passed email already exists.");

        var entity = new CredentialsDto(
                credentials.getEmail(),
                PasswordHash.hash(credentials.getPassword()));
        return OrError.ofNullable(credentialsService.save(entity));
    }
}
