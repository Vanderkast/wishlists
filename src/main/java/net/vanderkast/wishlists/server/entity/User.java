package net.vanderkast.wishlists.server.entity;

import net.vanderkast.wishlists.server.contract.Id;

import java.time.LocalDate;
import java.util.List;

public interface User extends Id {
    String getFirstName();

    String getLastName();

    LocalDate getBirthday();

    List<? extends User> getFriends();
}
