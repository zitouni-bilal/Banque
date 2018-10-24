package com.example.demo.mertier;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entities.Compte;
import com.example.demo.repository.RepositoryCompte;
import com.example.demo.repository.RepositoryOperation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MetierTest {

	@Autowired
	private RepositoryCompte repositoryCompte;
	@Autowired
	private RepositoryOperation repositoryOperation;
	
	@Test
	public void testConsulterCompte(){
		
		Compte cp=repositoryCompte.getOne("C1");
		
		Assert.assertNotNull(cp);
		
	}
	
	
}
