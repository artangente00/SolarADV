package com.PlayGroundAdv.Solar.repositories.sheets;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.PlansetCustomizeSheets;
import org.springframework.stereotype.Repository;

@Repository
public interface PlansetCustomizeSheetsRepository extends JpaRepository<PlansetCustomizeSheets, Long>, JpaSpecificationExecutor<PlansetCustomizeSheets> {

	Page<PlansetCustomizeSheets> findByIsDeleted(Boolean isDeleted,Pageable pageable);
	Page<PlansetCustomizeSheets> findByIdIn(List<Long> idsList,Pageable pageable);
	
	public static final String SELECT = "SELECT u from PlansetCustomizeSheets u where u.isDeleted= false AND ";
	public static final String NOT_IN = " u.pdfName NOT IN :p1 AND ";
	public static final String IN = " u.pdfName IN :p1 AND ";
	public static final String FILTER_BY_PROJECT = " ( (upper(u.utilityCompany) =:p4 OR u.utilityCompany IS NULL OR u.utilityCompany = '' )"
			+ " AND ( (:p6 IN (select v.userId from UserCustomizeSheets v where v.userId.id = :p6 AND v.sheetId = u.id)) OR (not exists (select 1 from UserCustomizeSheets v where v.sheetId = u.id))  ) "			
			+ " AND ((u.basicSystemType IS NULL OR u.basicSystemType = '') "
			+ " OR ( u.basicSystemType = 'Roof Mounted' AND :p80 = true ) "
			+ " OR ( u.basicSystemType = 'Ground Mounted' AND :p81 = true) "
			+ " OR ( u.basicSystemType = 'Patio Cover' AND :p85 = true) "
			+ " OR ( u.basicSystemType = 'Carport' AND :p83 = true) "
			+ " OR (u.basicSystemType = 'Other' AND (upper(u.basicSystemTypeOther) = :p84 OR u.basicSystemTypeOther IS NULL OR u.basicSystemTypeOther = '') ) )"
			+ " AND (u.inverterTechnology =:p9 OR u.inverterTechnology IS NULL OR u.inverterTechnology = '')"
			+ " AND (u.batteryInSystem.id =:p10 OR u.batteryInSystem IS NULL)"
			+ " AND ( (u.roofType =:p12 OR u.roofType =:p13 OR u.roofType IS NULL OR u.roofType = '')"
			+ " OR (u.roofType = 'Other' AND (u.roofTypeOther =:p19 OR u.roofTypeOther IS NULL OR u.roofTypeOther = '') ) )"
			+ " AND (u.roofingMaterialType.id =:p14 OR u.roofingMaterialType IS NULL)"
			+ " AND (u.railRackingModel.id =:p15 OR u.railRackingModel.id =:p16 OR u.railRackingModel IS NULL)"
			+ " AND (u.electEngStructEng =:p18 OR u.electEngStructEng IS NULL OR u.electEngStructEng = '')"
			+ " AND (u.necCode =:p5 OR u.necCode IS NULL OR u.necCode = '')"
			+ " AND (u.ifcCode =:p7 OR u.ifcCode IS NULL OR u.ifcCode = '') ) ";
	
	public static final String FILTER_BY_STATE_AHJ_USER = " ( (u.states is not NULL OR u.ahj is not NULL) "
			+ " AND ( u.utilityCompany IS NULL)"
			+ " AND (not exists (select 1 from UserCustomizeSheets v where v.sheetId = u.id))"
			+ " AND (u.basicSystemType IS NULL OR u.basicSystemType = '') "
			+ " AND (u.inverterTechnology IS NULL OR u.inverterTechnology = '')"
			+ " AND (u.batteryInSystem IS NULL)"
			+ " AND (u.roofType IS NULL OR u.roofType = '')"
			+ " AND (u.roofingMaterialType IS NULL)"
			+ " AND (u.railRackingModel IS NULL)"
			+ " AND (u.electEngStructEng IS NULL OR u.electEngStructEng = '')"
			+ " AND (u.necCode IS NULL OR u.necCode = '')"
			+ " AND (u.ifcCode IS NULL OR u.ifcCode = '') ) ";
	

	@Query(value = "SELECT u from PlansetCustomizeSheets u where (:name = '' OR (u.pdfName is not NULL AND upper(u.pdfName) LIKE CONCAT('%',:name,'%')))"
			+ " AND (:utility = '' OR (u.utilityCompany is not NULL AND upper(u.utilityCompany) LIKE CONCAT('%',:utility,'%'))) "
			+ " AND (:ahj = '' OR u.ahj is not NULL) AND u.isDeleted =:deleted")
	List<PlansetCustomizeSheets> searchCustomizeSheet(@Param("name") String pdfName, @Param("utility") String utilityCompany,
			@Param("ahj") String ahj, @Param("deleted") Boolean isDeleted);
	
	@Query(value = SELECT + NOT_IN + "(" + FILTER_BY_PROJECT + "OR" + FILTER_BY_STATE_AHJ_USER + ")")
	List<PlansetCustomizeSheets> findCustomizeSheetByProjectNotIn(@Param("p1") List<String> listPDFwithRev,
			@Param("p4") String utilityCompany, @Param("p6") Long userId, @Param("p80") Boolean roofMounted,
			@Param("p81") Boolean groundMounted, @Param("p85") Boolean patioMounted,
			@Param("p83") Boolean carportMounted, @Param("p84") String textOther,
			@Param("p9") String deviceToIncorporate, @Param("p10") Long batteryID, @Param("p12") String roofRafter,
			@Param("p13") String roofRafterOther, @Param("p14") Long roofMaterialTypeID,
			@Param("p15") Long railRackingRoofID, @Param("p16") Long railRackingGroundID,
			@Param("p18") String engineersApplicablEngineering, @Param("p19") String secroofRafterOther,
			@Param("p5") String necCode, @Param("p7") String ifcCode);


	@Query(value = SELECT + IN + "(" + FILTER_BY_PROJECT + "OR" + FILTER_BY_STATE_AHJ_USER + ")")
	List<PlansetCustomizeSheets> findCustomizeSheetByProjectIn(@Param("p1") List<String> listPDFwithRev,
			@Param("p4") String utilityCompany, @Param("p6") Long userId, @Param("p80") Boolean roofMounted,
			@Param("p81") Boolean groundMounted, @Param("p85") Boolean patioMounted,
			@Param("p83") Boolean carportMounted, @Param("p84") String textOther,
			@Param("p9") String deviceToIncorporate, @Param("p10") Long batteryID, @Param("p12") String roofRafter,
			@Param("p13") String roofRafterOther, @Param("p14") Long roofMaterialTypeID,
			@Param("p15") Long railRackingRoofID, @Param("p16") Long railRackingGroundID,
			@Param("p18") String engineersApplicablEngineering, @Param("p19") String secroofRafterOther,
			@Param("p5") String necCode, @Param("p7") String ifcCode);
}
