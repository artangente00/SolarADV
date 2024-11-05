package com.PlayGroundAdv.Solar.entity.ahj_library;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class AHJUsedByClients {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "AHJ")
	@ManyToOne
	private AHJChecklistEntity ahj;
	
	private String standardFee;
	private String roofMountOtc;
	private String groundMountOtc;
	private String otcNotes;
	private String planCkTimeFrame;	
	private String allowsExpediting;
	private String expFee;	
	private String expPlanCheckTime;	
	private String expVsNormalSubRequOutcomeForBoth;	
	private String expVsStandardSubmittalComments;	
	private String inspectionRequirements;	
	private String inspectionNotes;
	
	@Type(type = "list-array")
    @Column(name = "NOTES",columnDefinition = "text[]")
    private List<String> notes;
	
	public AHJUsedByClients(AHJChecklistEntity ahj) {
		super();
		this.ahj = ahj;
	}

}
