package com.spring.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.spring.dto.BankDto;
import com.spring.entity.Bank;
import com.spring.exceptions.CustomExceptions;
import com.spring.repository.IBankRepository;

@Service
public class BankService {



	@Autowired
	private IBankRepository repo;

	
	public Bank convertBankDtoToBank(BankDto bankDto) {
		Bank bank=new Bank();
		
		bank.setAccountHolderName(bankDto.getAccountHolderFirstName()+" "+bankDto.getAccountHolderSecondName());
		bank.setAccountNumber(bankDto.getAccountNumber());
		bank.setBalance(bankDto.getBalance());
		
		return bank;
	}
	public BankDto convertBankToBankDto(Bank bank) {
		BankDto bankDto=new BankDto();
		
		String[] s=bank.getAccountHolderName().split(" ");
		
		bankDto.setAccountHolderFirstName(s[0]);
		bankDto.setAccountHolderSecondName(s.length>1 ?s[1]:"");
		bankDto.setAccountNumber(bank.getAccountNumber());
		bankDto.setBalance(bank.getBalance());
		return bankDto;
		
	}
	
	public BankDto save(BankDto bankDto) {
		Bank b=convertBankDtoToBank(bankDto);
		
		repo.save(b);
		return convertBankToBankDto(b);
	}
	
	public Page<BankDto> findAll(Pageable pageable){
		return repo.findAll(pageable).map(t->convertBankToBankDto(t));
	}
	
	public BankDto getById(Integer id) {
		Optional<Bank> oId=repo.findById(id);
		
		if(oId.isPresent()) {
			Bank ids=oId.get();
			return convertBankToBankDto(ids);
		}
		CustomExceptions ex=new CustomExceptions("this id is not found");
		throw ex;
	}
	public BankDto deleteById(Integer id) {
		Optional<Bank> oId=repo.findById(id);
		if(oId.isPresent()) {
			Bank bId=oId.get();
			repo.deleteById(id);
			return convertBankToBankDto(bId);
		}
		CustomExceptions ex=new CustomExceptions("this id is not found");
		throw ex;
	}


	
	
}
