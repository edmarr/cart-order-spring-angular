package com.tech.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tb_itens_cart")
public class ItemCart implements Serializable{
    private static final long serialVersionUID = 1L;
    private Item item;
    private int quantity;

}