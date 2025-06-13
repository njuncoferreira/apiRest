package com.example.apiRest.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

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
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_author")
	private Author author;
}
