package com.PlayGroundAdv.Solar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
 * @author Soumeya CR-486 UpdateHO/Site InfoTab
 */
@Entity
@Table(name = "CityEntity")
public class CityEntity {
	
	/*  Permit Engineer Entity
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="city_sequence", sequenceName = "city_sequence", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="city_sequence")  
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="COUNTRY")
	private String county;
	
	@Column(name="CITYAUTHORITY")
	private Boolean cityAuthority;
	
	@Column(name="COUNTYAUTHORITY")
	private Boolean countyAuthority;
	
	@Column(name="PHONE")
	private String phone;
	
	@Column(name="STATE")
	private String state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Boolean getCityAuthority() {
		return cityAuthority;
	}

	public void setCityAuthority(Boolean cityAuthority) {
		this.cityAuthority = cityAuthority;
	}

	public Boolean getCountyAuthority() {
		return countyAuthority;
	}

	public void setCountyAuthority(Boolean countyAuthority) {
		this.countyAuthority = countyAuthority;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	

}
