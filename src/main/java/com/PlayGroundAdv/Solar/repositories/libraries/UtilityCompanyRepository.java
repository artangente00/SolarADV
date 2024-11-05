package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;

@Repository
public interface UtilityCompanyRepository extends JpaRepository<ElectricalUtilityEntity,Long>, JpaSpecificationExecutor<ElectricalUtilityEntity>{
	
	// C.I 06/01/2020 Get All UtilityCompany where the ID In UtilityCompany ID List
	List<ElectricalUtilityEntity> findByIdInOrderByZipAscUtilityCompanyNameAsc(List<Long> utilityCompanyIdList);	

	// C.I 06/01/2020
	public static final String FILTER_UTILITY_COMPANY_LIST_MODEL = "SELECT u.id from ElectricalUtilityEntity u where CONCAT('%',upper(u.utilityCompanyName),'%') LIKE CONCAT('%',upper(:p1),'%') and ((:p2 !='' And u.zip LIKE :p2) OR (:p2 ='')) and (u.id NOT IN :p3 OR :p3  = NULL ) and u.isDeleted = :p4 ORDER BY u.zip  ,u.utilityCompanyName";


	@Query(value = FILTER_UTILITY_COMPANY_LIST_MODEL)
	public List<Long> filteUtilityCompanyListModel(@Param("p1") String ucName,@Param("p2") String zipCode,@Param("p3") List<Long> roofMaterialTypeList, @Param("p4") Boolean isDeleted);
	
	ElectricalUtilityEntity findById(long id);
	Long countByZipAndUtilityCompanyNameAndIsDeleted(String zipCode, String ucName,boolean isDeleted);
	Long countByIdNotAndZipAndUtilityCompanyNameAndIsDeleted(long id,String zipCode, String ucName,boolean isDeleted );

	Page<ElectricalUtilityEntity> findByIsDeleted(Boolean isDel, Pageable page);

} 
