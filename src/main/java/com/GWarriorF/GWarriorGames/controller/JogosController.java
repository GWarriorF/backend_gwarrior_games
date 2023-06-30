package com.GWarriorF.GWarriorGames.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.GWarriorF.GWarriorGames.model.Jogo;
import com.GWarriorF.GWarriorGames.repository.CategoriasRepository;
import com.GWarriorF.GWarriorGames.repository.JogosRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jogos")
@CrossOrigin(origins = "*", allowedHeaders = "*" )
public class JogosController 
{
	@Autowired
	private JogosRepository jogos;
	@Autowired
	private CategoriasRepository categorias;
	
	@GetMapping
	public ResponseEntity<List<Jogo>> getAll()
	{
		return ResponseEntity.ok(jogos.findAll());
	}
	
    @GetMapping("/{id}")
	public ResponseEntity<Jogo> getById(@PathVariable Long id)
	{
		return jogos.findById(id)
			   .map(resposta -> ResponseEntity.ok(resposta))
			   .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Jogo>> getByTitulo(@PathVariable String titulo)
	{
		return ResponseEntity.ok(jogos.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Jogo> post (@Valid @RequestBody Jogo jogo)
	{
		if (categorias.existsById(jogo.getCategoria().getId()))
			return 	ResponseEntity.status(HttpStatus.CREATED)
								  .body(jogos.save(jogo)); 
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe!", null);
	}
	
	@PutMapping 
	public ResponseEntity<Jogo> put(@Valid @RequestBody Jogo jogo)
	{
		if (jogos.existsById(jogo.getId())) {
			if(categorias.existsById(jogo.getCategoria().getId()))
					return ResponseEntity.status(HttpStatus.OK)
										 .body(jogos.save(jogo));
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe!", null);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) 
	{
		Optional<Jogo> jogo = jogos.findById(id);
		
		if (jogo.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		jogos.deleteById(id);
	}
}
