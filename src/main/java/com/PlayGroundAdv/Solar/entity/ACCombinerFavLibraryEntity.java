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
@Table(name = "ACCombinerFavLibraryEntity")
public class ACCombinerFavLibraryEntity {

private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="hibernate_sequence43", sequenceName = "hibernate_sequence43", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence43")
	private Long id;

	@JoinColumn(name = "ID_CONTRACTOR")
	@ManyToOne
	private AuthentificationEntity authentificationEntity;

	@JoinColumn(name = "ID_AC_COMBINER_SLC")
	@ManyToOne
	private ACCombinerSLC acCombinerSlc;

	public ACCombinerFavLibraryEntity(AuthentificationEntity authentificationEntity, ACCombinerSLC acCombinerSlc) {
		super();
		this.authentificationEntity = authentificationEntity;
		this.acCombinerSlc = acCombinerSlc;
	}

	public ACCombinerFavLibraryEntity() {
		super();
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

	public ACCombinerSLC getAcCombinerSLC() {
		return acCombinerSlc;
	}

	public void setAcCombinerSLC(ACCombinerSLC acCombinerSlc) {
		this.acCombinerSlc = acCombinerSlc;
	}
}
