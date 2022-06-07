package com.masai.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueNumberAndStatus", columnNames = { "categoryName" }) })
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer categoryId;
	
	@Column(unique = true)
	private String categoryName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Product> prodList = new HashSet<Product>();
	

}
