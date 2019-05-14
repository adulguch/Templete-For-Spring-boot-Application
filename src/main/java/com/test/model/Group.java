package com.test.model;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.envers.Audited;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.test.AppUtils.AppUtils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="GROUPS")
@Audited
//@JsonIgnoreProperties(value={"users", "studies"}, allowSetters=true)
public class Group extends AuditModel implements Serializable{
	
	public static final String GROUP_ID_PK = "GROUP_ID_PK";
	public static final String NAME = "NAME";
	public static final String DESCRIPTION = "DESCRIPTION";
	public static final String ACTIVE = "ACTIVE";
	
	
	public Group(){
		this.users = new HashSet<>();
	}
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
    
	@Size(max=50, message="Group Name should be upto 50 characters")
	@Pattern(regexp= "^[A-Za-z0-9_-]*$", message= "Invalid Group Name")
	@Column(name="NAME", nullable=false)
	private String name;
	
	//@Size(max=250, message="Group Description should be upto 250 characters")
	@Column(name="DESCRIPTION")
	private String description;
	
	@Min(0)
	@Max(1)
	@Column(name="ACTIVE")
	private int active;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="GROUP_USER", joinColumns=@JoinColumn(name="GROUP_ID"), inverseJoinColumns=@JoinColumn(name="USER_ID"))
	//@Cascade(value={CascadeType.REMOVE, CascadeType.SAVE_UPDATE})
	private Set<AppUser> users;

	@PreUpdate
    void onUpdatePersist() {
		this.modifiedDate = new Date();
		this.modifiedBy = AppUtils.getUserName();
    }

    @PrePersist
    void onPrePersist() {
		this.modifiedDate = new Date();
		this.createdDate = new Date();
		this.modifiedBy = AppUtils.getUserName();
		this.createdBy = AppUtils.getUserName();

    }
	
	
	  
}
