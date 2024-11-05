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
@Table(name = "TiltLegsFavLibraryEntity")
public class TiltLegsFavLibraryEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@SequenceGenerator(name="hibernate_sequence40", sequenceName = "hibernate_sequence40", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence40")
	private Long id;
	
	@JoinColumn(name = "ID_CONTRACTOR")
	@ManyToOne
	private AuthentificationEntity authentificationEntity;

	@JoinColumn(name = "ID_TILTLEGS")
	@ManyToOne
	private TiltLegs tiltLegs;

	public TiltLegsFavLibraryEntity(AuthentificationEntity authentificationEntity, TiltLegs tiltLegs) {
		super();
		this.authentificationEntity = authentificationEntity;
		this.tiltLegs = tiltLegs;
	}

	public TiltLegsFavLibraryEntity() {
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

	public TiltLegs getTiltLegs() {
		return tiltLegs;
	}

	public void setTiltLegs(TiltLegs tiltLegs) {
		this.tiltLegs = tiltLegs;
	}

	
	
}
