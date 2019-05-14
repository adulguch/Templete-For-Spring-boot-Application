package com.test.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.envers.Audited;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class AuditModel.
 */
/**
 * The Class AuditModel.
 */
@EqualsAndHashCode
@MappedSuperclass
@Audited
public abstract class AuditModel {

	public static final String CREATED_BY = "CREATED_BY";
	public static final String MODIFIED_BY = "MODIFIED_BY";
	public static final String CREATED_DATE = "CREATED_DATE";
	public static final String MODIFIED_DATE = "MODIFIED_DATE";	
	public static final String PERFORMED_ACTION = "PERFORMED_ACTION";
	public static final String PERFORMED_BY = "PERFORMED_BY";
	public static final String AUDIT_KEY_ID = "AUDIT_KEY_ID";
	
	
	@Getter
	@Setter
	@Column(name = "CREATED_BY" , updatable =  false)    
	public String createdBy;

	@Getter
	@Setter
	//@JsonProperty
	//@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "CREATED_DATE",  updatable =  false)    
	public Date createdDate;

	@Getter
	@Setter
	@Column(name = "MODIFIED_BY")
	public String modifiedBy;

	@Getter
	@Setter
	@Column(name = "MODIFIED_DATE")    
	public Date modifiedDate;

	
   /* @PreUpdate
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

*/	
}
