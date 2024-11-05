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
@Table(name = "JunctionBoxFavLibraryEntity")
public class JunctionBoxFavLibraryEntity {

	@Id
	@SequenceGenerator(name="hibernate_sequence63", sequenceName = "hibernate_sequence63", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence63")
	private Long id;
	
	@JoinColumn(name = "ID_CONTRACTOR")
	@ManyToOne
	private AuthentificationEntity authentificationEntity;

	@JoinColumn(name = "DCCombinerDisconnectEntity")
	@ManyToOne
	private DCCombinerDisconnectEntity jbox;

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

	public DCCombinerDisconnectEntity getJbox() {
		return jbox;
	}

	public void setJbox(DCCombinerDisconnectEntity jbox) {
		this.jbox = jbox;
	}

	public JunctionBoxFavLibraryEntity(AuthentificationEntity authentificationEntity, DCCombinerDisconnectEntity jbox) {
		super();
		this.authentificationEntity = authentificationEntity;
		this.jbox = jbox;
	}

	public JunctionBoxFavLibraryEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
}
