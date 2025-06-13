package com.example.apiRest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apiRest.model.Book;

public interface BookRepository extends JpaRepository<Book, UUID>{

}
