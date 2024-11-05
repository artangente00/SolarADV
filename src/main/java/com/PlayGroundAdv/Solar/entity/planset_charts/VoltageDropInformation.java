package com.PlayGroundAdv.Solar.entity.planset_charts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class VoltageDropInformation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String wireSize;
	
	@Column
	private float dcResistance;
	
	@Column
	private float acResistance;
	
	@Column
	private float cm;
	
	@Column
	private float q;
	
	
}
