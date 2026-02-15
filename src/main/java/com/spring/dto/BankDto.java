package com.spring.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BankDto {
	
	
	
	@NotNull(message = "account number must be entered")
	private String accountNumber;
	@NotBlank(message = "account holder first name must be entered")
	private String accountHolderFirstName;
	@NotBlank(message = "account holder second name must be entered")
	private String accountHolderSecondName;
	@Digits(integer = 3,fraction = 2,message = "invalid balance")
	private Double balance;

}
