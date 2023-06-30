package com.GWarriorF.GWarriorGames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.GWarriorF.GWarriorGames.model.Categoria;

public interface CategoriasRepository extends JpaRepository<Categoria, Long>
{
	public List<Categoria> findAllByGeneroContainingIgnoreCase(@Param("genero") String genero); 
}
