package com.PlayGroundAdv.Solar.model;

public class SearchBackFeedSolarOCPDResult {

	private Long id;
	private Integer backFeed;
	private String updated;
	private Boolean isDeleted;
	private UsersEntityResult owner;
	private String fullName;

	public SearchBackFeedSolarOCPDResult() {
		super();
	}

	public SearchBackFeedSolarOCPDResult(Long id, Integer backFeed, String updated, Boolean isDeleted,
			UsersEntityResult owner, String fullName) {
		super();
		this.id = id;
		this.backFeed = backFeed;
		this.updated = updated;
		this.isDeleted = isDeleted;
		this.owner = owner;
		this.fullName = fullName;
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

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public UsersEntityResult getOwner() {
		return owner;
	}

	public void setOwner(UsersEntityResult owner) {
		this.owner = owner;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "SearchBackFeedSolarOCPDResult [id=" + id + ", backFeed=" + backFeed + ", updated=" + updated
				+ ", isDeleted=" + isDeleted + ", owner=" + owner + ", fullName=" + fullName + "]";
	}

}
