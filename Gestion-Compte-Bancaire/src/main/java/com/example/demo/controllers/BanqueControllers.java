package com.example.demo.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Compte;
import com.example.demo.entities.Operation;
import com.example.demo.metier.Metier;


@Controller
public class BanqueControllers {

	@Autowired
	private Metier metier;
	
	@RequestMapping(value="/operations")
	public String index(Model model){
		
		return "Comptes";
	}
	
	@RequestMapping(value="/consulterCompte")
	public String ConsulterCompte(Model model,
			                      @RequestParam String codeCompte,
			                      @RequestParam(name="page",defaultValue="0")int page,
						           @RequestParam(name="size",defaultValue="5")int size){
		
		Compte cp=metier.consuletCompte(codeCompte);
   
		Page<Operation> pageOperations=metier.getAllOperation(codeCompte, page, size);
		model.addAttribute("ListOperations", pageOperations.getContent());
		int [] pages=new int[pageOperations.getTotalPages()];
		model.addAttribute("pages", pages);
		
		model.addAttribute("comptee",cp);
		
		
		return "Comptes";
	}
	
	@RequestMapping(value="/addVersement")
	public String addVersement(Model model,
			                   @RequestParam String codeCompte,
			                   @RequestParam Double montant,
			                   @RequestParam String typeOper,
			                   @RequestParam String numCompte2
			                   ){
		if(typeOper.equals("vers"))
		metier.verser(codeCompte, montant);
		if(typeOper.equals("Retrer"))
			metier.retirer(codeCompte, montant);
		if(typeOper.equals("verm"))
			metier.versement(codeCompte, numCompte2, montant);;
		
		return "Comptes";
	}
	
	@RequestMapping(value="/addRetrer")
	public String addRetrer(Model model,@RequestParam String numCompte,@RequestParam Double montant){
		
		metier.verser(numCompte, montant);
		
		return "Clients";
	}
}
