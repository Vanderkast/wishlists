package net.vanderkast.wishlists.server.auth;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordHash {
    private PasswordHash() {
    }

    public static String hash(String password) {
        return DigestUtils.sha256Hex(password);
    }
}
