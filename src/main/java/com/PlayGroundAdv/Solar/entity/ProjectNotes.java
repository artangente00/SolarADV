package com.PlayGroundAdv.Solar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
public class ProjectNotes {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "PROJECT")
	@ManyToOne
	private PermitEntity project;
	
	@Column
	private String floridaRafterDesign;
	
	@Column
	private String floridaRafterDesignFileName;

	public ProjectNotes(PermitEntity project, String floridaRafterDesign, String floridaRafterDesignFileName) {
		super();
		this.project = project;
		this.floridaRafterDesign = floridaRafterDesign;
		this.floridaRafterDesignFileName = floridaRafterDesignFileName;
	}
	
	
	
}
