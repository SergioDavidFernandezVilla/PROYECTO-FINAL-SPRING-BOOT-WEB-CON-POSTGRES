package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Anuncio;
import com.example.demo.services.ServicesAnuncio;

@RestController
@RequestMapping("/anuncio")
public class ControllerAnuncio {
    
    @Autowired
    private ServicesAnuncio servicesAnuncio;

    // METODO CREATE 
    @PreAuthorize("hasAuthority('CREATE')")
    @PostMapping
    public ResponseEntity<Anuncio> createAnuncio(@RequestBody Anuncio anuncio){
        Anuncio createdAnuncio = servicesAnuncio.CreateAnuncio(anuncio);
        return new ResponseEntity<>(createdAnuncio, HttpStatus.CREATED);
    }   

    // METODO ANUNCIOS LIST
    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Anuncio> GetListAnuncios(){
        return servicesAnuncio.GetListAnuncios();
    }

    // METODO DELETE
    @PreAuthorize("hasAuthority('DELETE')")
    @DeleteMapping
    public void DeleteAnuncio(@PathVariable Long id){
        servicesAnuncio.DeleteAnuncio(id);
    }

    // METODO ACTULIZAR POR ID
    @PreAuthorize("hasAuthority('UPDATE')")
    @PutMapping("/{id}")
    public Anuncio UpdateAnuncioById(@PathVariable Long id, @RequestBody Anuncio anuncio){
        return servicesAnuncio.UpdateAnuncioById(id, anuncio);
    }

    // METODO GET POR ID
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/{id}")
    public Anuncio GetAnuncioById(@PathVariable Long id){
        return servicesAnuncio.GetAnuncioById(id);
    }
    

}
