package com.PlayGroundAdv.Solar.repositories.site_survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.SiteSurveyReqFieldLogicEntity;

@Repository
public interface SiteSurveyReqFieldLogicRepository extends JpaRepository<SiteSurveyReqFieldLogicEntity, Long>{
	
	SiteSurveyReqFieldLogicEntity findOneByStatereqfield(String statereqfield);

}
