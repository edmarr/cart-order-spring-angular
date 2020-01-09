package com.tech.service;

import java.math.BigDecimal;
import java.util.List;

import com.tech.domain.Item;
import com.tech.dto.ItemDTO;
import com.tech.exception.BusinessException;
import com.tech.exception.RequestException;
import com.tech.exception.ResourceNotFoundException;
import com.tech.mapper.MapperObject;
import com.tech.repository.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private CustomSequenceService sequenceService;

    public ItemDTO create(ItemDTO dto) {

        if (dto.getName() == null || dto.getValueItem() == null || dto.getValueItem().equals(BigDecimal.ZERO))
            throw new RequestException("Error data  json");

        if (repository.findItemByName(dto.getName()) != null)
            throw new BusinessException("The name Item was used before that. Only once record is permited");

        Item entity = MapperObject.parse(dto, Item.class);
        entity.setId(sequenceService.getNextSequence("itemSequence"));

        return MapperObject.parse(repository.save(entity), ItemDTO.class);
    }

    public List<ItemDTO> findall() {
        return MapperObject.parse(repository.findAll(), ItemDTO.class);
    }

    public ItemDTO update(ItemDTO dto) {
        if (repository.findItemByName(dto.getName()) != null) {
            throw new BusinessException("The name Item was used before that. Only once record is permited");
        }
        Item entity = repository
                .save(Item.builder().id(dto.getId()).name(dto.getName()).valueItem(dto.getValueItem()).build());
        return MapperObject.parse(entity, ItemDTO.class);
    }

    public void delete(Integer id) {
        Item entity = checkResource(id);
        repository.delete(entity);
    }

    public ItemDTO findById(Integer id) {
        return MapperObject.parse(checkResource(id), ItemDTO.class);
    }

    private Item checkResource(Integer id) {
        Item entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found with id:  " + id));
        return entity;
    }

}