package com.elitech.model.dto;

import java.math.BigDecimal;

import com.elitech.model.Types;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
@Data
public class TransactionDto {
	@Positive
	private BigDecimal montant;
	@NotBlank
	private Types type;
	@JsonIgnoreProperties("transactions")
	private CompteDto source;
	@JsonIgnoreProperties("transactions")

	private CompteDto destination;



}
