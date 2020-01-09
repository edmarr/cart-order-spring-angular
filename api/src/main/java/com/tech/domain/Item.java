package com.tech.domain;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Document(collection = "tb_itens")
public class Item implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
	private Integer id;
    private String name;
    private BigDecimal valueItem;

}