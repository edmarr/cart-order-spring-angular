package com.tech.controller;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tech.dto.CartItemDTO;
import com.tech.service.CarItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/carts")
@Api(value = "Cart EndPoint", description = "REST API for User", tags = { "CartsEndpoint" })
public class CartController {

    @Autowired
    private CarItemService service;


    @RequestMapping(method = RequestMethod.POST)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<CartItemDTO> create(@RequestBody CartItemDTO dto) {
        return new ResponseEntity<CartItemDTO>(service.create(dto), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON)
    public List<CartItemDTO> findAll() {
        return service.findAll();
    }
}