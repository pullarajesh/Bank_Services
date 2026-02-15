package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.entity.Bank;

public interface IBankRepository extends JpaRepository<Bank, Integer>{

	

}
