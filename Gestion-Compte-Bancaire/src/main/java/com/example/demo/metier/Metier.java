package com.example.demo.metier;

import org.springframework.data.domain.Page;

import com.example.demo.entities.Compte;
import com.example.demo.entities.Operation;

public interface Metier {

	public void addCompte(Compte compte);
	public Compte consuletCompte(String codeCompte);
    public void verser(String codeCompte,double montant);	
    public void retirer(String codeCompte,double montant);
    public void versement(String codeCompte1,String codeCompte,double montant);
    public Page<Operation> getAllOperation(String codeCompte,int page ,int size);
    
}
