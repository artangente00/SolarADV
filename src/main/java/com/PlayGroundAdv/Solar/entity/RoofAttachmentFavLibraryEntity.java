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
@Table(name = "RoofAttachmentFavLibraryEntity")
public class RoofAttachmentFavLibraryEntity {

	
private static final long serialVersionUID = 1L;

	
	@Id
	@SequenceGenerator(name="hibernate_sequence42", sequenceName = "hibernate_sequence42", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence42")
	private Long id;
	
	@JoinColumn(name = "ID_CONTRACTOR")
	@ManyToOne
	private AuthentificationEntity authentificationEntity;

	@JoinColumn(name = "ID_ROOF_ATTACHEMENT")
	@ManyToOne
	private RoofAttachmentsEntity roofAttachment;

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

	public RoofAttachmentsEntity getRoofAttachment() {
		return roofAttachment;
	}

	public void setRoofAttachment(RoofAttachmentsEntity roofAttachment) {
		this.roofAttachment = roofAttachment;
	}

	public RoofAttachmentFavLibraryEntity(AuthentificationEntity authentificationEntity,
			RoofAttachmentsEntity roofAttachment) {
		super();
		this.authentificationEntity = authentificationEntity;
		this.roofAttachment = roofAttachment;
	}

	public RoofAttachmentFavLibraryEntity() {
		super();
	}

	
	
}
