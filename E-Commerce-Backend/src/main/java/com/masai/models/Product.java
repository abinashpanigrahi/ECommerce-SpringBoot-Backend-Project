package com.masai.models;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	
	@NotNull
	private String productName;
	@NotNull
	private Double price;
	private String description;
	@NotNull
	private String manufacturer;
	@NotNull
	private Integer quantity;
	

//	@ManyToOne(cascade = CascadeType.ALL)
//	private Cart cart;
	@ManyToOne(cascade = CascadeType.ALL)
//	@JsonIgnore
	private Category category;
//	@ManyToMany(cascade = CascadeType.ALL)
//	private Order order;
//	@ManyToOne(cascade = CascadeType.ALL)
//	private Seller seller;
	
	

}
