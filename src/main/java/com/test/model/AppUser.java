package com.test.model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Entity (name= "APP_USER")
@Audited

@Getter
@Setter
/*@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "userId", scope=AppUser.class)*/
//@JsonIgnoreProperties(value={"groups"}, allowSetters=true)
public class AppUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AppUser(){
		//this.studies = new HashSet<>();
	}

	@Id  
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "USER_ID", nullable=false)
    private Long userId;

	
    @Column(name = "USER_NAME") 
    private String userName;
    
    
    @Column(name = "FIRST_NAME") 
    private String firstName;
    
    
    @Column(name = "LAST_NAME") 
    private String lastName;
    
    
    @Column(name = "EMAIL") 
    private String email;

    
    @Column(name = "TELEPHONE")     
	private String telephone;
    
    @Column(name="ACTIVE",nullable = true ,  columnDefinition="INT DEFAULT 0")
    private Integer active;
    
    /*@Column(name="PASSWORD",nullable = false, length=255)
    private String password;*/
    
//    @ManyToMany(mappedBy="users", fetch = FetchType.LAZY)
//    @Cascade(value={CascadeType.REMOVE, CascadeType.SAVE_UPDATE})
//    private Set<Group> groups;
//    
    @Override
    public int hashCode(){
    	return userId.hashCode();
    }
    
    @Override
    public boolean equals(Object o){
    	AppUser u = (AppUser)o;
    	return this.userId == u.userId; 
    }
}
