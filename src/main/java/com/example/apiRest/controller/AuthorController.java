package com.example.apiRest.controller;

import com.example.apiRest.dto.AuthorDTO;
import com.example.apiRest.model.Author;
import com.example.apiRest.service.AuthorService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/authors")
public class AuthorController {
	
	private final AuthorService service;
	
	public AuthorController(AuthorService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<Author> save(@RequestBody AuthorDTO authorDTO) {
		Author authorEntity = authorDTO.AuthorMap();
		service.save(authorEntity);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(authorEntity.getId())
				.toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		
		return new ResponseEntity<Author>(authorEntity, headers, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<AuthorDTO> getById(@PathVariable("id") String id){
		var authorId = UUID.fromString(id);
		
		Optional<Author> authorOptional = service.getById(authorId);
		
		if(authorOptional.isPresent()) {
			Author author = authorOptional.get();

			AuthorDTO dto = new AuthorDTO(author.getId(), author.getName(), author.getAnniversaryDate(), author.getNationality());

			return ResponseEntity.ok(dto);
		}

		return ResponseEntity.notFound().build();
	}
}
