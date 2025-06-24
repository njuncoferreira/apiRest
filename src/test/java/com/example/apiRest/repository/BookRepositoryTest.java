package com.example.apiRest.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

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
		book.setTitle("About ME");
		book.setPublishDate(LocalDate.of(1976, 2, 27));

		Author author = authorRepository.findById(UUID.fromString("9bd4543d-84eb-4d70-bf07-df12c62c20bd")).orElse(null);

		book.setAuthor(author);
		repository.save(book);
	}
}
