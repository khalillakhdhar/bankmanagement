package com.elitech.model.entities;

import java.math.BigDecimal;

import com.elitech.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIgnoreProperties({"hibernateLazyInitialiser","handler"})
public class Compte extends BaseEntity {
	private BigDecimal solde;
	@ManyToOne(optional = false,fetch = FetchType.EAGER)
	@JsonIgnoreProperties("comptes")
	private Utilisateur utilisateur;
	

}
