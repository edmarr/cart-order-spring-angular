package com.tech.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@lombok.Generated
@Document(collection = "tb_cart_itens")
public class CartItem implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private Integer id;
    private User user;
    private List<ItemCart> itens;
    private Boolean status;
}