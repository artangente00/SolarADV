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
@Table(name = "ProposedSubPanelFavLibraryEntity")
public class ProposedSubPanelFavLibraryEntity {
	
	@Id
	@SequenceGenerator(name="hibernate_sequence40", sequenceName = "hibernate_sequence40", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence40")
	private Long id;
	
	@JoinColumn(name = "ID_CONTRACTOR")
	@ManyToOne
	private AuthentificationEntity authentificationEntity;

	@JoinColumn(name = "ID_PROPOSEDSUBPANEL")
	@ManyToOne
	private ProposedSubPanel proposedSubPanel;

	public ProposedSubPanelFavLibraryEntity(AuthentificationEntity authentificationEntity,
			ProposedSubPanel proposedSubPanel) {
		super();
		this.authentificationEntity = authentificationEntity;
		this.proposedSubPanel = proposedSubPanel;
	}

	public ProposedSubPanelFavLibraryEntity() {
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

	public ProposedSubPanel getProposedSubPanel() {
		return proposedSubPanel;
	}

	public void setProposedSubPanel(ProposedSubPanel proposedSubPanel) {
		this.proposedSubPanel = proposedSubPanel;
	}

	
	
}
