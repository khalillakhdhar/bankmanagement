package com.elitech.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.elitech.model.dto.CompteDto;
import com.elitech.model.dto.ProfileDto;
import com.elitech.model.dto.TransactionDto;
import com.elitech.model.dto.UtilisateurDto;
import com.elitech.model.entities.Profile;


public interface UtilisateurService {
	public UtilisateurDto addOneUtilisateur(UtilisateurDto utilisateurDto);
	public Page<UtilisateurDto> getAllUtilisateur(Pageable pageable );
	public Optional<UtilisateurDto> getOneUtilisateur(long id);
	public void deleteOneUtilisateur(long id);
	public ProfileDto assignProfileToUtilisateur(long idUser,Profile ProfileDto);
	 public UtilisateurDto assignCompteToUtilisateur(long idUser, CompteDto compteDto);
	 public UtilisateurDto findByComptes(long idCompte);
}
