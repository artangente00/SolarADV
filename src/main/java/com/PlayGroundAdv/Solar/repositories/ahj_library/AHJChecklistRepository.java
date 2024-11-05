package com.PlayGroundAdv.Solar.repositories.ahj_library;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJChecklistEntity;
import com.PlayGroundAdv.Solar.model.DropdownOptions;

@Repository
public interface AHJChecklistRepository extends JpaRepository<AHJChecklistEntity, Long> {

	Page<AHJChecklistEntity> findByStateAndAhjAndCountyOrStateAndAhjAndCountyIsNullAndAhj(String state, String ahj,
			String county, String state2, String ahj2, String county2, Pageable pageable);

	Page<AHJChecklistEntity> findByStateAndAhj(String state, String ahj, Pageable pageable);

	Page<AHJChecklistEntity> findByStateAndCountyOrStateAndCountyIsNullAndAhj(String state, String county,
			String state2, String county2, Pageable pageable);

	Page<AHJChecklistEntity> findByAhjAndCountyOrAhjAndCountyIsNullAndAhj(String ahj, String county, String ahj2,
			String county2, Pageable pageable);

	Page<AHJChecklistEntity> findByState(String state, Pageable pageable);

	Page<AHJChecklistEntity> findByAhj(String ahj, Pageable pageable);

	Page<AHJChecklistEntity> findByCountyOrCountyIsNullAndAhj(String county, String ahjAsCounty, Pageable pageable);

	List<AHJChecklistEntity> findByState(String state);

	AHJChecklistEntity findFirstByStateAndAhjInIgnoreCaseOrStateAndCountyInIgnoreCase(String state,
			List<String> ahjNames, String state2, List<String> ahjCounty);

	List<AHJChecklistEntity> findByStateAndAhj(String state, String ahj);

	Boolean existsByAhjAndState(String ahj, String state);

	Boolean existsByAhjAndStateAndIdNot(String ahj, String state, Long ahjId);

	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.DropdownOptions(u.ahj,u.ahj) from AHJChecklistEntity u group by u.ahj order by u.ahj")
	List<DropdownOptions> getAhjList();

	@Query(value = "SELECT u.state from AHJChecklistEntity u group by u.state order by u.state")
	List<String> getStateList();

	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.DropdownOptions(COALESCE(u.county,u.ahj),COALESCE(u.county,u.ahj)) from AHJChecklistEntity u group by COALESCE(u.county,u.ahj) order by COALESCE(u.county,u.ahj)")
	List<DropdownOptions> getCountyList();

	@Query(value = "SELECT u.ifc from AHJChecklistEntity u where u.id = :id")
	String getIfc(@Param("id") Long id);

}
