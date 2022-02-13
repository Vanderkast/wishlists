package net.vanderkast.wishlists.server.user_federation;

import net.vanderkast.wishlists.server.contract.Id;
import net.vanderkast.wishlists.server.contract.OrError;
import net.vanderkast.wishlists.server.entity.Credentials;

public interface CreateUser {
    OrError<Id> createUser(Credentials credentials);
}
