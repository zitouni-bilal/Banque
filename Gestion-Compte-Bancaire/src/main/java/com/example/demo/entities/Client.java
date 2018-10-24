package com.example.demo.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Client  {
	@Id
	@GeneratedValue
	private Long code;
	private String nom;
	private String emai;
	@OneToMany(mappedBy="client",fetch=FetchType.LAZY)
	Collection<Compte> comptes;
	

}
