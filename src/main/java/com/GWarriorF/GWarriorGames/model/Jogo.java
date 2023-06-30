package com.GWarriorF.GWarriorGames.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_jogos")
public class Jogo 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@NotBlank(message = "O título do jogo é obrigatório")
	@Size(min = 2, max = 100, message = "O título do jogo aceita no minímo 2 e no máximo 100 caracteres!")
	private String titulo; 
	
	@NotNull(message = "O valor é obrigatório!")
	@Positive(message = "O valor deve ser positivo!")
	private BigDecimal valor; 
	
	@NotNull
	@Min(value = 0, message = "A faixa etária deve ser no mínimo 1 ano")
	@Max(value = 100, message = "A faixa etária deve ser no máximo 100 anos")
	private Long faixaEtaria;
	
	@Size(min = 2, max = 100, message = "O console deve ter no minimo 2 e no maximo 100 carateres!")
	private String console;
	
	@ManyToOne
	@JsonIgnoreProperties("Jogos")
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getFaixaEtaria() {
		return faixaEtaria;
	}

	public void setFaixaEtaria(Long faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	} 
	
	
}
