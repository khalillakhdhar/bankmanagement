package com.elitech.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.elitech.model.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long> {

}
