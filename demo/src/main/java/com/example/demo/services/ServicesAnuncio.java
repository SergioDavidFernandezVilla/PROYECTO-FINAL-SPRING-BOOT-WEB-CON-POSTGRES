package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Anuncio;
import com.example.demo.repository.RepositoryAnuncio;

@Service
public class ServicesAnuncio {
    
    @Autowired
    private RepositoryAnuncio repositoryAnuncio;

   // METODO CREATE
   public Anuncio CreateAnuncio(Anuncio anuncio){
        return repositoryAnuncio.save(anuncio);
   }

   // METODO GET LIST
   public List<Anuncio> GetListAnuncios(){
        return repositoryAnuncio.findAll();
   }

   // METODO DELETE
   public void DeleteAnuncio(Long id){
        repositoryAnuncio.deleteById(id);
   }

   // METODO ACTULIZAR POR ID
   public Anuncio UpdateAnuncioById(Long id, Anuncio anuncio){
        
        // Verificar si el anuncio existe en la base de datos
        Anuncio anuncioExistente = repositoryAnuncio.findById(id).get();
        if (anuncioExistente == null) {
            return null;
        }

        // Actualizar los campos del anuncio existente con los valores del anuncio proporcionado
        return repositoryAnuncio.save(anuncio);
   }

   // METODO GET POR ID
   public Anuncio GetAnuncioById(Long id){
        return repositoryAnuncio.findById(id).get();
   }

}
