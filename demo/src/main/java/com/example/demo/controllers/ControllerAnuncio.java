package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping
    public ResponseEntity<Anuncio> createAnuncio(@RequestBody Anuncio anuncio){
        Anuncio createdAnuncio = servicesAnuncio.CreateAnuncio(anuncio);
        return new ResponseEntity<>(createdAnuncio, HttpStatus.CREATED);
    }   

    // METODO ANUNCIOS LIST
    @GetMapping
    public List<Anuncio> GetListAnuncios(){
        return servicesAnuncio.GetListAnuncios();
    }

    // METODO DELETE
    @DeleteMapping
    public void DeleteAnuncio(@PathVariable Long id){
        servicesAnuncio.DeleteAnuncio(id);
    }

    // METODO ACTULIZAR POR ID
    @PutMapping("/{id}")
    public Anuncio UpdateAnuncioById(@PathVariable Long id, @RequestBody Anuncio anuncio){
        return servicesAnuncio.UpdateAnuncioById(id, anuncio);
    }

    // METODO GET POR ID
    @GetMapping("/{id}")
    public Anuncio GetAnuncioById(@PathVariable Long id){
        return servicesAnuncio.GetAnuncioById(id);
    }
    

}
