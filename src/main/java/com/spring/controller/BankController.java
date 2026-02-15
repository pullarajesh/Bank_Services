package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.BankDto;

import com.spring.service.BankService;

import jakarta.validation.Valid;

@RestController
public class BankController {

	@Autowired
	private BankService bankService;
	
	@PostMapping("/save")
	public ResponseEntity<BankDto> save(@RequestBody @Valid BankDto bankDto){
		BankDto bank=bankService.save(bankDto);
		return new ResponseEntity<BankDto>(bank,HttpStatus.CREATED);
	}
	@GetMapping("/getAll")
	public Page<BankDto> getAll(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "2")int size){
		
		Pageable pag=PageRequest.of(page, size);
		return bankService.findAll(pag);
	}
	@GetMapping("getById/{id}")
	public ResponseEntity<?> getBankById(@PathVariable Integer id) {

	    if (id == null) {
	        return ResponseEntity.badRequest().body("Id cannot be null");
	    }

	    BankDto bank = bankService.getById(id);

	    if (bank == null) {
	        return ResponseEntity.notFound().build();
	    }

	    return ResponseEntity.ok(bank);
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BankDto> deleteId(@PathVariable Integer id){
		BankDto ids=bankService.deleteById(id);
		return new ResponseEntity<BankDto>(ids,HttpStatus.OK);
	}
	

	
}
