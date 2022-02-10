package com.example.recipes.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.recipes.core.entities.User;
import com.example.recipes.infrastructure.repositories.UserRepository;

@Component
public class UserSaveIfNotDuplicate{
@Autowired
UserRepository userRepo;

    public boolean saveIfNotDuplicate(User user) {
        if(userRepo.existsUserByEmail(user.getEmail())){
            return false;
        }
        userRepo.save(user);
        return true;
    }
}
