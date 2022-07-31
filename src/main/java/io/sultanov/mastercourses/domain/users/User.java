package io.sultanov.mastercourses.domain.users;

import io.sultanov.mastercourses.enums.UserRole;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    @Enumerated(value = EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    Set<UserRole> roles = new HashSet<>();

    @Column(name = "access")
    private Boolean accessibility = false;

    public void addRole(UserRole role) {
        roles.add(role);
    }
}
