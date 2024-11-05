package com.PlayGroundAdv.Solar.repositories.site_survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.SiteSurveyReqFieldSettingEntity;

@Repository
public interface SiteSurveyReqFieldSettingRepository extends JpaRepository<SiteSurveyReqFieldSettingEntity, Long> {
	
	SiteSurveyReqFieldSettingEntity findOneByStatereqfield(String statereqfield);
	
}
