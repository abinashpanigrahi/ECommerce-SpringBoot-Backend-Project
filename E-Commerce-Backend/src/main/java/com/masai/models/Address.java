package com.masai.models;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
	
	@NotNull
	@Pattern(regexp = "[A-Za-z0-9\\s-]{3,}", message = "Not a valid street no")
	private String streetNo;
	
	@Pattern(regexp = "[A-Za-z0-9\\s-]{3,}", message = "Not a valid building name")
	private String buildingName;
	
	@Pattern(regexp = "[A-Za-z0-9\\s-]{3,}", message = "Not a valid locality name")
	private String locality;
	
	@NotNull(message = "City name cannot be valid")
	@Pattern(regexp = "[A-Za-z\\s]{2,}", message = "Not a valid city name")
	private String city;
	
	@NotNull
	@Embedded
	private StateEnum state;
	
	
	@Pattern(regexp = "[0-9]{6}", message = "Pincode not valid. Must be 6 digits")
	private String pincode;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customerId;
	
}
