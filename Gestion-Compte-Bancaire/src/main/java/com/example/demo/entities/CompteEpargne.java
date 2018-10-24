package com.example.demo.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@DiscriminatorValue("CE")
@NoArgsConstructor
public class CompteEpargne extends Compte {

	private double taux;

	public CompteEpargne(String numeroCompte, Date dateCreation, Double solde, Client client, double taux) {
		super(numeroCompte, dateCreation, solde, client);
		this.taux = taux;
	}
	
	
	
	
}
