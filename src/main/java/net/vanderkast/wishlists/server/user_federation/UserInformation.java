package net.vanderkast.wishlists.server.user_federation;

import net.vanderkast.wishlists.server.entity.User;

import java.util.Optional;

public interface UserInformation {
    Optional<User> findUserByEmail(String email);
}
