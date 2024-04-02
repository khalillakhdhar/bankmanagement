package com.elitech.model.dto;

import java.math.BigDecimal;

import com.elitech.model.Types;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
@Data
public class TransactionDto {
	@Positive
	private BigDecimal montant;
	@NotBlank
	private Types type;
	private UtilisateurDto source;
	private UtilisateurDto destination;



}
