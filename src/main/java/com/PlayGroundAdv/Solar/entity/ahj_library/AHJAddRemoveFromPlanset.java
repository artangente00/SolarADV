package com.PlayGroundAdv.Solar.entity.ahj_library;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.array.ListArrayType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "AHJ_ADD_REMOVE_FROM_PLANSET")
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class AHJAddRemoveFromPlanset {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "AHJ")
	@ManyToOne
	private AHJChecklistEntity ahj;
	
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
	private String showDistanceFromPLToStructuresonParcel;
	private String showDistanceBetweenAllStructuresOnProperty	;
	private String showBuildingArea ;
	private String onGroundMountsRemoveRoofMountFireCodes;
	private String mustShowWellLocation;
	private String scaleIfNotStdArch;
	private String moduleRackingCutSheetsMUSTShowFireRatingType1Or2;
	private String restricModuleFacingStreet;
	private String septicLocationGroundMounts;
	private String propaneTankLocationIfApplicable;
	private String existingStructuresDrawn;
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
	
	@Type(type = "list-array")
    @Column(name = "NOTES",columnDefinition = "text[]")
    private List<String> notes;
	
	public AHJAddRemoveFromPlanset(AHJChecklistEntity ahj) {
		super();
		this.ahj = ahj;
	}
	
}
