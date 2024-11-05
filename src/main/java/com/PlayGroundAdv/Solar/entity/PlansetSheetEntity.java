package com.PlayGroundAdv.Solar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
 * @author Arij
 */
@Entity
@Table(name = "PlansetSheetEntity")
public class PlansetSheetEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "acdisc_sequence", sequenceName = "acdisc_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acdisc_sequence")
	private Long id;

	@Column(name = "PDF_NAME")
	private String pdfName;

	@Column(name = "LAST_UPDATE")
	private String lastUpdate;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "LAST_UPDATE_BY")
	private Long lastUpdateBy;
	
	@Column(name = "COMMENT")
	private String comment;

	public Long getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(Long lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public String getPdfName() {
		return pdfName;
	}

	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
