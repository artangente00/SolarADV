package com.PlayGroundAdv.Solar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/*
 * @author Soumeya
 */
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "SsheetSpacingMappingEntity")
public class SsheetSpacingMappingEntity {

	/**
	 * CLASS ENTITY
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SsheetSpacingMappingLibrary_sequence", sequenceName = "SsheetSpacingMappingLibrary_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SsheetSpacingMappingLibrary_sequence")
	private Long id;

	@Column(name = "ROOF_TYPE")
	private String roofType;

	@Column(name = "ROOF_TYPE_OTHER")
	private String roofTypeOther;

	@Column(name = "RAFTER_TRUSS_SPACING")
	private String rafterTrussSpacing;

	@Column(name = "STANCHION_MAX_SPACING")
	private String stanchionMaxSpacing;

	@Column(name = "SHEET_NUMBER")
	private String sheetNumber;

	@Column(name = "QUAD_NUMBER")
	private String quadNumber;

	@Column(name = "DETAILS_HEADING")
	private String detailsHeading;

	@Column(name = "PDF_NAME")
	private String pdfname;

	@Column(name = "LAST_UPDATE")
	private Date lastUpdate;

	@JoinColumn(name = "ADDED_BY")
	@ManyToOne
	private AuthentificationEntity addedBy;

	@JoinColumn(name = "S_SHEET_FILE")
	@ManyToOne
	private SsheetLibraryEntity sSheetFile;

	@Column(name = "IS_DELETED")
	private Boolean isDeleted;

	@Column(name = "DELETED_ON")
	private Date deletedOn;

	@JoinColumn(name = "DELETED_BY")
	@ManyToOne
	private AuthentificationEntity deletedBy;

	@Column(name = "STANCHION_TYPE")
	private String stanchionType;
	@Column(name = "FLASHING")
	private Boolean flashing;

}
