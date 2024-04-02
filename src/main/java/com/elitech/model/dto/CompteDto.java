package com.elitech.model.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class CompteDto {
	@NotNull
	private BigDecimal solde;
	private UtilisateurDto utilisateur;
}
