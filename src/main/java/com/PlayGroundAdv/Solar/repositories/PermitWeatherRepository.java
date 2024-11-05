package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.PermitWeatherEntity;
import com.PlayGroundAdv.Solar.model.PermtiWeatherEntityResult;
import org.springframework.stereotype.Repository;

@Repository
public interface PermitWeatherRepository extends JpaRepository<PermitWeatherEntity,Long>{
	PermitWeatherEntity findByPermitEntityId(Long idPermit);

	public static final String PERMTI_WEATHER_RESULT = "SELECT new com.PlayGroundAdv.Solar.model.PermtiWeatherEntityResult " + 
			" (u.elevation, u.quatrePourCentAverageHigh, u.deuxPourCentAverageHigh, u.extremeMinimum, u.quatrePourCentAvHighOther, u.deuxPourCentAverageHighOther, u.extremeMinimumOther)"
			+ " from PermitWeatherEntity u where u.permitEntity.id = :p1";

	@Query(value = PERMTI_WEATHER_RESULT)
	PermtiWeatherEntityResult getPermtiWeatherEntityResult(@Param("p1") Long idProject);

	Long deleteByPermitEntityId(Long id);
}
