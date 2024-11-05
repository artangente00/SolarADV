package com.PlayGroundAdv.Solar.repositories.site_survey;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.SiteSurveyArrayChartEntity;

@Repository
public interface SiteSurveyArrayChartRepository extends JpaRepository<SiteSurveyArrayChartEntity, Long> {
	
	List<SiteSurveyArrayChartEntity> findBySiteSurveyEntityId(Long idSiteSurvey);

}
