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

/*
 * Author Soumeya
 */
@Entity
@Table(name = "SelectDrafterSheet")
public class SelectDrafterSheet {
	
	/**
	 *  CLASS ENTITY
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="SelectDrafterSheet_sequence",
			           sequenceName="SelectDrafterSheet_sequence",
			           allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SelectDrafterSheet_sequence")  
	private Long id;
	
	@Column(name="PAGE_NUMBER")
	private Integer pageNumber;
	
	@Column(name="PAGE_SHEET")
	private String pageSheet;
	
	@JoinColumn(name = "idPermit")
	@ManyToOne
	private PermitEntity idPermit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getPageSheet() {
		return pageSheet;
	}

	public void setPageSheet(String pageSheet) {
		this.pageSheet = pageSheet;
	}

	public PermitEntity getIdPermit() {
		return idPermit;
	}

	public void setIdPermit(PermitEntity idPermit) {
		this.idPermit = idPermit;
	}
	
	

}
