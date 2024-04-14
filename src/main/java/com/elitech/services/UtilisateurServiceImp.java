package com.elitech.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.elitech.model.dto.CompteDto;
import com.elitech.model.dto.ProfileDto;
import com.elitech.model.dto.UtilisateurDto;
import com.elitech.model.entities.Compte;
import com.elitech.model.entities.Profile;
import com.elitech.model.entities.Utilisateur;
import com.elitech.model.mappers.CompteMapper;
import com.elitech.model.mappers.ProfileMapper;
import com.elitech.model.mappers.UtilisateurMapper;
import com.elitech.repository.CompteRepository;
import com.elitech.repository.UtilisateurRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImp implements UtilisateurService {
	  @Autowired
	    private PasswordEncoder passwordEncoder;
	
	private final UtilisateurRepository utilisateurRepository;
	
	  private final CompteRepository compteRepository;
	
	@Override
	public UtilisateurDto addOneUtilisateur(UtilisateurDto utilisateurDto) {
	    Utilisateur utilisateur = UtilisateurMapper.convertToEntity(utilisateurDto);
	    utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
	    
	    Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
	    return UtilisateurMapper.convertToDTO(savedUtilisateur);
	}


	@Override
	public Page<UtilisateurDto> getAllUtilisateur(Pageable pageable) {
	    Page<Utilisateur> utilisateurs = utilisateurRepository.findAll(pageable);
	    
	    return utilisateurs.map(UtilisateurMapper::convertToDTO);
	}


	@Override
	public Optional<UtilisateurDto> getOneUtilisateur(long id) {
		// TODO Auto-generated method stub
		return utilisateurRepository.findById(id).map(UtilisateurMapper::convertToDTO);
	}

	@Override
	public void deleteOneUtilisateur(long id) {
		// TODO Auto-generated method stub
		utilisateurRepository.deleteById(id);

	}

	@Override
	public ProfileDto assignProfileToUtilisateur(long idUser, Profile profile) {
	    Utilisateur utilisateur = utilisateurRepository.findById(idUser)
	            .orElseThrow(() -> new EntityNotFoundException("Utilisateur avec l'ID " + idUser + " n'a pas été trouvé."));
	    
	    utilisateur.setProfile(profile);
	    Utilisateur updatedUtilisateur = utilisateurRepository.save(utilisateur);
	    return ProfileMapper.convertToDTO(updatedUtilisateur.getProfile());
	}


	 @Override
	    public UtilisateurDto assignCompteToUtilisateur(long idUser, CompteDto compteDto) {
	        Utilisateur utilisateur = utilisateurRepository.findById(idUser)
	                .orElseThrow(() -> new EntityNotFoundException("Utilisateur avec l'ID " + idUser + " n'a pas été trouvé."));
	        
	       Compte compte =CompteMapper.convertToEntity(compteDto);
	       compte.setUtilisateur(utilisateur);
	       Compte cpt=compteRepository.save(compte);
	       utilisateur.getComptes().add(cpt);
	       //utilisateurRepository.save(utilisateur);
	       return UtilisateurMapper.convertToDTO(utilisateur);
	       
	    }


	@Override
	public UtilisateurDto findByComptes(long idCompte) {
		// TODO Auto-generated method stub
	List<Compte> comptes =new ArrayList<Compte>();
	comptes.add(compteRepository.findById(idCompte).get());
	
		
		return UtilisateurMapper.convertToDTO(utilisateurRepository.findByComptes(comptes));
	}




}
