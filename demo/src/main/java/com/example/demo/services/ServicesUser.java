package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.UserEntity;
import com.example.demo.repository.RepositoryUser;

@Service
public class ServicesUser {

    @Autowired
    private RepositoryUser repositoryUser;

    // METODO CREATE
    public UserEntity CreateUser(UserEntity user) {
        return repositoryUser.save(user);
    }

    // METODO GET LIST
    public List<UserEntity> GetListUsers() {
        return repositoryUser.findAll();
    }

    // METODO DELETE
    public void DeleteUser(Long id) {
        repositoryUser.deleteById(id);
    }

    // METODO ACTULIZAR POR ID
    public UserEntity UpdateUserById(Long id, UserEntity user) {
        // Verificar si el usuario existe en la base de datos
        UserEntity userExistente = repositoryUser.findById(id).get();
        if (userExistente == null) {
            return null;
        }

        // Actualizar los campos del usuario existente con los valores del usuario proporcionado
        return repositoryUser.save(user);
        }

    // METODO GET POR ID
    public UserEntity GetUserById(Long id){
        return repositoryUser.findById(id).get();
    }

}
