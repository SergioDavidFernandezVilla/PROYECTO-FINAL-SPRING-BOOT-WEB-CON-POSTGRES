package com.example.demo.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.models.User;
import com.example.demo.repository.RepositoryUser;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private RepositoryUser repositoryUser;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/public/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .formLogin(form -> form.loginPage("/login").permitAll())
                .logout(logout -> logout.permitAll());

                return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Codificador de contraseñas seguro
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return nombre -> {
            Optional<User> user = repositoryUser.findByUsername(nombre);
            if (user.isPresent()) {
                return new CustomUserDetails(user.get());
            } else {
                throw new UsernameNotFoundException("Usuario no encontrado: " + nombre);
            }
        };
    }
}


