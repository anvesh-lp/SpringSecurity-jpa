package com.anvesh.springsecurityjpa.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.anvesh.springsecurityjpa.roles.UserRoles.USER;

@Entity
@Table(name = "User")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "User name Cannot be empty")
    private String username;

    private String firstname;

    private String lastname;

    @Size(min = 5, message = "password should be greater than 5 less than 15")
    @NotNull(message = "Password Cannot be empty")
    private String password;
    private boolean active;

    @Column(columnDefinition = "varchar(255) default 'ROLE_USER'")
    private String roles = "ROLE_" + USER.name();

}
