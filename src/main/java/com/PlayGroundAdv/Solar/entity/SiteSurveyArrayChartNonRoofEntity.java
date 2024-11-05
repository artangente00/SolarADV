
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
@Table(name="Site_Survey_Array_Chart_Non_Roof_Entity")
public class SiteSurveyArrayChartNonRoofEntity implements Serializable {

	
private static final long serialVersionUID = 1L;
	

	@Id
	@SequenceGenerator(name="hibernate_sequence17", sequenceName = "hibernate_sequence17", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence17")  
	private Long id;

	@JoinColumn(name = "ID_SITE_SURVEY")
	@ManyToOne
	private SiteSurveyEntity siteSurveyEntity;
	
	
	@Column(name="ARRAY_SKETCH")
	private Integer arraySketch;
	
	@Column(name="AZIMUTH")
	private String azimuth;
	
	@Column(name="ROOF_PITCH")
	private String roofPitch;
	
	@Column(name="MODULE_TILS")
	private Boolean moduleTils;
	
	@Column(name="EAVE_OVER_HANG")
	private String eaveOverHang;
	
	@Column(name="EAVE_OVER_HANG_OTHER")
	private String eaveOverHangOther;
	
	@Column(name="MODEL_VALUE")
	private String modelvalue;

	@Column(name="MODULE_QTY")
	private String moduleQty;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @return the arraySketch
	 */
	public Integer getArraySketch() {
		return arraySketch;
	}

	/**
	 * @return the azimuth
	 */
	public String getAzimuth() {
		return azimuth;
	}

	/**
	 * @return the roofPitch
	 */
	public String getRoofPitch() {
		return roofPitch;
	}

	/**
	 * @return the moduleTils
	 */
	public Boolean getModuleTils() {
		return moduleTils;
	}

	/**
	 * @return the eaveOverHang
	 */
	public String getEaveOverHang() {
		return eaveOverHang;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	

	/**
	 * @param arraySketch the arraySketch to set
	 */
	public void setArraySketch(Integer arraySketch) {
		this.arraySketch = arraySketch;
	}

	/**
	 * @param azimuth the azimuth to set
	 */
	public void setAzimuth(String azimuth) {
		this.azimuth = azimuth;
	}

	/**
	 * @param roofPitch the roofPitch to set
	 */
	public void setRoofPitch(String roofPitch) {
		this.roofPitch = roofPitch;
	}

	/**
	 * @param moduleTils the moduleTils to set
	 */
	public void setModuleTils(Boolean moduleTils) {
		this.moduleTils = moduleTils;
	}

	/**
	 * @param eaveOverHang the eaveOverHang to set
	 */
	public void setEaveOverHang(String eaveOverHang) {
		this.eaveOverHang = eaveOverHang;
	}

	/**
	 * @return the modelvalue
	 */
	public String getModelvalue() {
		return modelvalue;
	}

	/**
	 * @param modelvaule the modelvalue to set
	 */
	public void setModelvalue(String modelvalue) {
		this.modelvalue = modelvalue;
	}

	public String getEaveOverHangOther() {
		return eaveOverHangOther;
	}

	public String getModuleQty() {
		return moduleQty;
	}

	public void setModuleQty(String moduleQty) {
		this.moduleQty = moduleQty;
	}

	
	public void setEaveOverHangOther(String eaveOverHangOther) {
		this.eaveOverHangOther = eaveOverHangOther;
	}


	
	public SiteSurveyEntity getSiteSurveyEntity() {
		return siteSurveyEntity;
	}


	public void setSiteSurveyEntity(SiteSurveyEntity siteSurveyEntity) {
		this.siteSurveyEntity = siteSurveyEntity;
	}


	@Override
	public String toString() {
		return "SiteSurveyArrayChartEntity [id=" + id + ", SiteSurveyEntity=" + siteSurveyEntity + ", arraySketch="
				+ arraySketch + ", azimuth=" + azimuth + ", roofPitch=" + roofPitch + ", moduleTils=" + moduleTils
				+ ", eaveOverHang=" + eaveOverHang + ", eaveOverHangOther=" + eaveOverHangOther + ", modelvalue="
				+ modelvalue + ", moduleQty=" + moduleQty + "]";
	}

	

}
