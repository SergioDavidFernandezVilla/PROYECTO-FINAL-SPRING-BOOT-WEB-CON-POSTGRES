package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.UserEntity;

public interface RepositoryUser extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
    
}
