package com.GWarriorF.GWarriorGames.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categoria")
public class Categoria 
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@NotBlank (message = "O gênero do jogo é obrigatório!")
	@Size(min = 2, max = 100, message = "O gênero deve conter no minímo 2 e no máximo 100 caracteres!")
	private String genero;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("categoria")
	private List<Jogo> jogos;
	
	public List<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	} 
	
	
}
