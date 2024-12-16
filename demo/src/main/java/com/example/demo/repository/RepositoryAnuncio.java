package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Anuncio;

public interface RepositoryAnuncio extends JpaRepository<Anuncio, Long> {


}
