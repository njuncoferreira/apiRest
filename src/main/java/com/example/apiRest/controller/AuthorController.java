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
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/authors")
public class AuthorController {
	
	private final AuthorService service;
	
	public AuthorController(AuthorService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<AuthorDTO>> search(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "nationality", required = false) String nationality){
		List<Author> result = service.search(name, nationality);
		List<AuthorDTO> list = result
				.stream()
				.map(author -> new AuthorDTO(
						author.getId(),
						author.getName(),
						author.getAnniversaryDate(),
						author.getNationality())
				).collect(Collectors.toList());

		return ResponseEntity.ok(list);
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

	@PutMapping("{id}")
	public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody AuthorDTO dto) {
		var authorId = UUID.fromString(id);
		Optional<Author> authorOptional = service.getById(authorId);

		if(authorOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		var author = authorOptional.get();
		author.setName(dto.name());
		author.setNationality(dto.nationality());
		author.setAnniversaryDate(dto.anniversaryDate());

		service.update(author);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		var authorId = UUID.fromString(id);
		Optional<Author> authorOptional = service.getById(authorId);

		if(authorOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		service.delete(authorOptional.get());
		return ResponseEntity.noContent().build();
	}
}
