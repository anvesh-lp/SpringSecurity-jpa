package com.anvesh.springsecurityjpa.authentications;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {
    private final String jwtToken;
}
