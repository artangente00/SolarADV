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
public class AHJElectricalModel {
	private String groundWireMinOnRoof;
	private String otherGroundWireOrGround;
	private String acDiscoReqToBeXxFt;
	private String reqNoteOnTldForTypeOfAcDisco;
	private String onOffLabelsRequirementAc;
	private String commercialMustShowNoteOnPV001;
	private String weebsAllowed;
	private String otherWEEBRule;
	private String carbonMonoxideNote;
	private String centerFed;
	private String acceptsNewNfpa;
	private String requiredNoteIndicating;
	private String requireMinAcWireSize;
	private String requireMinGroundWireSizePv;
	private String acceptsOurLineDia;
	private String reqVDropCalcs;
	private String reqVDropForWireLengthLonger;
	private String reqSeparateAppForMspUpgrade;
	private String reqElecEngStampWhenSizeIs;
	private String reqElectEngStampForGroundMountSystems;
	private String reqElectEngStampIfNotWetSigend;
	private String ahjWillNotAllowAPECivilEng;
	private String reqAcKwOnPlanSetMethod;
	private String ahjWillAllowMoreThan1BackFeedBreaker;
	private String lineOrServSideTaps;
	private String allTapsMustBeInAnElect;
	private String allTapsMustShowRigidMetalConduit;
	private String allTapsMustShowWarningLabelOnMspStating;
	private String electNoteSpecificsRequiredByAhj;
	private String pocNoteOnTldIndicatingTypeOfPoc;
	private String pocNoteOrStickerLabeLSpecific;
	private String modificationForGroundRodLocationsIndependent;
	private String noGroundPathBetweenMsp;
	private String ahjWillNotAllowNecOnAnySheets;
	private String allElectPvEquipMustBeUlListed;
	private List<String> notes;
	
	public List<ResultUpdateLog> compareFields(AHJElectricalModel object) throws IllegalAccessException{
	    List<ResultUpdateLog> resultList = new ArrayList<>();      
	    Field[] fields = object.getClass().getDeclaredFields();
	    for(Field field : fields){
	        try {
	        	if(!field.getName().equals("notes") && field.get(this) != null && !field.get(this).equals(field.get(object))){
		            ResultUpdateLog resultObject = new ResultUpdateLog();
		            resultObject.setFieldName(field.getName());
		            resultObject.setNewValue(field.get(this).toString());
		            resultObject.setOldValue( field.get(object) != null ? field.get(object).toString() : null);
		            resultObject.setCategory("Electrical");
		            resultList.add(resultObject);
		        }else if(!field.getName().equals("notes") && field.get(this) == null && field.get(object) != null){
		        	ResultUpdateLog resultObject = new ResultUpdateLog();
		            resultObject.setFieldName(field.getName());
		            resultObject.setNewValue(null);
		            resultObject.setOldValue(field.get(object).toString());
		            resultObject.setCategory("Electrical");
		            resultList.add(resultObject);
		        }else if(field.getName().equals("notes")) {
		        	String previousNotes = field.get(object) != null ? field.get(object).toString().replace("[", "").replace("]", "") : "";
		        	String newNotes = field.get(this) != null ? field.get(this).toString().replace("[", "").replace("]", "") : "";
		        	if (!previousNotes.equals(newNotes)) {
		        		ResultUpdateLog resultObject = new ResultUpdateLog();
			            resultObject.setFieldName("Special Notes");
			            resultObject.setNewValue(newNotes);
			            resultObject.setOldValue(previousNotes);
			            resultObject.setCategory("Electrical");
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
