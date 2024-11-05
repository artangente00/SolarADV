package com.PlayGroundAdv.Solar.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ACDisconnectFavLibraryEntity")
public class ACDisconnectFavLibraryEntity {

private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="hibernate_sequence43", sequenceName = "hibernate_sequence43", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence43")
	private Long id;
	
	@JoinColumn(name = "ID_CONTRACTOR")
	@ManyToOne
	private AuthentificationEntity authentificationEntity;

	@JoinColumn(name = "ID_AC_Disconnect")
	@ManyToOne
	private ACDisconnect ACDisconnect;

	public ACDisconnectFavLibraryEntity(AuthentificationEntity authentificationEntity, ACDisconnect aCDisconnect) {
		super();
		this.authentificationEntity = authentificationEntity;
		this.ACDisconnect = aCDisconnect;
	}

	public ACDisconnectFavLibraryEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AuthentificationEntity getAuthentificationEntity() {
		return authentificationEntity;
	}

	public void setAuthentificationEntity(AuthentificationEntity authentificationEntity) {
		this.authentificationEntity = authentificationEntity;
	}

	public ACDisconnect getACDisconnect() {
		return ACDisconnect;
	}

	public void setACDisconnect(ACDisconnect aCDisconnect) {
		this.ACDisconnect = aCDisconnect;
	}
}
