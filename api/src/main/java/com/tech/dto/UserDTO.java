package com.tech.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable{

    private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String email;


}