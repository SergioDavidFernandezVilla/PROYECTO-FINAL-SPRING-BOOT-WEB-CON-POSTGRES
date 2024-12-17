package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.User;

public interface RepositoryUser extends JpaRepository<User, Long> {

    User findByEmail(String email);

    Optional<User> findByUsername(String username);
    
}
