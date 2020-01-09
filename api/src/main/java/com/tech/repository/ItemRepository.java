package com.tech.repository;

import com.tech.domain.Item;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, Integer> {
    Item findItemByName(String name);
}