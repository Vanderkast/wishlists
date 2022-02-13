package net.vanderkast.wishlists.server.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public interface Error {
    @JsonUnwrapped
    @JsonProperty(value = "error")
    String getMessage();
}
