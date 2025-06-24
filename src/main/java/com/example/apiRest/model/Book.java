package com.example.apiRest.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table
@Data
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(length = 20, nullable = false)
	private String isbn;
	
	@Column(length = 150, nullable = false)
	private String title;
	
	@Column(nullable = false)
	private LocalDate publishDate;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 30, nullable = false)
	private BookGenre genre;
	
	@Column(precision = 18, scale = 2)
	private BigDecimal price;

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "id_author")
	private Author author;
}
