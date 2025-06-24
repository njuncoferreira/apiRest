package com.example.apiRest.dto;

import com.example.apiRest.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(
        UUID id,
        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 100, message = "Number of characters too short or exceeded")
        String name,
        @NotNull(message = "Anniversary Date is required")
        @Past(message = "Future dates are not allowed.")
        LocalDate anniversaryDate,
        @NotBlank(message = "Nationality is required")
        @Size(min = 2, max = 50, message = "Number of characters too short or exceeded")
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
