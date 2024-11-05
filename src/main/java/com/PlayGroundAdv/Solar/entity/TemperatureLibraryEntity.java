package com.PlayGroundAdv.Solar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEMPERATURE_LIBRARY_ENTITY")
public class TemperatureLibraryEntity {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="POSTAL_CODE")
	private String postalCode;
	 
	@Column(name="FOUR_PERCENT_AVERAGE")
	private Integer fourPerCentAverage;
	 
	@Column(name="TWO_PERCENT_AVERAGE")
	private Integer twoPerCentAverage;
	
	@Column(name="EXTREME_MINIMUM")
	private Integer extremeMinimum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Integer getFourPerCentAverage() {
		return fourPerCentAverage;
	}

	public void setFourPerCentAverage(Integer fourPerCentAverage) {
		this.fourPerCentAverage = fourPerCentAverage;
	}

	public Integer getTwoPerCentAverage() {
		return twoPerCentAverage;
	}

	public void setTwoPerCentAverage(Integer twoPerCentAverage) {
		this.twoPerCentAverage = twoPerCentAverage;
	}

	public Integer getExtremeMinimum() {
		return extremeMinimum;
	}

	public void setExtremeMinimum(Integer extremeMinimum) {
		this.extremeMinimum = extremeMinimum;
	}


	
	
}
