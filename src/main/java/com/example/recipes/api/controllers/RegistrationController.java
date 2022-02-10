package com.example.recipes.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.recipes.core.entities.User;
import com.example.recipes.core.services.UserSaveIfNotDuplicate;
import com.example.recipes.infrastructure.repositories.UserRepository;

@RestController
public class RegistrationController {
    @Autowired
    UserRepository userRepo;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserSaveIfNotDuplicate userSaveIfNotDuplicate;

    @PostMapping("/api/register")
    public void register(@Valid @RequestBody User user) {
        System.out.println("Input" + user.getEmail() + " " + user.getPassword());

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        if(userSaveIfNotDuplicate.saveIfNotDuplicate(user)) {
            throw new ResponseStatusException(HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
