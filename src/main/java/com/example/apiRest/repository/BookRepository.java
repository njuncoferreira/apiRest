package com.example.apiRest.repository;

import com.example.apiRest.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID>{

}
