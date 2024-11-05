package com.PlayGroundAdv.Solar.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "NOT_ALLOWED_RACKING_NOTES")
@RequiredArgsConstructor
public class NotAllowedRackingNotes {
	
	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
	@Getter @Setter
    private PermitEntity project;
	
	@Column(name="HAS_NOT_ALLOWED_ROOF_RACKING")
	@Getter @Setter
	private Boolean hasNotAllowedRoofRacking;
	
	@Column(name="NOT_ALLOWED_ROOF_RACKING_NOTE")
	@Getter @Setter
	private String notAllowedRoofRackingNote;
	
	@Column(name="NOT_ALLOWED_ROOF_RACKING_FILE_NAME")
	@Getter @Setter
	private String notAllowedRoofRackingFileName;
	
	@Column(name="HAS_ROOF_RACKING_NOTE")
	@Getter @Setter
	private Boolean hasRoofRackingNote;

}
