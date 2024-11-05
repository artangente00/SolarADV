package com.PlayGroundAdv.Solar.model.ahj_library;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AHJAddRemoveFromPlansetModel {
	private String parcelNumberReq;
	private String labelUtilityFeederAsOverheadOrUnderground;
	private String identifyUtilityTransformerLocation;
	private String reqExistingPVSystemsIdentified ;
	private String electricalEngineerStampReq;
	private String removeNonApplicableCautionsPlacards;
	private String reqInstallationManuals;
	private String reqSpecificJurisdictionalConstNotes;
	private String reqSpecialDocs ;
	private String reqOtherSpecialDoc;
	private String showPLDimensions;
	private String showDistanceFromPLRoofMountStructure;
	private String showDistanceFromPLToAllExistingOrProposedStructuresonParcel;
	private String showDistanceBetweenAllStructuresOnProperty	;
	private String showBuildingArea ;
	private String onGroundMountsRemoveRoofMountFireCodes;
	private String mustShowWellLocation;
	private String scaleIfNotStdArch;
	private String moduleRackingCutSheetsMUSTShowFireRatingType1Or2;
	private String localOrdinanceRestrictingModulePlacementDirectlyFacingStreetFrontage;
	private String septicLocationOnSiteMapForGroundMountsIfSepticSysIsApplicable;
	private String propaneTankLocationOnSiteMapForGroundMountsIfPropaneTankIsApplicable;
	private String existingStructuresDrawnIdentifiedonGroundMountsSiteMaps;
	private String specialNoteOrContentOnPV001;
	private String specialNoteOrContentOnPV100;
	private String specialNoteOrContentOnPV100G;
	private String specialNoteOrContentOnPV101G;
	private String specialNoteOrContentOnS100;
	private String specialNoteOrContentOnS200;
	private String specialNoteOrContentOnS201;
	private String specialNoteOrContentOnS300;
	private String specialNoteOrContentOnE002;
	private String specialNoteOrContentOnE003;
	private String specialNoteOrContentOnE100;
	private List<String> notes;
	
	public List<ResultUpdateLog> compareFields(AHJAddRemoveFromPlansetModel object) throws IllegalAccessException{
	    List<ResultUpdateLog> resultList = new ArrayList<>();      
	    Field[] fields = object.getClass().getDeclaredFields();
	    for(Field field : fields){
	        try {
	        	if(!field.getName().equals("notes") && field.get(this) != null && !field.get(this).equals(field.get(object))){
		            ResultUpdateLog resultObject = new ResultUpdateLog();
		            resultObject.setFieldName(field.getName());
		            resultObject.setNewValue(field.get(this).toString());
		            resultObject.setOldValue( field.get(object) != null ? field.get(object).toString() : null);
		            resultObject.setCategory("Add to Planset/Remove from Planset & Drafting Req.");
		            resultList.add(resultObject);
		        }else if(!field.getName().equals("notes") && field.get(this) == null && field.get(object) != null){
		        	ResultUpdateLog resultObject = new ResultUpdateLog();
		            resultObject.setFieldName(field.getName());
		            resultObject.setNewValue(null);
		            resultObject.setOldValue(field.get(object).toString());
		            resultObject.setCategory("Add to Planset/Remove from Planset & Drafting Req.");
		            resultList.add(resultObject);
		        }else if(field.getName().equals("notes")) {
		        	String previousNotes = field.get(object) != null ? field.get(object).toString().replace("[", "").replace("]", "") : "";
		        	String newNotes = field.get(this) != null ? field.get(this).toString().replace("[", "").replace("]", "") : "";
		        	if (!previousNotes.equals(newNotes)) {
		        		ResultUpdateLog resultObject = new ResultUpdateLog();
			            resultObject.setFieldName("Special Notes");
			            resultObject.setNewValue(newNotes);
			            resultObject.setOldValue(previousNotes);
			            resultObject.setCategory("Add to Planset/Remove from Planset & Drafting Req.");
			            resultList.add(resultObject);
					}
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    return resultList;
	}
}
