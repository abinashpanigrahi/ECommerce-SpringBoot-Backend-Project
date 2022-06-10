package com.masai.models;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditCard {
	
	@Pattern(regexp = "[0-9]{16,18}", message = "Invalid card number")
	@NotNull
	private String cardNumber;
	
	@Pattern(regexp = "[0-9]{2}/[2-3]{1}[2-9]{1}", message = "Invalid validity. Enter in MM/YY format")
	private String cardValidity;
	
	@Pattern(regexp = "[0-9]{3}", message = "Invalid CVV. Must be numeric 3 digits length.")
	private String cardCVV;
	
}
