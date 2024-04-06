package com.elitech.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.elitech.model.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long> {

	//@Query
	//@Query("SELECT  FROM `compte` WHERE 1 ORDER BY solde LIMIT 1;")
	//Compte getBest();
}
