package net.vanderkast.wishlists.server.database.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.vanderkast.wishlists.server.entity.Credentials;

import javax.persistence.*;

@Entity(name = "users")
@Table(indexes = {@Index(columnList = "email", unique = true)})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CredentialsEntity implements Credentials {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    private String password;
}
