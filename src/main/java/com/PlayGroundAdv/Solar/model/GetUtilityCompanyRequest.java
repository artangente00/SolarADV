package com.PlayGroundAdv.Solar.model;

public class GetUtilityCompanyRequest {
	public UtilityCompanyModelRequest utilityCompanyModelRequest;
	public Long idUser;
	public Integer page;
	public Integer size;

	public GetUtilityCompanyRequest() {
		super();
	}

	public GetUtilityCompanyRequest(UtilityCompanyModelRequest utilityCompanyModelRequest, Long idUser, Integer page,
			Integer size) {
		super();
		this.utilityCompanyModelRequest = utilityCompanyModelRequest;
		this.idUser = idUser;
		this.page = page;
		this.size = size;
	}

	public UtilityCompanyModelRequest getUtilityCompanyModelRequest() {
		return utilityCompanyModelRequest;
	}

	public void setUtilityCompanyModelRequest(UtilityCompanyModelRequest utilityCompanyModelRequest) {
		this.utilityCompanyModelRequest = utilityCompanyModelRequest;
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
		return "GetUtilityCompanyRequest [utilityCompanyModelRequest=" + utilityCompanyModelRequest + ", idUser="
				+ idUser + ", page=" + page + ", size=" + size + "]";
	}

}