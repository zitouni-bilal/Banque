package com.example.demo.metier;



import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Compte;
import com.example.demo.entities.CompteCourant;
import com.example.demo.entities.Operation;
import com.example.demo.entities.Retrait;
import com.example.demo.entities.Versement;
import com.example.demo.repository.RepositoryCompte;
import com.example.demo.repository.RepositoryOperation;


@Service
@Transactional
public class BanqueMetierImpl implements Metier {

	@Autowired
	private RepositoryCompte repositoryCompte;
	@Autowired
	private RepositoryOperation repositoryOperation;
	
	@Override
	public Compte consuletCompte(String codeCpt) {

	   Compte cp=repositoryCompte.getOne(codeCpt);
	   if(cp == null) throw new RuntimeException("Compte introuvable");
	   
		return cp;
	}

	@Override
	public void verser(String codeCompte, double montant) {

		Compte cp=consuletCompte(codeCompte);

        Versement versement=new Versement(new Date(), montant, cp);
		
      repositoryOperation.save(versement);
        
      
        cp.setSolde(cp.getSolde()+montant);
        
        repositoryCompte.save(cp);
		
	}

	@Override
	public void retirer(String codeCompte, double montant) {
		Compte cp=consuletCompte(codeCompte);
		 double decouvert = 0;
		 if (cp instanceof CompteCourant) 
               decouvert = ((CompteCourant) cp).getDecouvert();
		 
		   if(cp.getSolde()+decouvert > montant){
	       Retrait  r=new Retrait(new Date(), montant, cp);
	       repositoryOperation.save(r);
	       cp.setSolde(cp.getSolde()-montant);
	       repositoryCompte.save(cp);
		   }
	}

	@Override
	public void versement(String codeCompte1, String codeCompte, double montant) {

		 this.verser(codeCompte, montant);
		 this.retirer(codeCompte1, montant);
		 
		
	}

	@Override
	public Page<Operation> getAllOperation(String codeCompte, int page, int size) {

     		return repositoryOperation.listOperationOfComp
     			   (codeCompte, new PageRequest(page, size));
	}

	@Override
	public void addCompte(Compte compte) {

      repositoryCompte.save(compte);
		
	}

}
