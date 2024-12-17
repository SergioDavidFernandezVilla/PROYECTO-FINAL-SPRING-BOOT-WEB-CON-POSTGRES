package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Anuncio;
import com.example.demo.services.ServicesAnuncio;

import org.springframework.ui.Model;

@Controller
public class ControllerHtml {
    
    @Autowired
    private ServicesAnuncio servicesAnuncio;

    @GetMapping("/formulario-anuncio")
    public String showForm(Model model) {
        model.addAttribute("anuncio", new Anuncio());
        return "new-anuncio"; // Nombre de tu archivo HTML
    }

    @PostMapping
    public String createAnuncio(@ModelAttribute Anuncio anuncio){
        servicesAnuncio.CreateAnuncio(anuncio);
        return "redirect:/anuncios"; // Redirige a la lista de anuncios
    }


    @GetMapping("/insert")
    public String InsertMessage(Model model){
        return "insert-message-sucess";
    }
}
