package com.elitech.model.dto;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UtilisateurDto {
	@NotBlank
	private String nom;
	@NotBlank
	@Email
	private String email;
	
	@JsonIgnoreProperties(allowGetters = true,value="password")
	@NotBlank
	@Size(min = 8)
	private String password;
	private List<CompteDto> comptes;
	private List<TransactionDto> transactions;
	private ProfileDto profile;
	
	
	
}
