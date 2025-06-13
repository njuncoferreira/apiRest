package com.example.apiRest.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.apiRest.model.Author;

@SpringBootTest
public class AuthorRepositoryTest {
	
	@Autowired
	AuthorRepository repository;
	
	@Test
	public void saveTest() {
		Author author = new Author();
		
		author.setName("Nathan Ferreira");
		author.setNationality("Brasileiro");
		author.setAnniversaryDate(LocalDate.of(2003, 8, 18));
		
		var savedAuthor = repository.save(author);
		System.out.println("Saved Author: " + savedAuthor);
	}
	
	@Test
	public void updateTest() {
		var id = UUID.fromString("2045810b-b716-4994-8264-9015573c3525");
		
		Optional<Author> possibleAuthor = repository.findById(id);
		
		if(possibleAuthor.isPresent()) {
			
			Author foundAuthor = possibleAuthor.get();
			System.out.println("Dados do Autor: ");
			System.out.println(foundAuthor);
			
			foundAuthor.setName("Josevaldo Cruz");
			foundAuthor.setAnniversaryDate(LocalDate.of(1965, 3, 17));
			
			repository.save(foundAuthor);
			System.out.println("Author has Updated!");
		}
		
	}
	
	@Test
	public void listAllTest() {
		List<Author> author = repository.findAll();
		
		author.forEach(System.out::println);
	}
	
	@Test
	public void deletePerIdTest() {
		var id = UUID.fromString("6d3f9568-f791-4253-b55f-4b860bb28d95");
		
		repository.deleteById(id);
	}
}
