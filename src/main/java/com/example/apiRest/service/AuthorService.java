package com.example.apiRest.service;

import com.example.apiRest.exceptions.NotAllowedException;
import com.example.apiRest.model.Author;
import com.example.apiRest.repository.AuthorRepository;
import com.example.apiRest.repository.BookRepository;
import com.example.apiRest.validator.AuthorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {
	private final AuthorRepository repository;
	private final AuthorValidator validator;
	private final BookRepository bookRepository;

	public List<Author> search( String name, String nationality) {
		var author = new Author();
		author.setName(name);
		author.setNationality(nationality);

		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreNullValues()
				.withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example<Author> authorExample = Example.of(author, matcher);

		return repository.findAll(authorExample);
	}

	public boolean hasBook(Author author) {
		return bookRepository.existsByAuthor(author);
	}

	public Optional<Author> getById(UUID id) {
		return repository.findById(id);
	}

	public void save(Author author) {
		validator.validate(author);
		repository.save(author);
	}

	public void update(Author author) {
		if(author.getId() == null){
			throw new IllegalArgumentException("To update, the author must already be saved in the database.");
		}
		validator.validate(author);
		repository.save(author);
	}

	public void delete(Author author) {
		if(hasBook(author)) {
			throw new NotAllowedException("It is not allowed to delete an author who has registered books!");
		}

		repository.delete(author);
	}
}
