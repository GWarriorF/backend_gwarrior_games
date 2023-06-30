package com.GWarriorF.GWarriorGames.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.GWarriorF.GWarriorGames.model.Categoria;
import com.GWarriorF.GWarriorGames.repository.CategoriasRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*" )
public class CategoriasController 
{
	@Autowired
	private CategoriasRepository categoriasRepository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll()
	{
		return ResponseEntity.ok(categoriasRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Long id)
	{
		return categoriasRepository.findById(id)
			   .map(resposta -> ResponseEntity.ok(resposta))
			   .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/genero/{genero}")
	public ResponseEntity<List<Categoria>> getByTitulo(@PathVariable String genero)
	{
		return ResponseEntity.ok(categoriasRepository.findAllByGeneroContainingIgnoreCase(genero));
	}
	
	@PostMapping
	public ResponseEntity<Categoria> post (@Valid @RequestBody Categoria genero)
	{
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(categoriasRepository.save(genero)); 
	}
	
	@PutMapping 
	public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria Categoria)
	{
		return categoriasRepository.findById(Categoria.getId())
					.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(categoriasRepository.save(Categoria)))
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) 
	{
		Optional<Categoria> Categoria = categoriasRepository.findById(id);
		
		if (Categoria.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		categoriasRepository.deleteById(id);
	}
}
