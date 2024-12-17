package com.example.demo.Details;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.config.CustomUserDetails;
import com.example.demo.models.User;
import com.example.demo.repository.RepositoryUser;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private RepositoryUser repositoryUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repositoryUser.findByUsername(username);
        if (user.isPresent()) {
            return new CustomUserDetails(user.get());
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
    }
}
