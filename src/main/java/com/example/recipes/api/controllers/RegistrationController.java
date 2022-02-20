package com.example.recipes.api.controllers;

import com.example.recipes.core.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.recipes.core.entities.User;
import com.example.recipes.infrastructure.repositories.UserRepository;

import javax.validation.Valid;

@RestController
public class RegistrationController {
    @Autowired
    UserRepository userRepo;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @PostMapping("/api/register")
    public void register(@Valid @RequestBody User user) {
        System.out.println("Input" + user.getEmail() + " " + user.getPassword());

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        if (userDetailsServiceImpl.saveIfNotDuplicate(user)) {
            throw new ResponseStatusException(HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
