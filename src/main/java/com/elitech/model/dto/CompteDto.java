package com.elitech.model.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class CompteDto {
	@NotNull
	private BigDecimal solde;
	@JsonIgnoreProperties("comptes")

	private UtilisateurDto utilisateur;
	@JsonIgnoreProperties({"source","destination"})
	

	private List<TransactionDto> transactions;
}
