package com.elitech.model.entities;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.elitech.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Utilisateur extends BaseEntity {
	private String nom;
	private String email;
	private String password;
	@OneToMany(mappedBy = "utilisateur")
	@JsonIgnoreProperties("utilisateur")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Compte> comptes;
	@OneToOne(mappedBy = "utilisateur")
	@JsonIgnoreProperties("utilisateur")
	private Profile profile;
	@OneToMany(mappedBy = "source")
	@JsonIgnoreProperties({"source","destination"})
	private List<Transaction> transactions;
	@OneToMany(mappedBy = "destination")
	@JsonIgnoreProperties({"source","destination"})
	private List<Transaction> transfert;

}
