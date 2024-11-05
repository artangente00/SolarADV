package com.PlayGroundAdv.Solar.entity.projects.ess;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
public class ESSCircuitSpec {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "connector")
	@OneToOne(mappedBy = "circuitSpec")
	private ESSConnectors connector;
	
	@Column
	private String conductorQty;

	@Column
	private String conductorType;

	@Column
	private String conductorSize;

	@Column
	private String conduitType;

	@Column
	private String conduitSize;

	@Column
	private Integer conductorQtyOther;

	@Column
	private String conductorTypeOther;

	@Column
	private String conductorSizeOther;

	@Column
	private String conduitTypeOther;

	@Column
	private String conduitSizeOther;

	@Column
	private Boolean existing;

	@Column
	private Boolean conductorNeutral;

	@Column
	private String circuitEnvironment;

	@Column
	private Float circuitLength;

	public ESSCircuitSpec(ESSConnectors connector) {
		super();
		this.connector = connector;
	}
	
	
}
