package com.example.apiRest.repository;

import com.example.apiRest.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface AuthorRepository extends JpaRepository<Author, UUID> {}
