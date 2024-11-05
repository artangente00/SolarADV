package com.PlayGroundAdv.Solar.model;

public class GetPermitCompanyInfoEntity {

	private PermitCompanyInfoEntityResultPrime permitCompanyInfoEntity;
	private PermitEditStatusModel permitEditStatusModel;

	public GetPermitCompanyInfoEntity() {
		super();
	}

	public GetPermitCompanyInfoEntity(PermitCompanyInfoEntityResultPrime permitCompanyInfoEntity,
			PermitEditStatusModel permitEditStatusModel) {
		super();
		this.setPermitCompanyInfoEntity(permitCompanyInfoEntity);
		this.setPermitEditStatusModel(permitEditStatusModel);
	}

	public PermitCompanyInfoEntityResultPrime getPermitCompanyInfoEntity() {
		return permitCompanyInfoEntity;
	}

	public void setPermitCompanyInfoEntity(PermitCompanyInfoEntityResultPrime permitCompanyInfoEntity) {
		this.permitCompanyInfoEntity = permitCompanyInfoEntity;
	}

	public PermitEditStatusModel getPermitEditStatusModel() {
		return permitEditStatusModel;
	}

	public void setPermitEditStatusModel(PermitEditStatusModel permitEditStatusModel) {
		this.permitEditStatusModel = permitEditStatusModel;
	}

}
