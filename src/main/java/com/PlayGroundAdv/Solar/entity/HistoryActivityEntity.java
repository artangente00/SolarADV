package com.PlayGroundAdv.Solar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Nader
 *
 */
@Entity
@Table(name = "HistoryActivityEntity")
public class HistoryActivityEntity {

	
	
private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="hibernate_sequence34", sequenceName = "hibernate_sequence34", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence34")  
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(nullable = true, name = "ID_USER_CO")
	private AuthentificationEntity idUserCo;
	
	@Column(name="IP_USER_CO")
	private String ipUserCo;
	
	@Column(name="TIME_ZONE_USER_CO")
	private String timeZoneUserCo;
	
	@Column(name="TYPE_ACTION")
	private String typeAction;
	
	@Column(name="IS_SUCCESS")
	private Boolean isSuccess;
	
	@Column(name="NUM_TAB")
	private String numTab;
	
	@Column(name="OPEN_DATE")
	private String openDate;
	
	@Column(name="SESSION_ID")
	private String sessionId;

	@Column(name="DATE")
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AuthentificationEntity getIdUserCo() {
		return idUserCo;
	}

	public void setIdUserCo(AuthentificationEntity idUserCo) {
		this.idUserCo = idUserCo;
	}

	public String getIpUserCo() {
		return ipUserCo;
	}

	public void setIpUserCo(String ipUserCo) {
		this.ipUserCo = ipUserCo;
	}


	public String getTypeAction() {
		return typeAction;
	}

	public void setTypeAction(String typeAction) {
		this.typeAction = typeAction;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getTimeZoneUserCo() {
		return timeZoneUserCo;
	}

	public void setTimeZoneUserCo(String timeZoneUserCo) {
		this.timeZoneUserCo = timeZoneUserCo;
	}

	public String getNumTab() {
		return numTab;
	}

	public void setNumTab(String numTab) {
		this.numTab = numTab;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
