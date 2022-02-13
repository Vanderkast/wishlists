package net.vanderkast.wishlists.server.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.vanderkast.wishlists.server.entity.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("not null")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity extends CredentialsEntity implements User {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "friends")
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserEntity> friends;
}
