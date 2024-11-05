package com.PlayGroundAdv.Solar.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "NotificationEntity")
public class NotificationEntity implements Serializable {

	/**
	 * PERMIT DISPLAY ENTITY FOR GRID & FILE UPLOADED
	 */ 
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@SequenceGenerator(name="notif_seq", sequenceName = "notif_seq", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="notif_seq")  
	private Long id;
	
	@Column(name="TYPE_NOTIF")
	private String typeNotif;
	
	@Column(name="IS_SHOWN")
	private Boolean isShowen;
	
	@Column(name="JOUR")
	private String Jour;
	
	@Column(name="HEUR")
	private String heur;
	
	@Column(name="DATE_NOTIF")
	private Date dateNotif;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="SUBJECT")
	private String subject;
	
	@Column(name="MESSAGE")
	private String message;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity permitNotif;
	
	@JoinColumn(name = "ID_USER")
	@ManyToOne
	private AuthentificationEntity userNotif;

	public PermitEntity getPermitNotif() {
		return permitNotif;
	}

	public void setPermitNotif(PermitEntity permitNotif) {
		this.permitNotif = permitNotif;
	}

	public AuthentificationEntity getUserNotif() {
		return userNotif;
	}

	public void setUserNotif(AuthentificationEntity userNotif) {
		this.userNotif = userNotif;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeNotif() {
		return typeNotif;
	}

	public void setTypeNotif(String typeNotif) {
		this.typeNotif = typeNotif;
	}

	public Boolean getIsShowen() {
		return isShowen;
	}

	public void setIsShowen(Boolean isShowen) {
		this.isShowen = isShowen;
	}

	public String getJour() {
		return Jour;
	}

	public void setJour(String jour) {
		Jour = jour;
	}

	public String getHeur() {
		return heur;
	}

	public void setHeur(String heur) {
		this.heur = heur;
	}

	public Date getDateNotif() {
		return dateNotif;
	}

	public void setDateNotif(Date dateNotif) {
		this.dateNotif = dateNotif;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	
}
