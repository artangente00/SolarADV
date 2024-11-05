package com.PlayGroundAdv.Solar.model.ahj_library;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AHJDataModel {
	
	public Long id;
	public String ahj;
	public String county;
	public String state;
	public Boolean incorporated;
	public GoverningCodesModel governingCodes;
	public AHJACDiscoModel acdisco;
	public AHJAddRemoveFromPlansetModel addRemoveFromPlanset;
	public AHJAnchorSpacingRelatedRequirementsModel anchorSpacingRelatedRequirements;
	public AHJDesignCriteriaModel designCriteria;
	public AHJElectricalModel electrical;
	public AHJFireSetbacksRoofMountedModel fireSetbacksRoofMounted;
	public AHJGroundMountRelatedRequirementsModel groundMountRelatedRequirements;
	public AHJGroundMountSetbacksModel groundMountSetbacks;
	public AHJPVMeterModel pvMeter;
	public AHJReqCustomPlaSetSheetorSizeModel reqCustomPlaSetSheetorSize;
	public AHJRoofMountRelatedRequirementOnlyModel roofMountRelatedRequirementOnly;
	public AHJRoofVentsModel roofVents;
	public AHJWaterIntrusionModel waterIntrusion;
	public AHJLastKnownPlanSetApproved lastKnownPlanSetApproved;
	public AHJHowManyModel howMany;
	public AHJPreApprovalReqModel preApprovalReq;
	public AHJRevisionOrResubmittalModel revisionOrResubmittal;
	public AHJContactsModel contacts;
	public AHJUsedByClientsModel usedByClients;
	public Long madeBy;
	public Date lastUpdate;
	public String updatedBy;
	public List<AHJCustomFieldsModel> customFields;
	
	
}
