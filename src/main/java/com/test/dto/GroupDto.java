//package com.test.dto;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.Set;
//
//import com.doloop.domain.AuditModel;
//import com.doloop.persistence.model.AppUser;
//
//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;
//
//@Setter
//@Getter
//public class GroupDto  extends AuditModel implements Serializable {
//	private static final long serialVersionUID = 1L;
//	
//	public GroupDto() {
//		
//	}
//	
//	private Long id;
//	private String name;
//	private String description;
//	private int active;
//	private Set<AppUser> users;
//	
//	
//	@Builder
//	public GroupDto(Long id, String name, String description, int active, Set<AppUser> users, String createdBy,
//			Date createdDate, String modifiedBy, Date modifiedDate) {
//		super();
//		super.createdBy = createdBy;
//		super.createdDate = createdDate;
//		super.modifiedBy = modifiedBy;
//		super.modifiedDate = modifiedDate;
//		this.id = id;
//		this.name = name;
//		this.description = description;
//		this.active = active;
//		this.users = users;
//	}
//	
//	
//}
