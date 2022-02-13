package net.vanderkast.wishlists.server.contract;

import org.springframework.lang.NonNull;

public interface Id {
    /**
     * @return positive numbers represents entity ID, or -1 if ID is not specified.
     */
    @NonNull
    default Long getId() {
        return -1L;
    }
}
