package com.PlayGroundAdv.Solar.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Entity implementation class for Entity: PermitSketchEntity
 *
 */
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Permit_Sketch_Entity")
public class PermitSketchEntity implements Serializable {

	
private static final long serialVersionUID = 1L;
	

	@Id
	@SequenceGenerator(name="hibernate_sequence17", sequenceName = "hibernate_sequence17", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence17")  
	private Long id;

	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity permitEntity;
	
	
	@Column(name="ARRAY_SKETCH")
	private Integer arraySketch;
	
	@Column(name="AZIMUTH")
	private String azimuth;
	
	@Column(name="ROOF_PITCH")
	private String roofPitch;
	
	@Column(name="MODULE_TILS")
	private Boolean moduleTils;
	
	@Column(name="EAVE_OVER_HANG")
	private String eaveOverHang;
	
	@Column(name="EAVE_OVER_HANG_OTHER")
	private String eaveOverHangOther;
	
	@Column(name="MODEL_VALUE")
	private String modelvalue;

	@Column(name="MODULE_QTY")
	private String moduleQty;

	@Column
	private Integer squareFootage;
	
}
