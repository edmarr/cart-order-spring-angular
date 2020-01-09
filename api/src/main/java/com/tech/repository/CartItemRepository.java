package com.tech.repository;

import com.tech.domain.CartItem;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartItemRepository extends MongoRepository<CartItem, Integer> {

    
	
}