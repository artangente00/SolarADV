package com.PlayGroundAdv.Solar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
 * @author nader
 */
@Entity
@Table(name = "AuthorityHavingJurisdctionEntity")
public class AuthorityHavingJurisdctionEntity {
	 public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getZipCodes() {
		return zipCodes;
	}

	public void setZipCodes(String zipCodes) {
		this.zipCodes = zipCodes;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getBuildingPhone() {
		return buildingPhone;
	}

	public void setBuildingPhone(String buildingPhone) {
		this.buildingPhone = buildingPhone;
	}

	public String getFireDept() {
		return fireDept;
	}

	public void setFireDept(String fireDept) {
		this.fireDept = fireDept;
	}

	public String getFireDeptPhone() {
		return fireDeptPhone;
	}

	public void setFireDeptPhone(String fireDeptPhone) {
		this.fireDeptPhone = fireDeptPhone;
	}

	/**
	 *  Permit Engineer Entity
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="hibernate_sequence13", sequenceName = "hibernate_sequence13", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence13")  
	private Long id;
	
	
	@Column(name="ZIP_CODES")
	private String zipCodes;
	
	@Column(name="BUILDING")
	private String building;
	
	@Column(name="BUILDING_PHONE")
	private String buildingPhone;
	
	@Column(name="FIREDEPT")
	private String fireDept;
	
	@Column(name="FIREDEPT_Phone")
	private String fireDeptPhone;
	
}
