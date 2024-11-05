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


@Entity
@Table(name = "BackFeedSolarOCPD")
public class BackFeedSolarOCPD {

	/**
	 *  CLASS ENTITY
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="BackFeedSolarOCPD_sequence",
			           sequenceName="BackFeedSolarOCPD_sequence",
			           allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BackFeedSolarOCPD_sequence")  
	private Long id;
	
	
	@Column(name="BACK_FEED")
	private Integer backFeed;
	
	@Column(name="UPDATED")
	private String updated;
	
	@Column(name="IS_DELETED")
	private boolean isDeleted;
	
	@Column(name="HAS_SUPER_USER_EDIT")
	private Boolean hasSuperUserEdit;
	
	@Column(name="ADD_Date")
	private Date addDate;
	
	@JoinColumn(name = "ID_OWNER")
	@ManyToOne
	private AuthentificationEntity authentificationEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getBackFeed() {
		return backFeed;
	}

	public void setBackFeed(Integer backFeed) {
		this.backFeed = backFeed;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getHasSuperUserEdit() {
		return hasSuperUserEdit;
	}

	public void setHasSuperUserEdit(Boolean hasSuperUserEdit) {
		this.hasSuperUserEdit = hasSuperUserEdit;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public AuthentificationEntity getAuthentificationEntity() {
		return authentificationEntity;
	}

	public void setAuthentificationEntity(AuthentificationEntity authentificationEntity) {
		this.authentificationEntity = authentificationEntity;
	}

	@Override
	public String toString() {
		return "BackFeedSolarOCPD [id=" + id + ", backFeed=" + backFeed + ", updated=" + updated + ", isDeleted="
				+ isDeleted + ", hasSuperUserEdit=" + hasSuperUserEdit + ", addDate=" + addDate
				+ ", authentificationEntity=" + authentificationEntity + "]";
	}
	
    

    
}
