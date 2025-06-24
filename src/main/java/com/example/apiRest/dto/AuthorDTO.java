package com.example.apiRest.dto;

import com.example.apiRest.model.Author;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(
        UUID id,
        String name,
        LocalDate anniversaryDate,
        String nationality
) {
    public Author AuthorMap() {
        Author author = new Author();

        author.setName(this.name);
        author.setAnniversaryDate(this.anniversaryDate);
        author.setNationality(this.nationality);

        return author;
    }
}
