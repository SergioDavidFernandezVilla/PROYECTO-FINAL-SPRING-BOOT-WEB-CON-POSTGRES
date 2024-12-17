package com.example.demo.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.models.User;


// Clase interna o separada que implementa UserDetails
public class CustomUserDetails implements UserDetails {
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // Devuelve los roles o permisos si los tienes definidos en tu entidad User
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Ajusta según tu lógica
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Ajusta según tu lógica
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Ajusta según tu lógica
    }

    @Override
    public boolean isEnabled() {
        return true; // Ajusta según tu lógica
    }
}
