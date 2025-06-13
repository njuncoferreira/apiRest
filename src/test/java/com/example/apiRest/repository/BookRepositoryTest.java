package com.example.apiRest.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.apiRest.model.Author;
import com.example.apiRest.model.Book;
import com.example.apiRest.model.BookGenre;

@SpringBootTest
public class BookRepositoryTest {
	
	@Autowired
	BookRepository repository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Test
	public void saveTest() {
		Book book = new Book();
		
		book.setIsbn("90887-84874");
		book.setPrice(BigDecimal.valueOf(100));
		book.setGenre(BookGenre.BIOGRAPHY);
		book.setTitle("United States Of America - USA");
		book.setPublishDate(LocalDate.of(1976, 2, 27));
		
		Author author = new Author();
		
		author.setName("Nathan Ferreira");
		author.setNationality("Brasileiro");
		author.setAnniversaryDate(LocalDate.of(2003, 8, 18));
		
		book.setAuthor(author);
		
		repository.save(book);
		System.out.println("Book saved!");
	}
}
