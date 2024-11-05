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
@Table(name = "PermitTotalSectionEntity")
public class PermitTotalSectionEntity {

	@Id
	@SequenceGenerator(name="hibernate_sequence23", sequenceName = "hibernate_sequence23", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence23")  
	private Long id;
	
	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity permitEntity;
	
	@Column(name="SECTION")
	private Integer section;
	
	@Column(name="SQUARE_FOOTAGE")
	private Integer squareFootage;
	
	@Column(name="EAVE_OVERHANG")
	private String eaveOverhang;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the permitEntity
	 */
	public PermitEntity getPermitEntity() {
		return permitEntity;
	}

	/**
	 * @return the section
	 */
	public Integer getSection() {
		return section;
	}

	/**
	 * @return the squareFootage
	 */
	public Integer getSquareFootage() {
		return squareFootage;
	}

	/**
	 * @return the eaveOverhang
	 */
	public String getEaveOverhang() {
		return eaveOverhang;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param permitEntity the permitEntity to set
	 */
	public void setPermitEntity(PermitEntity permitEntity) {
		this.permitEntity = permitEntity;
	}

	/**
	 * @param section the section to set
	 */
	public void setSection(Integer section) {
		this.section = section;
	}

	/**
	 * @param squareFootage the squareFootage to set
	 */
	public void setSquareFootage(Integer squareFootage) {
		this.squareFootage = squareFootage;
	}

	/**
	 * @param eaveOverhang the eaveOverhang to set
	 */
	public void setEaveOverhang(String eaveOverhang) {
		this.eaveOverhang = eaveOverhang;
	}

	@Override
	public String toString() {
		return "PermitTotalSectionEntity [id=" + id + ", permitEntity=" + permitEntity + ", section=" + section
				+ ", squareFootage=" + squareFootage + ", eaveOverhang=" + eaveOverhang + "]";
	}
	
	
	
}
