package com.PlayGroundAdv.Solar.model;

import java.util.Date;

public class PlansetUsedSheetsLogModel {

	private String project;
	private Date date;
	private String pv_001;
	private String n_001;
	private String pv_100R;
	private String pv_100G;
	private String pv_101G;
	private String s_100;
	private String s_200;
	private String s_201;
	private String s_300;
	private String e_001;
	private String e_002;
	private String e_003;
	private String e_100;
	private String p_001;
	private String p_002;
	private String r_sheet;

	public PlansetUsedSheetsLogModel() {
		super();
	}

	public PlansetUsedSheetsLogModel(String project, Date date, String pv_001, String n_001, String pv_100r,
			String pv_100g, String pv_101g, String s_100, String s_200, String s_201, String s_300, String e_001,
			String e_002, String e_003, String e_100, String p_001, String p_002, String r_sheet) {
		super();

		this.project = project;
		this.date = date;
		this.pv_001 = pv_001;
		this.n_001 = n_001;
		pv_100R = pv_100r;
		pv_100G = pv_100g;
		pv_101G = pv_101g;
		this.s_100 = s_100;
		this.s_200 = s_200;
		this.s_201 = s_201;
		this.s_300 = s_300;
		this.e_001 = e_001;
		this.e_002 = e_002;
		this.e_003 = e_003;
		this.e_100 = e_100;
		this.p_001 = p_001;
		this.p_002 = p_002;
		this.r_sheet = r_sheet;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPv_001() {
		return pv_001;
	}

	public void setPv_001(String pv_001) {
		this.pv_001 = pv_001;
	}

	public String getN_001() {
		return n_001;
	}

	public void setN_001(String n_001) {
		this.n_001 = n_001;
	}

	public String getPv_100R() {
		return pv_100R;
	}

	public void setPv_100R(String pv_100r) {
		pv_100R = pv_100r;
	}

	public String getPv_100G() {
		return pv_100G;
	}

	public void setPv_100G(String pv_100g) {
		pv_100G = pv_100g;
	}

	public String getPv_101G() {
		return pv_101G;
	}

	public void setPv_101G(String pv_101g) {
		pv_101G = pv_101g;
	}

	public String getS_100() {
		return s_100;
	}

	public void setS_100(String s_100) {
		this.s_100 = s_100;
	}

	public String getS_200() {
		return s_200;
	}

	public void setS_200(String s_200) {
		this.s_200 = s_200;
	}

	public String getS_201() {
		return s_201;
	}

	public void setS_201(String s_201) {
		this.s_201 = s_201;
	}

	public String getS_300() {
		return s_300;
	}

	public void setS_300(String s_300) {
		this.s_300 = s_300;
	}

	public String getE_001() {
		return e_001;
	}

	public void setE_001(String e_001) {
		this.e_001 = e_001;
	}

	public String getE_002() {
		return e_002;
	}

	public void setE_002(String e_002) {
		this.e_002 = e_002;
	}

	public String getE_003() {
		return e_003;
	}

	public void setE_003(String e_003) {
		this.e_003 = e_003;
	}

	public String getE_100() {
		return e_100;
	}

	public void setE_100(String e_100) {
		this.e_100 = e_100;
	}

	public String getP_001() {
		return p_001;
	}

	public void setP_001(String p_001) {
		this.p_001 = p_001;
	}

	public String getP_002() {
		return p_002;
	}

	public void setP_002(String p_002) {
		this.p_002 = p_002;
	}

	public String getR_sheet() {
		return r_sheet;
	}

	public void setR_sheet(String r_sheet) {
		this.r_sheet = r_sheet;
	}

}
