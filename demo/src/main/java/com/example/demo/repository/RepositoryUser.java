package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.UserEntity;

public interface RepositoryUser extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    Optional<UserEntity> findByUsername(String username);
    
}
