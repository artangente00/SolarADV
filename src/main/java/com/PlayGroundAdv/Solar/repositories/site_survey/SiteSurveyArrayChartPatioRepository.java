package com.PlayGroundAdv.Solar.repositories.site_survey;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.SiteSurveyArrayChartPatioEntity;

@Repository
public interface SiteSurveyArrayChartPatioRepository extends JpaRepository<SiteSurveyArrayChartPatioEntity, Long> {

	List<SiteSurveyArrayChartPatioEntity> findBySiteSurveyEntityId(Long idSiteSurvey);
	
}
