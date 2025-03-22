package com.elitech.controller;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.elitech.model.dto.AuthRequest;
import com.elitech.model.dto.CompteDto;
import com.elitech.model.dto.ProfileDto;
import com.elitech.model.dto.UtilisateurDto;
import com.elitech.model.entities.Profile;
import com.elitech.services.UtilisateurServiceImp;
import com.elitech.services.auth.JwtService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/utilisateurs") 
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurServiceImp utilisateurService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @PostMapping
    public ResponseEntity<UtilisateurDto> addUtilisateur(@RequestBody UtilisateurDto utilisateurDto) {
        UtilisateurDto createdUtilisateur = utilisateurService.addOneUtilisateur(utilisateurDto);
        return new ResponseEntity<>(createdUtilisateur, HttpStatus.CREATED);
    }

    @GetMapping
    //@PreAuthorize("hasAuthority('ADMIN_ROLES')")
    public ResponseEntity<Page<UtilisateurDto>> getAllUtilisateurs(Pageable pageable) {
        Page<UtilisateurDto> utilisateurs = utilisateurService.getAllUtilisateur(pageable);
        return ResponseEntity.ok(utilisateurs);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_ROLES')")

    public ResponseEntity<UtilisateurDto> getUtilisateurById(@PathVariable long id) {
        return utilisateurService.getOneUtilisateur(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody AuthRequest authRequest){
        try {
            Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
            if(authenticate.isAuthenticated()){
                String token = jwtService.generateToken(authRequest.getUserName());
                UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
                List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
                Map<String, Object> response = new HashMap<>();
                response.put("token", token);
                response.put("roles", roles);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Invalid credentials"));
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Invalid user request"));
        }
    }

    
    @GetMapping("/{id}/comptes")
    public ResponseEntity<UtilisateurDto> getUserByComptes(@PathVariable long id)
    {
    	return ResponseEntity.ok(utilisateurService.findByComptes(id));
    }
    
    

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN_ROLES')")
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

