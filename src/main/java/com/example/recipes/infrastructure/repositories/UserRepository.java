package com.example.recipes.infrastructure.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.recipes.core.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByEmail(String email);
    boolean existsUserByEmail(String email);
}