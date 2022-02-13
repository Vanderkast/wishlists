package net.vanderkast.wishlists.server.entity;

import net.vanderkast.wishlists.server.contract.Id;

import java.util.Optional;

public interface WishlistItem extends Id {
    String getName();

    Optional<Id> getTakenBy();
}
