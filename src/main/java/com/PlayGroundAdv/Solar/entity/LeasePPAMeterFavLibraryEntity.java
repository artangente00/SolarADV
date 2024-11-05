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
@Table(name = "LeasePPAMeterFavLibraryEntity")
public class LeasePPAMeterFavLibraryEntity {
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

	@JoinColumn(name = "ID_LEASEPPAMETER")
	@ManyToOne
	private LeasePPAMeter leasePPAMeter;

	public LeasePPAMeterFavLibraryEntity() {
		super();
	}

	public LeasePPAMeterFavLibraryEntity(AuthentificationEntity authentificationEntity, LeasePPAMeter leasePPAMeter) {
		super();
		this.authentificationEntity = authentificationEntity;
		this.leasePPAMeter = leasePPAMeter;
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

	public LeasePPAMeter getLeasePPAMeter() {
		return leasePPAMeter;
	}

	public void setLeasePPAMeter(LeasePPAMeter leasePPAMeter) {
		this.leasePPAMeter = leasePPAMeter;
	}

	
	
}
