package net.vanderkast.wishlists.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.vanderkast.wishlists.server.entity.Credentials;

@AllArgsConstructor
@Getter
public class CredentialsDto implements Credentials {
    private final String email;
    @JsonProperty
    private final String password;
}
