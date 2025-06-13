package com.example.apiRest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apiRest.model.Author;


public interface AuthorRepository extends JpaRepository<Author, UUID> {}
