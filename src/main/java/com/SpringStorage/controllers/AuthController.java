package com.SpringStorage.controllers;

import com.SpringStorage.entities.User;
import com.SpringStorage.security.JwtRequest;
import com.SpringStorage.security.JwtResponse;
import com.SpringStorage.security.JwtTokenUtil;
import com.SpringStorage.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            System.out.println(authRequest.getUsername());
            System.out.println(authRequest.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/reg")
    public void createRegToken(@RequestBody JwtRequest authRequest) {
        User user = userService.findByUsername(authRequest.getUsername()).orElse(null);
        System.out.println(authRequest.getUsername());
        System.out.println(authRequest.getPassword());
        if (user != null) {
            System.out.println("user un null");
            return;
        }
        user = userService.findByEmail(authRequest.getEmail()).orElse(null);
        if (user != null) {
            System.out.println("User em null");
            return;
        }
        userService.saveUser(authRequest.getUsername(), authRequest.getEmail(), authRequest.getPassword());

//        return(authRequest);
    }
}
