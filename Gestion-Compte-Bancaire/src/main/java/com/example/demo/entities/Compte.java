package com.example.demo.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_CPTE", discriminatorType=DiscriminatorType.STRING,length=2)
public class Compte {

	
	@Id
	private String numeroCompte;
	private Date dateCreation;
	private Double solde;
	@ManyToOne
	@JoinColumn(name="CODE_CLE")
	private Client client;
	@OneToMany(mappedBy="compte")
	private Collection<Operation> operations;
	public Compte(String numeroCompte, Date dateCreation, Double solde, Client client) {
		super();
        this.numeroCompte = numeroCompte;
		this.dateCreation = dateCreation;
		this.solde = solde;
		this.client = client;
	}
	
	
	
}
