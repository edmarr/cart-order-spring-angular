package com.tech.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@lombok.Generated
public class CartItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private Integer id;
    private UserDTO user;
    private List<ItemCartDTO> itens;
}