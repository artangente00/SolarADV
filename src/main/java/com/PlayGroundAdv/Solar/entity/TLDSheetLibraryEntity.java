package com.PlayGroundAdv.Solar.entity;

import java.util.Date;

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
 * @author Arij
 */
@Entity
@Table(name = "TLDSheetLibraryEntity")
public class TLDSheetLibraryEntity {

	/**
	 *  CLASS ENTITY
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="TLDsheetsLibrary_sequence",
			           sequenceName="TLDsheetsLibrary_sequence",
			           allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TLDsheetsLibrary_sequence")  
	private Long id;
	
	@Column(name="pdf_Name")
	private String pdfName;
	
	@Column(name = "LAST_UPDATE")
	private Date lastUpdate;
	
	@Column(name = "IS_DELETED")
	private Boolean isDeleted;
	
	@JoinColumn(name = "ADDED_BY")
	@ManyToOne
	private AuthentificationEntity addedBy;
	
	@JoinColumn(name = "UPDATED_BY")
	@ManyToOne
	private AuthentificationEntity updatedBy;
	
	@Column(name = "DELETED_ON")
	private Date deletedOn;
	
	@JoinColumn(name = "DELETED_BY")
	@ManyToOne
	private AuthentificationEntity deletedBy;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPdfName() {
		return pdfName;
	}

	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public AuthentificationEntity getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(AuthentificationEntity addedBy) {
		this.addedBy = addedBy;
	}

	
	public AuthentificationEntity getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(AuthentificationEntity updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
	public Date getDeletedOn() {
		return deletedOn;
	}

	public void setDeletedOn(Date deletedOn) {
		this.deletedOn = deletedOn;
	}

	public AuthentificationEntity getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(AuthentificationEntity deletedBy) {
		this.deletedBy = deletedBy;
	}

}
