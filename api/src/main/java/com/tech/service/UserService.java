package com.tech.service;

import java.util.List;

import com.tech.domain.User;
import com.tech.dto.UserDTO;
import com.tech.exception.BusinessException;
import com.tech.exception.RequestException;
import com.tech.exception.ResourceNotFoundException;
import com.tech.mapper.MapperObject;
import com.tech.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private CustomSequenceService sequenceService;

    public UserDTO create(UserDTO dto) {

        if (dto.getName() == null || dto.getEmail() == null)
            throw new RequestException("Error data  json");

        if (repository.findUserByEmail(dto.getEmail()) != null)
            throw new BusinessException("The email was used before that. Only once record is permited");

        User entity = MapperObject.parse(dto, User.class);
        entity.setId(sequenceService.getNextSequence("userSequence"));

        return MapperObject.parse(repository.save(entity), UserDTO.class);
    }

    public List<UserDTO> findall() {
        return MapperObject.parse(repository.findAll(), UserDTO.class);
    }

    public UserDTO findById(Integer id) {
        return MapperObject.parse(checkResource(id), UserDTO.class);
    }

    public void delete(Integer id) {
        User entity = checkResource(id);
        repository.delete(entity);
    }

    public UserDTO update(UserDTO dto) {
        checkResource(dto.getId());
        if (repository.findUserByEmail(dto.getName()) != null) {
            throw new BusinessException("The email was used before that. Only once record is permited");
        }
        User entity = repository.save(User.builder().id(dto.getId()).name(dto.getName()).email(dto.getEmail()).build());
        return MapperObject.parse(entity, UserDTO.class);
    }

    private User checkResource(Integer id) {
        User entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found with id:  " + id));
        return entity;
    }

}