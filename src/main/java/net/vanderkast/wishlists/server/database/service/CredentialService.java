package net.vanderkast.wishlists.server.database.service;

import net.vanderkast.wishlists.server.contract.Id;
import net.vanderkast.wishlists.server.entity.Credentials;

import java.util.Optional;

public interface CredentialService {
    Optional<Credentials> findCredentialsByEmail(String email);

    Id save(Credentials credentials);
}
