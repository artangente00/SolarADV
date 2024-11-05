package com.PlayGroundAdv.Solar.model;

import java.util.List;

import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.model.project.CustomUpload;
import com.PlayGroundAdv.Solar.model.project.ess.ESSConnectorsModel;
import com.PlayGroundAdv.Solar.model.project.ess.ESSNodesModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPermitByIdResult {

	private PermitAdditionalInfoEntityResult permitAdditionalInfoEntity;
	private PermitArraysEntityResult PermitArraysEntityResult;
	private PermitCompanyInfoEntityResultPrime permitCompanyInfoEntityResult;
	private PermitHomeSiteEntityResult permitHomeSiteEntityResult;
	private PermitProjectSiteInfoEntityTwo permitProjectSiteInfoEntityTwo;
	private PermtiWeatherEntityResult permtiWeatherEntityResult;
	private PermitEntity permitEntity;
	private PermitArrayEntityResultSecond permitArraysEntity;
	private PermitAdvEntityResult permitAdvEntityResult;
	private PermitEngineerEntityResult permitEngineerEntityResult;
	private PermitConduitConductorSectionEntitieResult permitConduitConductorSection;
	private PermitDrafterDataResult permitDrafterData;
	private PermitLayoutSketchResult layoutSketch;
	private PermitEditStatusModel permitEditStatusModel;
	private SystemAttributesModel systemAttributes;
	private List<String> projectDrafts;
	private UserSettingEntity userSettingEntity;
	private NotAllowedRackingNotesModel notAllowedRackingNotes;
	private OCPDMainPanelModel ocpdMainPanel;
	private PermitEnergyBatterySystemDto PermitEnergyBatterySystem;
	private ProjectNotesDto projectNotes;
	private List<PermitSketchResults> arrayLayout;
	private List<CustomUpload> customUpload;
	private List<ESSNodesModel> essNodesModel;
	private List<ESSConnectorsModel> essConnectorsModel;
	public GetPermitByIdResult() {
		super();
	}

	public GetPermitByIdResult(PermitAdditionalInfoEntityResult permitAdditionalInfoEntity,
			com.PlayGroundAdv.Solar.model.PermitArraysEntityResult permitArraysEntityResult,
			PermitCompanyInfoEntityResultPrime permitCompanyInfoEntityResult,
			PermitHomeSiteEntityResult permitHomeSiteEntityResult,
			PermitProjectSiteInfoEntityTwo permitProjectSiteInfoEntityTwo,
			PermtiWeatherEntityResult permtiWeatherEntityResult, PermitEntity permitEntity,
			PermitArrayEntityResultSecond permitArraysEntity, PermitAdvEntityResult permitAdvEntityResult,
			PermitEngineerEntityResult permitEngineerEntityResult,
			PermitConduitConductorSectionEntitieResult permitConduitConductorSection,
			PermitDrafterDataResult permitDrafterData, PermitLayoutSketchResult layoutSketch,
			PermitEditStatusModel permitEditStatusModel, List<String> projectDrafts,
			UserSettingEntity userSettingEntity, NotAllowedRackingNotesModel notAllowedRackingNotes) {

		super();
		this.permitAdditionalInfoEntity = permitAdditionalInfoEntity;
		this.PermitArraysEntityResult = permitArraysEntityResult;
		this.permitCompanyInfoEntityResult = permitCompanyInfoEntityResult;
		this.permitHomeSiteEntityResult = permitHomeSiteEntityResult;
		this.permitProjectSiteInfoEntityTwo = permitProjectSiteInfoEntityTwo;
		this.permtiWeatherEntityResult = permtiWeatherEntityResult;
		this.permitEntity = permitEntity;
		this.permitArraysEntity = permitArraysEntity;
		this.permitAdvEntityResult = permitAdvEntityResult;
		this.permitEngineerEntityResult = permitEngineerEntityResult;
		this.permitConduitConductorSection = permitConduitConductorSection;
		this.setPermitDrafterData(permitDrafterData);
		this.setLayoutSketch(layoutSketch);
		this.setPermitEditStatusModel(permitEditStatusModel);
		this.projectDrafts = projectDrafts;
		this.userSettingEntity = userSettingEntity;
		this.notAllowedRackingNotes = notAllowedRackingNotes;
	}



}
