package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.repository.RepositoryUser;

@Service
public class ServicesUser {

    @Autowired
    private RepositoryUser repositoryUser;

    // METODO CREATE
    public User CreateUser(User user) {
        return repositoryUser.save(user);
    }

    // METODO GET LIST
    public List<User> GetListUsers() {
        return repositoryUser.findAll();
    }

    // METODO DELETE
    public void DeleteUser(Long id) {
        repositoryUser.deleteById(id);
    }

    // METODO ACTULIZAR POR ID
    public User UpdateUserById(Long id, User user) {
        // Verificar si el usuario existe en la base de datos
        User userExistente = repositoryUser.findById(id).get();
        if (userExistente == null) {
            return null;
        }

        // Actualizar los campos del usuario existente con los valores del usuario proporcionado
        return repositoryUser.save(user);
        }

    // METODO GET POR ID
    public User GetUserById(Long id){
        return repositoryUser.findById(id).get();
    }

    // METODO GET POR EMAIL
    public User GetUserByEmail(String email){
        return repositoryUser.findByEmail(email);
    }

}
