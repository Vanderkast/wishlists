package net.vanderkast.wishlists.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.vanderkast.wishlists.server.contract.Id;

public interface Credentials extends Id {
    String getEmail();

    @JsonIgnore
    String getPassword();
}
