package com.masai.models;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartId;

	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "cart_product_mapping",
	joinColumns = {
			@JoinColumn(name = "cart_id", referencedColumnName = "cartId")
	},
	inverseJoinColumns = {
			@JoinColumn(name = "product_id", referencedColumnName = "productId")
	})
	private List<Product> products = new ArrayList<>();
	
	private Double cartTotal;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Customer customer;

}






