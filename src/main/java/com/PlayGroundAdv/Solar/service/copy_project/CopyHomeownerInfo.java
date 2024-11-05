package com.PlayGroundAdv.Solar.service.copy_project;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.repositories.PermitHomeSiteInfoRepository;

@Service
@Transactional
public class CopyHomeownerInfo {

	final PermitHomeSiteInfoRepository homeSiteInfoRepo;

	public CopyHomeownerInfo(PermitHomeSiteInfoRepository homeSiteInfoRepo) {
		super();
		this.homeSiteInfoRepo = homeSiteInfoRepo;
	}

	public void copyHomeownerInfo(Long idPermit, Long idNewPermit, Boolean isTemplate) {
		try {
			PermitHomeSiteInfoEntity homeSiteInfoEntity = homeSiteInfoRepo.findByPermitEntityId(idPermit);
			PermitHomeSiteInfoEntity newHomeSiteInfoEntity = homeSiteInfoRepo.findByPermitEntityId(idNewPermit);
			newHomeSiteInfoEntity = clonePermitHomeSiteInfoEntityNotAll(newHomeSiteInfoEntity, homeSiteInfoEntity);
			if (Boolean.TRUE.equals(isTemplate))
				newHomeSiteInfoEntity = clonePermitHomeSiteInfoEntity(newHomeSiteInfoEntity, homeSiteInfoEntity);
			homeSiteInfoRepo.save(newHomeSiteInfoEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PermitHomeSiteInfoEntity clonePermitHomeSiteInfoEntityNotAll(
			PermitHomeSiteInfoEntity newPermitHomeSiteInfoEntity, PermitHomeSiteInfoEntity permitHomeSiteInfoEntity) {

		if (newPermitHomeSiteInfoEntity != null && permitHomeSiteInfoEntity != null) {

			newPermitHomeSiteInfoEntity.setHomePhone(permitHomeSiteInfoEntity.getHomePhone());
			newPermitHomeSiteInfoEntity.setCellPhone(permitHomeSiteInfoEntity.getCellPhone());
			newPermitHomeSiteInfoEntity.setOtherPhone(permitHomeSiteInfoEntity.getOtherPhone());
			newPermitHomeSiteInfoEntity.setEmailPhone(permitHomeSiteInfoEntity.getEmailPhone());
			newPermitHomeSiteInfoEntity.setPropertyAPN(permitHomeSiteInfoEntity.getPropertyAPN());
			newPermitHomeSiteInfoEntity.setFootageStructure(permitHomeSiteInfoEntity.getFootageStructure());
			newPermitHomeSiteInfoEntity
					.setResidenceBindingCategory(permitHomeSiteInfoEntity.getResidenceBindingCategory());
			newPermitHomeSiteInfoEntity.setConstructionType(permitHomeSiteInfoEntity.getConstructionType());
			newPermitHomeSiteInfoEntity.setRoofRafter(permitHomeSiteInfoEntity.getRoofRafter());
			newPermitHomeSiteInfoEntity.setRoofRafterOther(permitHomeSiteInfoEntity.getRoofRafterOther());
			newPermitHomeSiteInfoEntity.setSecroofRafterOther(permitHomeSiteInfoEntity.getSecroofRafterOther());
			newPermitHomeSiteInfoEntity.setBuildingRisk(permitHomeSiteInfoEntity.getBuildingRisk());
			newPermitHomeSiteInfoEntity.setBuildingOccupancy(permitHomeSiteInfoEntity.getBuildingOccupancy());
			newPermitHomeSiteInfoEntity.setTextOtherConst(permitHomeSiteInfoEntity.getTextOtherConst());
			newPermitHomeSiteInfoEntity.setTextOtherBuildOccup(permitHomeSiteInfoEntity.getTextOtherBuildOccup());
			newPermitHomeSiteInfoEntity.setTextOtherBuild(permitHomeSiteInfoEntity.getTextOtherBuild());

			newPermitHomeSiteInfoEntity.setServiceVoltage(permitHomeSiteInfoEntity.getServiceVoltage());
			newPermitHomeSiteInfoEntity.setServiceVoltageOther(permitHomeSiteInfoEntity.getServiceVoltageOther());
			newPermitHomeSiteInfoEntity.setIfServiceVoltage(permitHomeSiteInfoEntity.getIfServiceVoltage());
			newPermitHomeSiteInfoEntity.setRidgeBeamDepthAtArrays(permitHomeSiteInfoEntity.getRidgeBeamDepthAtArrays());
			newPermitHomeSiteInfoEntity
					.setMaxHorizontalSpanAtArrays(permitHomeSiteInfoEntity.getMaxHorizontalSpanAtArrays());
			newPermitHomeSiteInfoEntity
					.setMaxHorizontalSpanAtArraysInches(permitHomeSiteInfoEntity.getMaxHorizontalSpanAtArraysInches());
			newPermitHomeSiteInfoEntity
					.setMaxHorizontalSpanAtArraysHS(permitHomeSiteInfoEntity.getMaxHorizontalSpanAtArraysHS());
			newPermitHomeSiteInfoEntity.setMaxHorizontalSpanAtArraysHSInches(
					permitHomeSiteInfoEntity.getMaxHorizontalSpanAtArraysHSInches());
			newPermitHomeSiteInfoEntity.setBuildingRiskOther(permitHomeSiteInfoEntity.getBuildingRiskOther());
			newPermitHomeSiteInfoEntity.setTextOtherExpo(permitHomeSiteInfoEntity.getTextOtherExpo());
			newPermitHomeSiteInfoEntity.setStanchionMaxSpacing(permitHomeSiteInfoEntity.getStanchionMaxSpacing());
			newPermitHomeSiteInfoEntity
					.setStanchionMaxSpacingOther(permitHomeSiteInfoEntity.getStanchionMaxSpacingOther());
			newPermitHomeSiteInfoEntity
					.setRidgeBeamDepthAtArraysOther(permitHomeSiteInfoEntity.getRidgeBeamDepthAtArraysOther());
			newPermitHomeSiteInfoEntity.setProjectJurisdiction(permitHomeSiteInfoEntity.getProjectJurisdiction());
			newPermitHomeSiteInfoEntity.setProjectJurisOther(permitHomeSiteInfoEntity.getProjectJurisOther());
		}
		return newPermitHomeSiteInfoEntity;
	}

	public PermitHomeSiteInfoEntity clonePermitHomeSiteInfoEntity(PermitHomeSiteInfoEntity newPermitHomeSiteInfoEntity,
			PermitHomeSiteInfoEntity permitHomeSiteInfoEntity) {
		if (newPermitHomeSiteInfoEntity != null && permitHomeSiteInfoEntity != null) {
			newPermitHomeSiteInfoEntity.setMeterNumber(permitHomeSiteInfoEntity.getMeterNumber());
			newPermitHomeSiteInfoEntity.setEsiidNumber(permitHomeSiteInfoEntity.getEsiidNumber());
			newPermitHomeSiteInfoEntity.setUtilityCompanyName(permitHomeSiteInfoEntity.getUtilityCompanyName());
			newPermitHomeSiteInfoEntity.setSiteAddress(permitHomeSiteInfoEntity.getSiteAddress());
			newPermitHomeSiteInfoEntity.setAddressLine2(permitHomeSiteInfoEntity.getAddressLine2());
			newPermitHomeSiteInfoEntity.setCity(permitHomeSiteInfoEntity.getCity());
			newPermitHomeSiteInfoEntity.setState(permitHomeSiteInfoEntity.getState());
			newPermitHomeSiteInfoEntity.setPostalCode(permitHomeSiteInfoEntity.getPostalCode());
			newPermitHomeSiteInfoEntity.setSecondaryAddress(permitHomeSiteInfoEntity.getSecondaryAddress());
			newPermitHomeSiteInfoEntity.setSecondaryAddressLine2(permitHomeSiteInfoEntity.getSecondaryAddressLine2());
			newPermitHomeSiteInfoEntity.setSecondaryCity(permitHomeSiteInfoEntity.getSecondaryCity());
			newPermitHomeSiteInfoEntity.setSecondaryState(permitHomeSiteInfoEntity.getSecondaryState());
			newPermitHomeSiteInfoEntity.setSecondaryPostalCode(permitHomeSiteInfoEntity.getSecondaryPostalCode());
			newPermitHomeSiteInfoEntity.setSameMailing(permitHomeSiteInfoEntity.getSameMailing());
			newPermitHomeSiteInfoEntity.setCityOther(permitHomeSiteInfoEntity.getCityOther());
			newPermitHomeSiteInfoEntity.setProjectJurisdiction(permitHomeSiteInfoEntity.getProjectJurisdiction());
			newPermitHomeSiteInfoEntity.setProjectJurisOther(permitHomeSiteInfoEntity.getProjectJurisOther());
			newPermitHomeSiteInfoEntity.setSecondaryCityOther(permitHomeSiteInfoEntity.getSecondaryCityOther());
		}
		return newPermitHomeSiteInfoEntity;
	}
}
