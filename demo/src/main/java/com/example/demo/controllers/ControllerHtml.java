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
    public String WelcomePage(Model model) {
        model.addAttribute("mensaje", "Bienvenidos al tablón de anuncios");
        model.addAttribute("anuncios", servicesAnuncio.GetListAnuncios()); // Mostrar lista de anuncios
        return "index"; // Cargar el template index.html
    }

    // Mostrar el formulario
    @GetMapping("/anuncio")
    public String mostrarFormulario(Model model) {
        model.addAttribute("anuncio", new Anuncio()); // Crear un objeto vacío para el formulario
        return "new-anuncio"; // Nombre del template HTML
    }

    // Manejar el envío del formulario
    @PostMapping("/anuncio")
    public String crearAnuncio(@ModelAttribute Anuncio anuncio) {
        servicesAnuncio.CreateAnuncio(anuncio); // Guardar el anuncio en la base de datos
        return "redirect:/"; // Redirigir a la página de inicio después de guardar
    }

    @GetMapping("/insert")
    public String InsertMessage(Model model){
        return "insert-message-sucess";
    }
}
