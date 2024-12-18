package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.UserEntity;
import com.example.demo.services.ServicesUser;

@RestController
@RequestMapping("/user")
public class ControllerUser {
    
    @Autowired
    private ServicesUser servicesUser;

    // METODO CREATE
    @PreAuthorize("permitAll()")
    @PostMapping("/create")
    public UserEntity CreateUser(@RequestBody UserEntity user){
        return servicesUser.CreateUser(user);
    }

    // METODO GET LIST
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/list")
    public List<UserEntity> GetListUsers(){
        return servicesUser.GetListUsers();
    }

    // METODO DELETE
    @PreAuthorize("hasAuthority('DELETE')")
    @PostMapping("/delete")
    public void DeleteUser(@RequestBody Long id){
        servicesUser.DeleteUser(id);
    }

    // METODO ACTULIZAR POR ID
    @PreAuthorize("hasAuthority('UPDATE')")
    @PostMapping("/update")
    public UserEntity UpdateUserById(@RequestBody Long id, @RequestBody UserEntity user){
        return servicesUser.UpdateUserById(id, user);
    }

    // METODO GET POR ID
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/get")
    public UserEntity GetUserById(@RequestBody Long id){
        return servicesUser.GetUserById(id);
    }

}
