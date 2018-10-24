package com.example.demo.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {

	private Double decouvert;

	public CompteCourant(String numeroCompte, Date dateCreation, Double solde, Client client,
			Double decouvert) {
		super(numeroCompte, dateCreation, solde, client);
		this.decouvert = decouvert;
	}

	
	
	
	
}
