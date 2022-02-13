package net.vanderkast.wishlists.server.entity;

import net.vanderkast.wishlists.server.contract.Id;

import java.time.LocalDate;
import java.util.List;

public interface Wishlist extends Id {
    User getOwner();

    String getDescription();

    LocalDate getTillDate();

    Boolean isPublic();

    List<WishlistItem> getItems();
}
