package com.elitech.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.elitech.model.dto.CompteDto;
import com.elitech.model.dto.ProfileDto;
import com.elitech.model.dto.UtilisateurDto;
import com.elitech.model.entities.Profile;
import com.elitech.services.UtilisateurServiceImp;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/utilisateurs") 
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurServiceImp utilisateurService;

    @PostMapping
    public ResponseEntity<UtilisateurDto> addUtilisateur(@RequestBody UtilisateurDto utilisateurDto) {
        UtilisateurDto createdUtilisateur = utilisateurService.addOneUtilisateur(utilisateurDto);
        return new ResponseEntity<>(createdUtilisateur, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<UtilisateurDto>> getAllUtilisateurs(Pageable pageable) {
        Page<UtilisateurDto> utilisateurs = utilisateurService.getAllUtilisateur(pageable);
        return ResponseEntity.ok(utilisateurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDto> getUtilisateurById(@PathVariable long id) {
        return utilisateurService.getOneUtilisateur(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}/comptes")
    public ResponseEntity<UtilisateurDto> getUserByComptes(@PathVariable long id)
    {
    	return ResponseEntity.ok(utilisateurService.findByComptes(id));
    }
    
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable long id) {
        utilisateurService.deleteOneUtilisateur(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idUser}/profile")
    public ResponseEntity<ProfileDto> assignProfileToUtilisateur(@PathVariable long idUser, @RequestBody Profile profile) {
        try {
            ProfileDto profileDto = utilisateurService.assignProfileToUtilisateur(idUser, profile);
            return ResponseEntity.ok(profileDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{idUser}/compte")
    public ResponseEntity<UtilisateurDto> assignCompteToUtilisateur(@PathVariable long idUser, @RequestBody CompteDto compteDto) {
        try {
            UtilisateurDto utilisateurDto = utilisateurService.assignCompteToUtilisateur(idUser, compteDto);
            return ResponseEntity.ok(utilisateurDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}

