package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;
import com.PlayGroundAdv.Solar.model.ElectricalUtilityModel;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectricalUtilityRepository extends JpaRepository<ElectricalUtilityEntity, Long> {

	@Query("SELECT new com.PlayGroundAdv.Solar.model.ElectricalUtilityModel(u.id, u.utilityCompanyName, u.phone, u.state, u.zip, "
			+ " u.utilityNumber, u.utilityType, u.county, u.aCDReq, u.pVMReq, "
			+ " u.aCDPVMOrientation, u.mappingValue) from ElectricalUtilityEntity u where u.isDeleted = :p2 and u.zip = :p1  ORDER BY u.utilityCompanyName ")
	List<ElectricalUtilityModel> getByZipAndIsDeletedOrderByUtilityCompanyName(@Param("p1") String zipCode,
			@Param("p2") Boolean deleted);

	@Query("SELECT DISTINCT u.utilityCompanyName from ElectricalUtilityEntity u where u.isDeleted = false ORDER BY u.utilityCompanyName ")
	List<String> getUtilityList();

	@Query("SELECT  u.utilityCompanyName from ElectricalUtilityEntity u where u.id = :p1")
	String getUtilityCompanyName(@Param("p1") Long id);

	@Query("SELECT u.mappingValue from ElectricalUtilityEntity u where u.id = :p1 ")
	String getMappingValue(@Param("p1") Long id);
}
