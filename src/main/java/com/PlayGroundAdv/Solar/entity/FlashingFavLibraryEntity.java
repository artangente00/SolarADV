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
@Table(name = "FlashingFavLibraryEntity")
public class FlashingFavLibraryEntity {
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

	@JoinColumn(name = "ID_FLASHING")
	@ManyToOne
	private Flashing flashing;

	public FlashingFavLibraryEntity(AuthentificationEntity authentificationEntity, Flashing flashing) {
		super();
		this.authentificationEntity = authentificationEntity;
		this.flashing = flashing;
	}

	public FlashingFavLibraryEntity() {
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

	public Flashing getFlashing() {
		return flashing;
	}

	public void setFlashing(Flashing flashing) {
		this.flashing = flashing;
	}

	
	
}
