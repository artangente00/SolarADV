package com.PlayGroundAdv.Solar.model;

public class GetRFIRequest {
	public String fieldName;
	public Boolean isQFav;
	public Integer page;
	public Integer size;

	public GetRFIRequest() {
		super();
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Boolean getIsQFav() {
		return isQFav;
	}

	public void setIsQFav(Boolean isQFav) {
		this.isQFav = isQFav;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public GetRFIRequest(String fieldName, Boolean isQFav, Integer page, Integer size) {
		super();
		this.fieldName = fieldName;
		this.isQFav = isQFav;
		this.page = page;
		this.size = size;
	}

	@Override
	public String toString() {
		return "GetRFIRequest [fieldName=" + fieldName + ", isQFav=" + isQFav + ", page=" + page + ", size=" + size
				+ "]";
	}

}
