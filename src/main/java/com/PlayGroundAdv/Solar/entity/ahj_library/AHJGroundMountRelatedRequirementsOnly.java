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
@Table(name = "AHJ_GROUND_MOUNT_RELATED_REQUIREMENTS_ONLY")
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class AHJGroundMountRelatedRequirementsOnly {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "AHJ")
	@ManyToOne
	private AHJChecklistEntity ahj;

	private String structuralEngReqAnySolarPatioCoverOrCarportType;
	private String structuralEngReqOnAnyGroundMount;
	private String structuralEngReqIfSystemSizeGreaterThanxxkW;
	private String mustShowStanchionsAndNotesIndicating;
	private String commercialStructuralEngReqIfCommercial;
	private String commercialMustShowEXISTINGOnAllRoof;
	private String commercialMustShowLiveLoad;
	private String commercialVerifyGravityLoadsDoNotIncrease;
	private String commercialProvideStructCalcsForStanchion;
	private String structuralCriteriaSheetReq;
	private String roofSheetingSizeThickAndTypeReq;
	private String roofZoneIndicatedOnS100;
	private String specificStrucNotesOrCriteria;
	
	@Type(type = "list-array")
    @Column(name = "NOTES",columnDefinition = "text[]")
    private List<String> notes;
	
	public AHJGroundMountRelatedRequirementsOnly(AHJChecklistEntity ahj) {
		super();
		this.ahj = ahj;
	}
	
	
}
