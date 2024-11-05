package com.PlayGroundAdv.Solar.model;


public class SearchRoofMaterialTypeResult {

	private Long id;
	private String typeRoof;
	private String updated;
	private boolean isDeleted;
	private UsersEntityResult owner;
	private String fullName;
	private String mappingValue;

	public SearchRoofMaterialTypeResult() {
		super();
	}

	public SearchRoofMaterialTypeResult(Long id, String typeRoof, String updated, boolean isDeleted,
			UsersEntityResult owner, String fullName, String mappingValue) {
		super();
		this.id = id;
		this.typeRoof = typeRoof;
		this.updated = updated;
		this.isDeleted = isDeleted;
		this.owner = owner;
		this.fullName = fullName;
		this.mappingValue = mappingValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeRoof() {
		return typeRoof;
	}

	public void setTypeRoof(String typeRoof) {
		this.typeRoof = typeRoof;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMappingValue() {
		return mappingValue;
	}

	public void setMappingValue(String mappingValue) {
		this.mappingValue = mappingValue;
	}

	public UsersEntityResult getOwner() {
		return owner;
	}

	public void setOwner(UsersEntityResult owner) {
		this.owner = owner;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "SearchRoofMaterialTypeResult [id=" + id + ", typeRoof=" + typeRoof + ", updated=" + updated
				+ ", isDeleted=" + isDeleted + ", owner=" + owner + ", fullName=" + fullName + ", mappingValue="
				+ mappingValue + "]";
	}

}
