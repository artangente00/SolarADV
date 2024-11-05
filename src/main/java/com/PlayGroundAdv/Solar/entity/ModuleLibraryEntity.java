package com.PlayGroundAdv.Solar.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
 * @author Arij
 */
@Entity
@Table(name = "ModuleLibraryEntity")
public class ModuleLibraryEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@SequenceGenerator(name="hibernate_sequence39", sequenceName = "hibernate_sequence39", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence39")
	private Long id;
	
	@JoinColumn(name = "ID_CONTRACTOR")
	@ManyToOne
	private AuthentificationEntity authentificationEntity;

	@JoinColumn(name = "ID_MODULE")
	@ManyToOne
	private Cmodulev2 cmodulev2;
	
	public ModuleLibraryEntity(AuthentificationEntity authentificationEntity, Cmodulev2 cmodulev2) {
		super();
		this.authentificationEntity = authentificationEntity;
		this.cmodulev2 = cmodulev2;
	}

	public ModuleLibraryEntity() {
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

	public Cmodulev2 getCmodulev2() {
		return cmodulev2;
	}

	public void setCmodulev2(Cmodulev2 cmodulev2) {
		this.cmodulev2 = cmodulev2;
	}
}
