package com.masai.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	@PastOrPresent
	private LocalDate date;
	@NotNull
	@Enumerated(EnumType.STRING)
	private OrderStatusValues orderStatus;
	
	private Double total;
	
	private String cardNumber;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "customer_id", referencedColumnName = "customerId")
	private Customer customer;
	
	@OneToMany
	@JsonIgnore
	private List<CartItem> ordercartItems = new ArrayList<>();
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "address_id", referencedColumnName = "addressId")
	private Address address;
}
