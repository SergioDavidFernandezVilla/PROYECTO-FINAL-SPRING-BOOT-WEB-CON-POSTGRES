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

    @GetMapping("/")
    public String welcomePage(Model model){
        return "index";
    }

    @GetMapping("/crear-nuevo-anuncio")
    public String showForm(Model model) {
        model.addAttribute("anuncio", new Anuncio());
        return "nuevo-anuncio"; // Nombre de tu archivo HTML
    }

    @PostMapping("/crear-nuevo-anuncio")
    public String createAnuncio(@ModelAttribute("anuncio") Anuncio anuncio) {
        servicesAnuncio.CreateAnuncio(anuncio); // Guarda los datos
        return "redirect:/insert"; // Redirige a una página de éxito
    }


    @GetMapping("/insert")
    public String InsertMessage(Model model){
        return "insert-message-sucess";
    }
}
