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
@Table(name = "RailRackingFavLibraryEntity")
public class RailRackingFavLibraryEntity {

	
private static final long serialVersionUID = 1L;

	
	@Id
	@SequenceGenerator(name="hibernate_sequence41", sequenceName = "hibernate_sequence41", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence41")
	private Long id;
	
	@JoinColumn(name = "ID_CONTRACTOR")
	@ManyToOne
	private AuthentificationEntity authentificationEntity;

	@JoinColumn(name = "ID_RAILRACKING")
	@ManyToOne
	private RailRacking railRacking;

	public RailRackingFavLibraryEntity(AuthentificationEntity authentificationEntity, RailRacking railRacking) {
		super();
		this.authentificationEntity = authentificationEntity;
		this.railRacking = railRacking;
	}

	public RailRackingFavLibraryEntity() {
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

	public RailRacking getRailRacking() {
		return railRacking;
	}

	public void setRailRacking(RailRacking railRacking) {
		this.railRacking = railRacking;
	}
	
	
}
