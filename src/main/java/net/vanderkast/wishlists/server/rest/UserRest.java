package net.vanderkast.wishlists.server.rest;

import lombok.AllArgsConstructor;
import net.vanderkast.wishlists.server.contract.Id;
import net.vanderkast.wishlists.server.contract.OrError;
import net.vanderkast.wishlists.server.dto.CredentialsDto;
import net.vanderkast.wishlists.server.entity.User;
import net.vanderkast.wishlists.server.user_federation.CreateUser;
import net.vanderkast.wishlists.server.user_federation.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserRest {
    private final CreateUser create;
    private final UserInformation userInformation;

    @PostMapping(path = "/signup")
    @ResponseBody
    public OrError<Id> signup(@RequestBody CredentialsDto credentials) {
        return create.createUser(credentials);
    }

    @GetMapping(path = "/me")
    @ResponseBody
    public OrError<User> me(@RequestHeader(name = "Authorization") String authHeader) {
        var emailPassword = new String(Base64.getDecoder().decode(authHeader.substring(6))).split(":", 2); // "Basic log:pass"
        if (emailPassword.length != 2)
            return OrError.error("Login is not present in authorization header");
        var email = emailPassword[0];
        var resp = userInformation.findUserByEmail(email)
                .map(OrError::of);
        return resp.orElse(OrError.error("User with passed email was not found."));
    }
}
