package com.PlayGroundAdv.Solar.repositories.site_survey;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.SiteSurveyTextAreaFieldsEntity;

@Repository
public interface SiteSurveyTextAreaFieldsRepository extends JpaRepository<SiteSurveyTextAreaFieldsEntity, Long> {
	
	List<SiteSurveyTextAreaFieldsEntity> findBySiteSurveyEntityIdAndSiteSurveyCostumFieldEntityTabName(Long idSite, String tabName);
	List<SiteSurveyTextAreaFieldsEntity> findBySiteSurveyEntityIdAndSiteSurveyCostumFieldEntityState(Long idSite, String state);
	List<SiteSurveyTextAreaFieldsEntity> findBySiteSurveyEntityId(Long siteSurveyID);

}
