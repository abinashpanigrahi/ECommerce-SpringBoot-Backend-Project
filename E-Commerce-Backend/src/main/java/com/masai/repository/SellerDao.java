package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.Seller;

public interface SellerDao extends JpaRepository<Seller, Integer> {

}
