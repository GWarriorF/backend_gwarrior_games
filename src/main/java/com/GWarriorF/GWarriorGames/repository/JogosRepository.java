package com.GWarriorF.GWarriorGames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.GWarriorF.GWarriorGames.model.Jogo;

public interface JogosRepository extends JpaRepository<Jogo, Long> 
{

	public List<Jogo> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo); 

}
