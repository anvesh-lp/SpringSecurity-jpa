package com.anvesh.springsecurityjpa.controllers;

import com.anvesh.springsecurityjpa.authentications.AuthenticationRequest;
import com.anvesh.springsecurityjpa.authentications.AuthenticationResponse;
import com.anvesh.springsecurityjpa.jwts.JwtUtils;
import com.anvesh.springsecurityjpa.providers.UserDetailProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloResource {

    private final AuthenticationManager authenticationManager;

    private final UserDetailProvider userDetailsService;

    @Autowired
    private JwtUtils utils;

    public HelloResource(AuthenticationManager authenticationManager, UserDetailProvider userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "<h1>hello worlds</h1>";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            e.getStackTrace();
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = utils.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(token));

    }
}
