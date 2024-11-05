package com.PlayGroundAdv.Solar.model;

public class GetTiltLegsRequest {
	public SearchTiltLegsRequest searchTiltLegsRequest;
	public Long idUser;
	public Integer page;
	public Integer size;

	public GetTiltLegsRequest() {
		super();
	}

	public GetTiltLegsRequest(SearchTiltLegsRequest searchTiltLegsRequest, Long idUser, Integer page, Integer size) {
		super();
		this.searchTiltLegsRequest = searchTiltLegsRequest;
		this.idUser = idUser;
		this.page = page;
		this.size = size;
	}

	public SearchTiltLegsRequest getSearchTiltLegsRequest() {
		return searchTiltLegsRequest;
	}

	public void setSearchTiltLegsRequest(SearchTiltLegsRequest searchTiltLegsRequest) {
		this.searchTiltLegsRequest = searchTiltLegsRequest;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
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

	@Override
	public String toString() {
		return "GetTiltLegsRequest [searchTiltLegsRequest=" + searchTiltLegsRequest + ", idUser=" + idUser + ", page="
				+ page + ", size=" + size + "]";
	}

}
