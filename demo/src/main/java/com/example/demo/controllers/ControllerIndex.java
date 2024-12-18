package com.example.demo.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class ControllerIndex {
    

    @GetMapping("/")
    @PreAuthorize("permitAll()")
    public String index(){
        return "Hola Mundo AUTH - VISTA NO PROTEGIDA";
    }

    @GetMapping("/protected")
    @PreAuthorize("hasAuthority('READ')")
    public String ProtectedView(){
        return "Hola Mundo AUTH - VISTA PROTEGIDA";
    }

}
