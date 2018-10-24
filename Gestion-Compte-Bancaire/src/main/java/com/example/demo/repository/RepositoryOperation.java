package com.example.demo.repository;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Operation;

@Repository
public interface RepositoryOperation extends JpaRepository<Operation, Long>{

	@Query("select o from Operation o where o.compte.numeroCompte = :x")
	public Page<Operation> listOperationOfComp(@Param("x") String codeCpte,Pageable pageable);  
}