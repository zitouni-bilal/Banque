package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Compte;

@Repository
public interface RepositoryCompte extends JpaRepository<Compte, String> {

	
	
	

}
