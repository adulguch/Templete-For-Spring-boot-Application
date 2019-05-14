package com.test.dto;

import java.io.Serializable;
import java.util.Set;

import com.test.model.Group;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	AppUserDto() {

	}

	private Long userId;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
	private Integer active;
	private Set<Group> groups;
	
	
	

	@Builder
	AppUserDto(Long userId, String userName, String firstName, String lastName, String email,
			String telephone, Integer active, Set<Group> groups ) {
		this.userId = userId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.telephone = telephone;
		this.active = active;
		this.groups = groups;   

	}
}
