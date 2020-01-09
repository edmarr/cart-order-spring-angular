package com.tech.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tech.dto.UserDTO;
import com.tech.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/api/users")
@Api(value = "Users EndPoint", description = "REST API for User", tags = { "UsersEndpoint" })
public class UserController{

    @Autowired 
    private UserService service;

    @RequestMapping(method = RequestMethod.POST)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
        return new ResponseEntity<UserDTO>(service.create(dto), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> findAll() {
        return service.findall();
    }

    @GetMapping("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO update(@RequestBody UserDTO item) {
        return service.update(item);
    }


}