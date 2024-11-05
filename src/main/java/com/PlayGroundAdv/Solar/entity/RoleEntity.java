package com.PlayGroundAdv.Solar.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name = "RoleEntity")
public class RoleEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="Role_Seq", sequenceName = "Role_Seq", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Role_Seq")
	private Long id;
	
	@Column(name="DESIGNATION_NAME")
	private String designationName;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="IS_SUPER_USER")
	private Boolean isSuperUser;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the designationName
	 */
	public String getDesignationName() {
		return designationName;
	}

	/**
	 * @param designationName the designationName to set
	 */
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the isSuperUser
	 */
	public Boolean getIsSuperUser() {
		return isSuperUser;
	}

	/**
	 * @param isSuperUser the isSuperUser to set
	 */
	public void setIsSuperUser(Boolean isSuperUser) {
		this.isSuperUser = isSuperUser;
	}
	
	
	
}



	
