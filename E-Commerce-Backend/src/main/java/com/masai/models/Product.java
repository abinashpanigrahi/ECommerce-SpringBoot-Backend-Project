package com.masai.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;

	@NotNull
	@Size(min = 3, max = 30, message = "Product name size should be between 3-30")
	private String productName;

	@NotNull
	@DecimalMin(value = "0.00")
	private Double price;

	private String description;

	@NotNull
	private String manufacturer;

	@NotNull
	@Min(value = 0)
	private Integer quantity;

	@Enumerated(EnumType.STRING)
	private CategoryEnum category;

	@Enumerated(EnumType.STRING)
	private ProductStatus status;

//	@ManyToMany(cascade = CascadeType.ALL)
//	private Order order;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Seller seller;

//	@ManyToMany
//	@JsonIgnore
//	private List<Cart> productCarts = new ArrayList<>();

}
