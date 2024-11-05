package com.PlayGroundAdv.Solar.repositories.users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.users.RoofStructureSetting;
import com.PlayGroundAdv.Solar.model.users.RoofStructureSettingDto;

public interface RoofStructureSettingRepository extends JpaRepository<RoofStructureSetting, Long> {

	public static final String FIND_BY_USER = "SELECT new com.PlayGroundAdv.Solar.model.users.RoofStructureSettingDto"
			+ "(u.id, u.state, u.rafterTrussDesign, u.crossSectionSize, u.rafterTrussDesignOther, u.crossSectionSizeOther, u.userId.id)"
			+ " from RoofStructureSetting u where u.userId.id = :userId";
	public static final String AND_STATE = " and u.state = :state";

	Boolean existsByStateAndUserIdId(String state, Long userId);

	Boolean existsByStateAndUserIdIdAndIdNotIn(String state, Long userId, List<Long> ids);

	@Query(value = FIND_BY_USER)
	List<RoofStructureSettingDto> findByUser(@Param("userId") Long userId);

	@Query(value = FIND_BY_USER + AND_STATE)
	RoofStructureSettingDto findByUserAndState(@Param("userId") Long userId, @Param("state") String state);
}
