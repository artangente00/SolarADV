package com.PlayGroundAdv.Solar.entity;

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
@Table(name = "SiteSurveyTextAreaFieldsEntity")
public class SiteSurveyTextAreaFieldsEntity {

	@Id
	@SequenceGenerator(name="hibernate_sequence231", sequenceName = "hibernate_sequence231", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence231")  
	private Long id;
	
	@JoinColumn(name = "ID_SITE_SURVEY")
	@ManyToOne
	private SiteSurveyEntity siteSurveyEntity;
	
	@JoinColumn(name = "ID_COSTUM_FIELD")
	@ManyToOne
	private SiteSurveyCostumFieldEntity siteSurveyCostumFieldEntity;
	
    @Column(name="TEXT_BOX_CONTENT")
	private String textBoxContent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SiteSurveyEntity getSiteSurveyEntity() {
		return siteSurveyEntity;
	}

	public void setSiteSurveyEntity(SiteSurveyEntity siteSurveyEntity) {
		this.siteSurveyEntity = siteSurveyEntity;
	}

	public SiteSurveyCostumFieldEntity getSiteSurveyCostumFieldEntity() {
		return siteSurveyCostumFieldEntity;
	}

	public void setSiteSurveyCostumFieldEntity(SiteSurveyCostumFieldEntity siteSurveyCostumFieldEntity) {
		this.siteSurveyCostumFieldEntity = siteSurveyCostumFieldEntity;
	}

	public String getTextBoxContent() {
		return textBoxContent;
	}

	public void setTextBoxContent(String textBoxContent) {
		this.textBoxContent = textBoxContent;
	}

	@Override
	public String toString() {
		return "SiteSurveyTextAreaFieldsEntity [id=" + id + ", siteSurveyEntity=" + siteSurveyEntity
				+ ", siteSurveyCostumFieldEntity=" + siteSurveyCostumFieldEntity + ", textBoxContent=" + textBoxContent
				+ "]";
	}

	
	
}
