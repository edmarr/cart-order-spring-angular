package com.tech.service;

import java.util.List;

import com.tech.domain.CartItem;
import com.tech.dto.CartItemDTO;
import com.tech.mapper.MapperObject;
import com.tech.repository.CartItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarItemService {

    @Autowired
    private CartItemRepository repository;

    @Autowired
    private CustomSequenceService sequenceService;

    public CartItemDTO create(CartItemDTO dto) {
        CartItem entity = null;
        entity = MapperObject.parse(dto, CartItem.class);
        entity.setId(sequenceService.getNextSequence("carItemSequence"));
        entity.setStatus(Boolean.TRUE);
        return MapperObject.parse(repository.save(entity), CartItemDTO.class);
    }

    public List<CartItemDTO> findAll() {
        List<CartItemDTO> list = MapperObject.parse(repository.findAll(), CartItemDTO.class);
        return list;
    }
}