package com.PlayGroundAdv.Solar.model;

import java.util.Date;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;

public class BackFeedSolarOCPDEntityModel {

	private Long id;
	private Integer backFeed;
	private String updated;
	private Boolean isDeleted;
	private Boolean hasSuperUserEdit;
	private Date addDate;
	private AuthentificationEntity authentificationEntity;

	public BackFeedSolarOCPDEntityModel() {
		super();
	}

	public BackFeedSolarOCPDEntityModel(Long id, Integer backFeed, String updated, Boolean isDeleted,
			Boolean hasSuperUserEdit, Date addDate, AuthentificationEntity authentificationEntity) {
		super();
		this.id = id;
		this.backFeed = backFeed;
		this.updated = updated;
		this.isDeleted = isDeleted;
		this.hasSuperUserEdit = hasSuperUserEdit;
		this.addDate = addDate;
		this.authentificationEntity = authentificationEntity;
	}

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

	public Boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(Boolean isDeleted) {
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

}
