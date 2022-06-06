package com.masai.models;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

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
	@NotNull
	@Column(name = "cartId")
	private Integer cartId;

//	
//	@OneToMany(cascade = CascadeType.ALL)
//	@ElementCollection
//    @CollectionTable(name = "product_mapping", 
//     joinColumns = {@JoinColumn(name = "cart_id", referencedColumnName = "cartId")})
//    @MapKeyColumn(name = "product_name")
//    @Column(name = "quantity")
//	private Map<Product,Integer> products = new HashMap<>();
//	
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Product> Products = new ArrayList<>();
	

	
	private Double cartTotal;
	
	
	private Customer myCustomer;
	
	
	
}






