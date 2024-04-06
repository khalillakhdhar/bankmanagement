package com.elitech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elitech.model.entities.Utilisateur;
import com.elitech.model.entities.Compte;


public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
Utilisateur findByComptes(List<Compte> comptes);

}
