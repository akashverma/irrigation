package com.example.irrigation.controllers;

import com.example.irrigation.config.security.JwtUtil;
import com.example.irrigation.config.security.MyUserDetailsService;
import com.example.irrigation.dtos.AuthenticateRequest;
import com.example.irrigation.dtos.AuthenticateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticateRequest authenticateRequest) throws Exception {

        //Step1. Trying to authenticate the provided creds in AuthenticateRequest
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticateRequest.getUsername(), authenticateRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password ", e);
        }

        //Step2. If you reached till here, it means authentication completed successfully

        // so fetch the user for these credentials and generate the jwt for that user and return it
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticateRequest.getUsername());
        String jwt = jwtUtil.generateToken(userDetails);
        AuthenticateResponse authenticateResponse = new AuthenticateResponse(jwt);

        return ResponseEntity.ok().body(authenticateResponse);

    }
}
