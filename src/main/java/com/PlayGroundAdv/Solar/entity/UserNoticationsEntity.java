package com.PlayGroundAdv.Solar.entity;

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
@Table(name = "UserNoticationsEntity")
public class UserNoticationsEntity {

	/**
	 * PERMIT DISPLAY ENTITY FOR GRID & FILE UPLOADED
	 */ 
	private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_notif_seq")
    @SequenceGenerator(name = "user_notif_seq", sequenceName = "user_notif_seq", allocationSize = 1)
	private Long id;

	
	@JoinColumn(name = "ID_USER")
	@ManyToOne
	private AuthentificationEntity idUser;
	
	@JoinColumn(name = "ID_Notif")
	@ManyToOne
	private NotificationEntity idNotif;
	
	@Column(name="IS_READ")
	private Boolean isRead;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AuthentificationEntity getIdUser() {
		return idUser;
	}

	public void setIdUser(AuthentificationEntity idUser) {
		this.idUser = idUser;
	}

	public NotificationEntity getIdNotif() {
		return idNotif;
	}

	public void setIdNotif(NotificationEntity idNotif) {
		this.idNotif = idNotif;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}
	
	
	
}
