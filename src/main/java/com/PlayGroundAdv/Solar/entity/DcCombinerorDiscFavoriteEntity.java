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
@Table(name = "DcCombinerorDiscFavoriteEntity")
public class DcCombinerorDiscFavoriteEntity {

private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="hibernate_sequence62", sequenceName = "hibernate_sequence62", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence62")
	private Long id;
	
	@JoinColumn(name = "ID_CONTRACTOR")
	@ManyToOne
	private AuthentificationEntity authentificationEntity;

	@JoinColumn(name = "DCCombinerDisconnectEntity")
	@ManyToOne
	private DCCombinerDisconnectEntity dcCombinerDisconnectEntity;

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

	public DCCombinerDisconnectEntity getDcCombinerDisconnectEntity() {
		return dcCombinerDisconnectEntity;
	}

	public void setDcCombinerDisconnectEntity(DCCombinerDisconnectEntity dCCombinerDisconnectEntity) {
		this.dcCombinerDisconnectEntity = dCCombinerDisconnectEntity;
	}

	public DcCombinerorDiscFavoriteEntity(AuthentificationEntity authentificationEntity,
			DCCombinerDisconnectEntity dCCombinerDisconnectEntity) {
		super();
		this.authentificationEntity = authentificationEntity;
		this.dcCombinerDisconnectEntity = dCCombinerDisconnectEntity;
	}

	public DcCombinerorDiscFavoriteEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
}
