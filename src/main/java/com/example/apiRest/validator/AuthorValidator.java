package com.example.apiRest.validator;

import com.example.apiRest.exceptions.DuplicateRecordException;
import com.example.apiRest.model.Author;
import com.example.apiRest.repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorValidator {

    private AuthorRepository repository;

    public AuthorValidator(AuthorRepository repository) {
        this.repository = repository;
    }

    public void validate(Author author) {
        if(existsAuthor(author)){
            throw new DuplicateRecordException("Author already registered");
        }
    }

    private boolean existsAuthor(Author author) {
        Optional<Author> findAuthor = repository.findByNameAndAnniversaryDateAndNationality(author.getName(), author.getAnniversaryDate(), author.getNationality());

        if(author.getId() == null) {
            return findAuthor.isPresent();
        }

        return !author.getId().equals(findAuthor.get().getId()) && findAuthor.isPresent();
    }

}
