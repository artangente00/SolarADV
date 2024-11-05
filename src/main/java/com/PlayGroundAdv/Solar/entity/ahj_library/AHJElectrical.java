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
@Table(name = "AHJ_ELECTRICAL")
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class AHJElectrical {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "AHJ")
	@ManyToOne
	private AHJChecklistEntity ahj;

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
	
	@Type(type = "list-array")
    @Column(name = "NOTES",columnDefinition = "text[]")
    private List<String> notes;
	
	public AHJElectrical(AHJChecklistEntity ahj) {
		super();
		this.ahj = ahj;
	}
	
}
