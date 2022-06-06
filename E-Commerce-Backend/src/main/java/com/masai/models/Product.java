package com.masai.models;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private String productName;
	private Double price;
	private String description;
	private String manufacturer;
	private Integer quantity;
	

//	@ManyToOne(cascade = CascadeType.ALL)
//	private Cart cart;
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Category category;
//	@ManyToMany(cascade = CascadeType.ALL)
//	private Order order;
//	@ManyToOne(cascade = CascadeType.ALL)
//	private Seller seller;
	
	

}
