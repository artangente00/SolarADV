package com.PlayGroundAdv.Solar.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.PlayGroundAdv.Solar.entity.log.ArchiveLogEntity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PermitEntity")
@Getter
@Setter
@RequiredArgsConstructor
public class PermitEntity {

	/**
	 * PERMIT DISPLAY ENTITY FOR GRID & FILE UPLOADED
	 */
	@Id
	@SequenceGenerator(name = "PermitEntity_seq", sequenceName = "PermitEntity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PermitEntity_seq")
	private Long id;

	@Column(name = "HOME_OWN_NAME")
	private String homeOwnName;

	@Column(name = "ADVANCEMENT")
	private String advancement;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "UPDATE_DATE")
	private Date updateDate;

	@JoinColumn(name = "UPDATED_BY")
	@ManyToOne(fetch = FetchType.EAGER)
	private AuthentificationEntity updatedBy;

	@Column(name = "UPDATED_DATE_V2")
	private String updatedDatev2;

	@Column(name = "UPDATED_DATE_V3")
	private String updatedDatev3;

	@Column(name = "RELEASE_V2")
	private String releasev2;

	@Column(name = "RELEASE_V3")
	private String releasev3;

	@Column(name = "TYPE_FILE_2")
	private String typeFile2;

	@Column(name = "TYPE_FILE_1")
	private String typeFile1;

	@Column(name = "TYPE_FILE_3")
	private String typeFile3;

	@Column(name = "TYPE_FILE_4")
	private String typeFile4;

	@Column(name = "TYPE_FILE_5")
	private String typeFile5;

	@Column(name = "TYPE_FILE_6")
	private String typeFile6;

	@Column(name = "TYPE_FILE_7")
	private String typeFile7;

	@Column(name = "TYPE_FILE_8")
	private String typeFile8;

	@Column(name = "TYPE_FILE_9")
	private String typeFile9;

	@Column(name = "TYPE_FILE_10")
	private String typeFile10;

	@Column(name = "TYPE_FILE_11")
	private String typeFile11;

	@Column(name = "NAME_FILE_1")
	private String nameFile1;

	@Column(name = "NAME_FILE_2")
	private String nameFile2;

	@Column(name = "NAME_FILE_3")
	private String nameFile3;

	@Column(name = "NAME_FILE_4")
	private String nameFile4;

	@Column(name = "NAME_FILE_5")
	private String nameFile5;

	@Column(name = "NAME_FILE_6")
	private String nameFile6;

	@Column(name = "NAME_FILE_7")
	private String nameFile7;

	@Column(name = "NAME_FILE_8")
	private String nameFile8;

	@Column(name = "NAME_FILE_9")
	private String nameFile9;

	@Column(name = "NAME_FILE_10")
	private String nameFile10;

	@Column(name = "NAME_FILE_11")
	private String nameFile11;

	@Column(name = "IS_DELETED")
	private boolean isDeleted;

	@Column(name = "SUBMITTED")
	private boolean submitted;

	@Column
	private Boolean submittedByContractor;

	@Column(name = "IS_PAYED")
	private boolean isPayed;

	@Column(name = "CREATION_PERMIT_DATE")
	private Date creationPermitDate;

	@Column(name = "IS_TEMPLATE")
	private Boolean isTemplate;

	@Column(name = "R_R_VERSION")
	private Integer RRVersion;

	@Column(name = "dateOfSubmitPermitOnHold")
	private Date dateOfSubmitPermitOnHold;

	@Column(name = "isOnHold")
	private Boolean isOnHold;

	@Column(name = "dateOfSubmitPermit")
	private Date dateOfSubmitPermit;

	@Column(name = "isCanceled")
	private Boolean isCanceled;

	@Column(name = "dateOfSubmitPermitCanceled")
	private Date dateOfSubmitPermitCanceled;

	@JoinColumn(name = "ID_CONTRACTOR")
	@ManyToOne
	private AuthentificationEntity authentificationEntity;

	@Column(name = "uploadFile1")
	private String uploadFile1;

	@Column(name = "uploadFile2")
	private String uploadFile2;

	@Column(name = "uploadFile3")
	private String uploadFile3;

	@Column(name = "uploadFile4")
	private String uploadFile4;

	@Column(name = "uploadFile5")
	private String uploadFile5;

	@Column(name = "uploadFile6")
	private String uploadFile6;

	@Column(name = "uploadFile7")
	private String uploadFile7;

	@Column(name = "uploadFile8")
	private String uploadFile8;

	@Column(name = "uploadFile9")
	private String uploadFile9;

	@Column(name = "uploadFile10")
	private String uploadFile10;

	@Column(name = "PLANSET_VERSION")
	private Integer plansetVersion = 0;

	@Column(name = "PLANSET_STATE")
	private String plansetState;

	@Column(name = "PLANSET_INVERTER_TECHNOLOGIES")
	private String PlansetInverterTechnologies;

	@Column(name = "PLANSET_ROOF_DESIGN")
	private String PlansetRoofDesign;

	@Column(name = "PLANSET_ROOF_RAFTER")
	private String PlansetRoofRafter;

	@Column(name = "PROJECT_NAME")
	private String projectName;

	@Column(name = "INSERT_ROOF_NOTE")
	private String insertRoofNote;

	@Column(name = "EXIST_MODULE")
	private Boolean existModule;

	@Column(name = "EXIST_OPTIMIZER")
	private Boolean existOptimizer;

	@Column(name = "EXIST_DCJUNCTIONBOX")
	private Boolean existdcJunctionBox;

	@Column(name = "EXIST_DCCOMBINER")
	private Boolean existdcDcCombiner;

	@Column(name = "EXIST_DCDISCONNECT")
	private Boolean existdcDcdisconnect;

	@Column(name = "EXIST_INVERTER")
	private Boolean existInverter;

	@Column(name = "EXIST_ACJUNCTIONBOX")
	private Boolean existAcJunctionBox;

	@Column(name = "EXIST_ACCOMBINER")
	private Boolean existAcCombiner;

	@Column(name = "EXIST_ACDISCONNECT")
	private Boolean existAcDisconnect;

	@Column(name = "EXIST_PRODUCTIONMETER")
	private Boolean existProductionMeter;

	@Column(name = "EXIST_SUBPANEL")
	private Boolean existSubPanel;

	@Column(name = "OPENED")
	private Boolean opened;

	@Column(name = "IS_CHECKING")
	private Calendar isChecking;

	@JoinColumn(name = "OPENED_BY")
	@ManyToOne
	private AuthentificationEntity openedBy;

	@Column(name = "HAS_EDIT_REQUEST")
	private Boolean hasEditRequest;

	@Column(name = "HAS_CLOSE_REQUEST")
	private Boolean hasCloseRequest;

	@Column(name = "HAS_CLOSE_REQUEST_BY")
	private String hasCloseRequestby;

	@Column(name = "HOMEOWNER_LAST_NAME")
	private String homeOwnLastName;

	@Column(name = "TEMPLATE_NAME")
	private String templateName;

	@Column(name = "PLANSET_DRIVE_ID")
	private String plansetDriveId;

	@Column(name = "SAVE_COUNT")
	private Integer saveCount;

	@Column
	private Integer mondayId;

	@Column(length = 550)
	private String webSocketSession;

	@Column
	private String uploadCommentsPlanset1;
	@Column
	private String uploadCommentsPlanset2;
	@Column
	private String uploadCommentsPlanset3;
	@Column
	private String uploadCommentsPlanset4;
	@Column
	private String uploadCommentsPlanset5;

	// A.B 11-25-2022: CR-1259-MOD-002
	@Column
	private String archiveLink;
	@Column
	private String archiveStatus;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "archive_id")
    private ArchiveLogEntity archive;
}
