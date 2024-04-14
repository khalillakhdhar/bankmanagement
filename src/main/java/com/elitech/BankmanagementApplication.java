package com.elitech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.elitech.model.entities.Utilisateur;
import com.elitech.model.mappers.UtilisateurMapper;
import com.elitech.repository.UtilisateurRepository;
import com.elitech.services.UtilisateurService;

@SpringBootApplication
public class BankmanagementApplication implements CommandLineRunner {

	@Autowired
	UtilisateurService utilisateurService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(BankmanagementApplication.class, args);
	}
/*
 * class 
 * public static int x=2;
 * 
 * 
 * 
 */

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	//	utilisateurService.addOneUtilisateur(UtilisateurMapper.convertToDTO(new Utilisateur("khalil","khalil@gmail.com", "azerty", null, null, "ADMIN_ROLES,USER_ROLES")));
		
	}
}
