package com.ttech.jwt.security.service.impl;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "roles")
public class Role {

	 @Id
	  private String id;	
	  private RoleType name;
}
