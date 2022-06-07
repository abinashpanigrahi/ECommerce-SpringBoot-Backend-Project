package com.masai.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Product {

	
	@Id
	private Integer productId;
	private Integer reqQuantity;
	private String productName;
	@ManyToOne(cascade = CascadeType.ALL)
	private Cart cart;
	
}
