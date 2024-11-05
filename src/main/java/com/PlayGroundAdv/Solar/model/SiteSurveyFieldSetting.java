package com.PlayGroundAdv.Solar.model;

import com.PlayGroundAdv.Solar.entity.SiteSurveyReqFieldLogicEntity;
import com.PlayGroundAdv.Solar.entity.SiteSurveyReqFieldSettingEntity;

public class SiteSurveyFieldSetting {

	private Long id;

	SiteSurveyReqFieldSettingEntity siteSurveyReqFieldSettingEntity;
	SiteSurveyReqFieldLogicEntity siteSurveyReqFieldLogicEntity;

	public SiteSurveyFieldSetting() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SiteSurveyReqFieldSettingEntity getSiteSurveyReqFieldSettingEntity() {
		return siteSurveyReqFieldSettingEntity;
	}

	public void setSiteSurveyReqFieldSettingEntity(SiteSurveyReqFieldSettingEntity siteSurveyReqFieldSettingEntity) {
		this.siteSurveyReqFieldSettingEntity = siteSurveyReqFieldSettingEntity;
	}

	public SiteSurveyReqFieldLogicEntity getSiteSurveyReqFieldLogicEntity() {
		return siteSurveyReqFieldLogicEntity;
	}

	public void setSiteSurveyReqFieldLogicEntity(SiteSurveyReqFieldLogicEntity siteSurveyReqFieldLogicEntity) {
		this.siteSurveyReqFieldLogicEntity = siteSurveyReqFieldLogicEntity;
	}

	public SiteSurveyFieldSetting(Long id, SiteSurveyReqFieldSettingEntity siteSurveyReqFieldSettingEntity,
			SiteSurveyReqFieldLogicEntity siteSurveyReqFieldLogicEntity) {
		super();
		this.id = id;
		this.siteSurveyReqFieldSettingEntity = siteSurveyReqFieldSettingEntity;
		this.siteSurveyReqFieldLogicEntity = siteSurveyReqFieldLogicEntity;
	}

	@Override
	public String toString() {
		return "SiteSurveyFieldSetting [id=" + id + ", siteSurveyReqFieldSettingEntity="
				+ siteSurveyReqFieldSettingEntity + ", siteSurveyReqFieldLogicEntity=" + siteSurveyReqFieldLogicEntity
				+ "]";
	}

}
