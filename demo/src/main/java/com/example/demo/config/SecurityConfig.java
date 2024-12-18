package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    AuthenticationConfiguration authenticationConfiguration;
    
    // METODO SECURITY FILTER CHAIN
    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
        .csrf(csrf -> csrf.disable())
        .httpBasic(Customizer.withDefaults())
        .sessionManagement(sesion -> sesion.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(http -> {
            // CONFIGURAR ENDPOINTS PUBLICOS
            http.requestMatchers(HttpMethod.GET, "/auth/get").permitAll();

            // CONFIGURAR ENDPOINTS PRIVADOS
            http.requestMatchers(HttpMethod.POST, "/auth/post").hasAnyRole("ADMIN", "DEVELOPER");
            http.requestMatchers(HttpMethod.PATCH, "/auth/patch").hasAnyAuthority("REFACTOR");

            // CONFIGURAR EL RESTO ENDPOINTS POR DEFECTO
            http.anyRequest().denyAll();
        })
        .build();
    } */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {

                
                    // RUTA CRUD USER
                    http.requestMatchers(HttpMethod.POST, "/user").hasAnyRole("ADMIN", "DEVELOPER");
                    http.requestMatchers(HttpMethod.GET, "/user").hasAnyRole("ADMIN", "DEVELOPER");

                    
                    // RUTA CREAR ANUNCIO
                    http.requestMatchers(HttpMethod.GET, "/new").hasAnyAuthority("ADMIN", "USER");
                    http.requestMatchers(HttpMethod.POST, "/new").hasAnyAuthority("ADMIN", "USER");
                    

                    // RUTA INICIO
                    http.requestMatchers(HttpMethod.GET, "/").hasAnyRole("ADMIN", "DEVELOPER", "USER");

                    // RUTA COMPROBAR ALGUNAS IDEAS...
                    http.requestMatchers(HttpMethod.POST, "/auth/post").hasAnyRole("ADMIN", "DEVELOPER");
                    http.requestMatchers(HttpMethod.PATCH, "/auth/patch").hasAnyAuthority("REFACTOR");
                    http.requestMatchers("/auth").permitAll();

                     // CONFIGURAR EL RESTO ENDPOINTS POR DEFECTO
                    http.anyRequest().denyAll();
                }).formLogin(Customizer.withDefaults())
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceIml userDetailService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
