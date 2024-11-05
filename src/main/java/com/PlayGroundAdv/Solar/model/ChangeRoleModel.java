package com.PlayGroundAdv.Solar.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeRoleModel {
	private Long idUser;
	private Long idRole;
	private Boolean hasSettingAccess;
	private Boolean hasAhjMgtAccess;
	private Boolean hasLibVerifAccess;
	public ChangeRoleModel() {
		super();
	}

	public ChangeRoleModel(Long idUser, Long idRole, Boolean hasSettingAccess, Boolean hasAhjMgtAccess, Boolean hasLibVerifAccess) {
		super();
		this.idUser = idUser;
		this.idRole = idRole;
		this.hasSettingAccess = hasSettingAccess;
		this.hasAhjMgtAccess = hasAhjMgtAccess;
		this.hasLibVerifAccess = hasLibVerifAccess;
	}

}
