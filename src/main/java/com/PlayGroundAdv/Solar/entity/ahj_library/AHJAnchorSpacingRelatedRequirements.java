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
@Table(name = "AHJ_ANCHOR_SPACING_RELATED_REQUIREMENTS")
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class AHJAnchorSpacingRelatedRequirements {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "AHJ")
	@ManyToOne
	private AHJChecklistEntity ahj;

	private String wetOrRaisedStampReqOnAllEngineering;
	private String digitalAuthenticatedStampRequired;
	private String allSheetsMustBeSeparatedIntoIndividualFiles;
	private String specialRedNoteOnEachDigitallyStampedSheet;
	private String structuralEngReqAllRoofMounts;
	private String structuralEngLetterMustIncludeFullCalculations;
	private String resaCalcsReqOnMfgTrusses;
	private String structuralEngReqAllRoofMountsWithTileRoof;
	private String structuralEngReqIfAllSystemComponentsExceed;
	private String structuralEngReqIfModuleToRoofPlane;
	private String roofReportReqToIncludeInStructuralEng;
	private String structuralEngReqIfAnchSpacingExceeds;
	private String structuralEngReqIfRackingSpanTables;
	private String structuralEngReqIfAgeOfResOlderThan19xx;
	
	@Type(type = "list-array")
    @Column(name = "NOTES",columnDefinition = "text[]")
    private List<String> notes;
	
	public AHJAnchorSpacingRelatedRequirements(AHJChecklistEntity ahj) {
		super();
		this.ahj = ahj;
	}
	
}
