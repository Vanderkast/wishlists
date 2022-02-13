package net.vanderkast.wishlists.server.database.service;

import lombok.AllArgsConstructor;
import net.vanderkast.wishlists.server.contract.Id;
import net.vanderkast.wishlists.server.database.entity.CredentialsEntity;
import net.vanderkast.wishlists.server.database.repository.CredentialRepository;
import net.vanderkast.wishlists.server.database.repository.UserRepository;
import net.vanderkast.wishlists.server.entity.Credentials;
import net.vanderkast.wishlists.server.entity.User;
import net.vanderkast.wishlists.server.user_federation.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserService implements UserInformation, CredentialService {
    private final UserRepository users;
    private final CredentialRepository credentials;

    @Override
    public Optional<User> findUserByEmail(String email) {
        return users.findUserEntityByEmailEquals(email).map(User.class::cast);
    }

    @Override
    public Optional<Credentials> findCredentialsByEmail(String email) {
        return credentials.findByEmail(email).map(Credentials.class::cast);
    }

    @Override
    public Id save(Credentials data) {
        return credentials.save(new CredentialsEntity(null, data.getEmail(), data.getPassword()));
    }
}
