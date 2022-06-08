package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.CartItem;

public interface CartItemDao extends JpaRepository<CartItem, Integer>{

}
