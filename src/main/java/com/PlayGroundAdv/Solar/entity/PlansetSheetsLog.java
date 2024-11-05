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

@Entity
@Table(name = "PLANSET_SHEETS_LOG")
public class PlansetSheetsLog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="SHEET_NAME")
	private String sheetName;
	
	@Column(name="SHEET_TYPE")
	private String sheetType;// A.B 02-17 CEC, NEC or Customize
	
	@Column(name="ACTION")
	private String action;// A.B 02-17 Add, Edit or Delete
	
	@Column(name="COMMENT")
	private String comment;// A.B 02-17 For CEC & NEC
	
	@Column(name="DATE")
	private Date date;
	
	@Column(name="SHEET_ID")
	private Long sheetId;// A.B 02-17 Only for Customize
	
	@JoinColumn(name = "USER_INFO")
	@ManyToOne
	private AuthentificationEntity userInfo;
	
	

	public PlansetSheetsLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlansetSheetsLog(String sheetName, String sheetType, String action, Date date, Long sheetId,
			AuthentificationEntity userInfo) {
		super();
		this.sheetName = sheetName;
		this.sheetType = sheetType;
		this.action = action;
		this.date = date;
		this.sheetId = sheetId;
		this.userInfo = userInfo;
	}
	
	public PlansetSheetsLog(String sheetName, String sheetType, String comment, Date date,
			AuthentificationEntity userInfo) {
		super();
		this.sheetName = sheetName;
		this.sheetType = sheetType;
		this.comment = comment;
		this.date = date;
		this.userInfo = userInfo;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getSheetType() {
		return sheetType;
	}

	public void setSheetType(String sheetType) {
		this.sheetType = sheetType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getSheetId() {
		return sheetId;
	}

	public void setSheetId(Long sheetId) {
		this.sheetId = sheetId;
	}

	public AuthentificationEntity getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(AuthentificationEntity userInfo) {
		this.userInfo = userInfo;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	
	
}
