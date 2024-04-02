package com.elitech.model.dto;


import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class ProfileDto {
	@Size(min = 8,max = 16)
	private String phoneNumber;
	
	private String adresse;
	private UtilisateurDto utilisateur; 
}
