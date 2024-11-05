package com.PlayGroundAdv.Solar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PermitCustomizedSheetsEntity")
public class PermitCustomizedSheetsEntity {

	/**
	 *  CLASS ENTITY
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="PermitCustomized_sequence",
			           sequenceName="PermitCustomized_sequence",
			           allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PermitCustomized_sequence")  
	private Long id;
	
	@JoinColumn(name="PROJECT")
	@ManyToOne
	private PermitEntity project;
	
	@JoinColumn(name = "SHEET")
	@ManyToOne
	private PlansetCustomizeSheets sheet;
	
	@Column(name="MASTER_SHEET")
	private Boolean masterSheet;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PermitEntity getProject() {
		return project;
	}

	public void setProject(PermitEntity project) {
		this.project = project;
	}


	public PlansetCustomizeSheets getSheet() {
		return sheet;
	}

	public void setSheet(PlansetCustomizeSheets sheet) {
		this.sheet = sheet;
	}

	public Boolean getMasterSheet() {
		return masterSheet;
	}

	public void setMasterSheet(Boolean masterSheet) {
		this.masterSheet = masterSheet;
	}
	
	

}
