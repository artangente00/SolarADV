package com.PlayGroundAdv.Solar.repositories.site_survey;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.SiteSurveyCostumFieldEntity;


@Repository
public interface SiteSurveyCostumFieldRepository extends JpaRepository<SiteSurveyCostumFieldEntity, Long>{
	
	List<SiteSurveyCostumFieldEntity> findByState(String state);
	List<SiteSurveyCostumFieldEntity> findByStateAndDeleted(String state, Boolean del);

}
