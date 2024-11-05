package com.PlayGroundAdv.Solar.model;

public class GetConverterRequest {

	public SearchConvertersRequest searchConvertersRequest;
	public Long idUser;
	public Integer page;
	public Integer size;

	public GetConverterRequest() {
		super();
	}

	public GetConverterRequest(SearchConvertersRequest searchConvertersRequest, Long idUser, Integer page,
			Integer size) {
		super();
		this.searchConvertersRequest = searchConvertersRequest;
		this.idUser = idUser;
		this.page = page;
		this.size = size;
	}

	public SearchConvertersRequest getSearchConvertersRequest() {
		return searchConvertersRequest;
	}

	public void setSearchConvertersRequest(SearchConvertersRequest searchConvertersRequest) {
		this.searchConvertersRequest = searchConvertersRequest;
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
		return "GetConverterRequest [searchConvertersRequest=" + searchConvertersRequest + ", idUser=" + idUser
				+ ", page=" + page + ", size=" + size + "]";
	}

}
