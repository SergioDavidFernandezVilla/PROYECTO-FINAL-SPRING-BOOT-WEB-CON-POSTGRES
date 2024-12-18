package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Anuncio;
import com.example.demo.repository.RepositoryAnuncio;
import com.example.demo.services.ServicesAnuncio;

import org.springframework.ui.Model;

@Controller
public class ControllerHtml {
    
    @Autowired
    private ServicesAnuncio servicesAnuncio;

    @Autowired
    private RepositoryAnuncio anuncioRepository;

    @GetMapping("/")
    public String welcomePage(Model model){
        List<Anuncio> anuncios = anuncioRepository.findAll();
        model.addAttribute("anuncios", anuncios); // Atributo correcto
        model.addAttribute("mensaje", "Bienvenidos al tablón de anuncios");
        return "index";
    }

    @GetMapping("/new")
    public String createAnuncioForm(Model model) {
        model.addAttribute("anuncio", new Anuncio());
        return "nuevo-anuncio";
    }

    @PostMapping("/new")
    public String createAnuncio(@ModelAttribute("anuncio") Anuncio anuncio) {
        servicesAnuncio.CreateAnuncio(anuncio); // Guarda los datos
        return "redirect:/insert"; // Redirige a una página de éxito
    }


    @GetMapping("/insert")
    public String InsertMessage(Model model){
        return "insert-message-sucess";
    }
}
