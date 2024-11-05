package com.PlayGroundAdv.Solar.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*
 * @author Soumeya
 */
@Entity
public class MountingTypeEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String mountingType;
	
	@ManyToOne()
	@JoinColumn(name="id_rail")
	private RailRacking idRail;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMountingType() {
		return mountingType;
	}

	public void setMountingType(String mountingType) {
		this.mountingType = mountingType;
	}

	public RailRacking getIdRail() {
		return idRail;
	}

	public void setIdRail(RailRacking idRail) {
		this.idRail = idRail;
	}
	

}
