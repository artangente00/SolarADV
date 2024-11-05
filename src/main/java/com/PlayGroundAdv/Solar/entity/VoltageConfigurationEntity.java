package com.PlayGroundAdv.Solar.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VoltageConfigurationEntity")
public class VoltageConfigurationEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String voltage;
	
	@JoinColumn(name = "ID_INVERTER")
	@ManyToOne
	private Inverters idInverter;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}

	public Inverters getIdInverter() {
		return idInverter;
	}

	public void setIdInverter(Inverters idInverter) {
		this.idInverter = idInverter;
	}

	

}
