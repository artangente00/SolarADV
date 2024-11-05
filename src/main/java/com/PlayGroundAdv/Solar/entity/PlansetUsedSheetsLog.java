package com.PlayGroundAdv.Solar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "PLANSET_USED_SHEETS_LOG")
public class PlansetUsedSheetsLog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "PROJECT")
	@ManyToOne
	private PermitEntity project;
	
	@JoinColumn(name = "USER_INFO")
	@ManyToOne
	private AuthentificationEntity userInfo;
	
	@Column(name="DATE")
	private Date date;
	
	@Column(name="PV_001")
	private String pv_001;
	
	@Column(name="N_001")
	private String n_001;
	
	@Column(name="PV_100R")
	private String pv_100R;
	
	@Column(name="PV_101R")
	private String pv_101R;
	
	@Column(name="PV_100G")
	private String pv_100G;
	
	@Column(name="PV_101")
	private String pv_101G;
	
	@Column(name="S_100")
	private String s_100;
	
	@Column(name="S_200")
	private String s_200;
	
	@Column(name="S_201")
	private String s_201;
	
	@Column(name="S_300")
	private String s_300;
	
	@Column(name="E_001")
	private String e_001;
	
	@Column(name="E_002")
	private String e_002;
	
	@Column(name="E_003")
	private String e_003;
	
	@Column(name="E_100")
	private String e_100;
	
	@Column(name="P_001")
	private String p_001;
	
	@Column(name="P_002")
	private String p_002;
	
	@Column(name="R_SHEET")
	private String r_sheet;

	public PermitEntity getProject() {
		return project;
	}

	public void setProject(PermitEntity project) {
		this.project = project;
	}

	public AuthentificationEntity getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(AuthentificationEntity userInfo) {
		this.userInfo = userInfo;
	}
	
	

}
