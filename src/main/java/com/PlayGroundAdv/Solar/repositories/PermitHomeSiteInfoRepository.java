package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;

import org.springframework.stereotype.Repository;

@Repository
public interface PermitHomeSiteInfoRepository extends JpaRepository<PermitHomeSiteInfoEntity, Long> {

	PermitHomeSiteInfoEntity findByPermitEntityId(Long idProject);

	PermitHomeSiteInfoEntity findOneByPermitEntityId(Long idProject);

	List<PermitHomeSiteInfoEntity> findByUtilityCompanyNameAndPermitEntityIsDeleted(String utilityCompany,
			boolean isDeleted);

	public static final String FIND_PROJECT_HOME_SITE_INFO = "SELECT new com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult  "
			+ " ( u.utilityCompanyName, u.formattedAddress,  u.siteAddress, u.addressLine2, u.city, "
			+ " u.state, u.latitude, u.longitude, u.postalCode, u.secondaryAddress, "
			+ " u.secondaryAddressLine2, u.secondaryCity, u.secondaryState, u.secondaryPostalCode, "
			+ " u.homePhone, u.cellPhone, u.otherPhone, u.emailPhone,  u.propertyAPN,  "
			+ " u.footageStructure, u.residenceBindingCategory, u.constructionType,  "
			+ " u.roofRafter, u.buildingRisk, u.buildingOccupancy, u.permitEntity.homeOwnName, "
			+ " u.textOtherConst, u.textOtherExpo, u.textOtherBuildOccup, u.textOtherBuild, "
			+ " u.sameMailing, u.serviceVoltage, u.serviceVoltageOther, u.ifServiceVoltage, "
			+ " u.ridgeBeamDepthAtArrays, u.maxHorizontalSpanAtArrays, u.maxHorizontalSpanAtArraysHS, "
			+ " u.maxHorizontalSpanAtArraysInches, u.maxHorizontalSpanAtArraysHSInches, "
			+ " u.buildingRiskOther, u.stanchionMaxSpacing, u.stanchionMaxSpacingOther, "
			+ " u.ridgeBeamDepthAtArraysOther, u.UtilityCompanyNameOther, u.cityOther, "
			+ " u.projectJurisdiction, u.projectJurisOther, u.secondaryCityOther, "
			+ " u.secroofRafterOther, u.roofRafterOther, u.meterNumber, u.esiidNumber ) "
			+ " from PermitHomeSiteInfoEntity u where u.permitEntity.id = :p1 ";

	@Query(value = FIND_PROJECT_HOME_SITE_INFO)
	PermitHomeSiteEntityResult getProjectHomeSiteInfo(@Param("p1") Long idProject);

	@Query(value = "SELECT u.siteAddress from PermitHomeSiteInfoEntity u WHERE u.permitEntity.id = :p1")
	String finddSiteAddress(@Param("p1") Long idProject);

	@Query("SELECT new com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel  "
			+ " ( u.homeOwnLastName, u.homeOwnName, u.projectName, u.status, v.firstName, v.lastName)"
			+ " from PermitEntity u, AuthentificationEntity v, PermitHomeSiteInfoEntity w"
			+ " where  w.utilityCompanyName = :p1 and  u.isDeleted  = false and u.status != 'Delivered'  and u.status != 'Submitted' and u.isTemplate  = false and u.id = w.permitEntity.id and u.authentificationEntity.id = v.id")
	List<ProjectForLibrariesModel> getPermitUtilityCompany(@Param("p1") String utilityCompany);

	Long deleteByPermitEntityId(Long id);

}
