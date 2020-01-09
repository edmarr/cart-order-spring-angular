package com.tech.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    
	private Integer id;
    private String name;
    private BigDecimal valueItem;

}