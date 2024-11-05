package com.PlayGroundAdv.Solar.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name = "PermitWeatherEntity")
public class PermitWeatherEntity implements Serializable {

	/**
	 * 
	 */ 
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="hibernate_sequence8", sequenceName = "hibernate_sequence8", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence8")  
	private Long id;
	
	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity permitEntity;
	
	@Column(name="ELEVATION")
	private String elevation;
	
	@Column(name="QUATRE_AVERAGE_HIGH")
	private String quatrePourCentAverageHigh;
	
	@Column(name="DEUX_AVERAGE_HIGH")
	private String deuxPourCentAverageHigh;
	
	@Column(name="EXTREME_MINIMUM")
	private String extremeMinimum;
	
	@Column(name="QUATRE_CENT_AVERAGE_HIGH_OTHER")
	private String quatrePourCentAvHighOther;
	
	@Column(name="DEUX_CENT_AVERAGE_HIGH_OTHER")
	private String deuxPourCentAverageHighOther;
	
	@Column(name="EXTREME_MINIMUM_OTHER")
	private String extremeMinimumOther;

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
	 * @return the elevation
	 */
	public String getElevation() {
		return elevation;
	}

	/**
	 * @param elevation the elevation to set
	 */
	public void setElevation(String elevation) {
		this.elevation = elevation;
	}

	/**
	 * @return the quatrePourCentAverageHigh
	 */
	public String getQuatrePourCentAverageHigh() {
		return quatrePourCentAverageHigh;
	}

	/**
	 * @param quatrePourCentAverageHigh the quatrePourCentAverageHigh to set
	 */
	public void setQuatrePourCentAverageHigh(String quatrePourCentAverageHigh) {
		this.quatrePourCentAverageHigh = quatrePourCentAverageHigh;
	}

	/**
	 * @return the deuxPourCentAverageHigh
	 */
	public String getDeuxPourCentAverageHigh() {
		return deuxPourCentAverageHigh;
	}

	/**
	 * @param deuxPourCentAverageHigh the deuxPourCentAverageHigh to set
	 */
	public void setDeuxPourCentAverageHigh(String deuxPourCentAverageHigh) {
		this.deuxPourCentAverageHigh = deuxPourCentAverageHigh;
	}

	/**
	 * @return the extremeMinimum
	 */
	public String getExtremeMinimum() {
		return extremeMinimum;
	}

	/**
	 * @param extremeMinimum the extremeMinimum to set
	 */
	public void setExtremeMinimum(String extremeMinimum) {
		this.extremeMinimum = extremeMinimum;
	}

	public PermitEntity getPermitEntity() {
		return permitEntity;
	}

	public void setPermitEntity(PermitEntity permitEntity) {
		this.permitEntity = permitEntity;
	}

	public String getQuatrePourCentAvHighOther() {
		return quatrePourCentAvHighOther;
	}

	public void setQuatrePourCentAvHighOther(String quatrePourCentAvHighOther) {
		this.quatrePourCentAvHighOther = quatrePourCentAvHighOther;
	}

	public String getDeuxPourCentAverageHighOther() {
		return deuxPourCentAverageHighOther;
	}

	public void setDeuxPourCentAverageHighOther(String deuxPourCentAverageHighOther) {
		this.deuxPourCentAverageHighOther = deuxPourCentAverageHighOther;
	}

	public String getExtremeMinimumOther() {
		return extremeMinimumOther;
	}

	public void setExtremeMinimumOther(String extremeMinimumOther) {
		this.extremeMinimumOther = extremeMinimumOther;
	}

	@Override
	public String toString() {
		return "PermitWeatherEntity [id=" + id + ", permitEntity=" + permitEntity + ", elevation=" + elevation
				+ ", quatrePourCentAverageHigh=" + quatrePourCentAverageHigh + ", deuxPourCentAverageHigh="
				+ deuxPourCentAverageHigh + ", extremeMinimum=" + extremeMinimum + ", quatrePourCentAvHighOther="
				+ quatrePourCentAvHighOther + ", deuxPourCentAverageHighOther=" + deuxPourCentAverageHighOther
				+ ", extremeMinimumOther=" + extremeMinimumOther + "]";
	}
	
	
	
}
