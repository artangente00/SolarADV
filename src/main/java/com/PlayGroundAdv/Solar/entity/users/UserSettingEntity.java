package com.PlayGroundAdv.Solar.entity.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "UserSettingEntity")
@Getter
@Setter
public class UserSettingEntity {
	
	@Id
	@SequenceGenerator(name="UserSetting_seq",
			           sequenceName="UserSetting_seq",
			           allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UserSetting_seq")  
	private Long id;
	
	@JoinColumn(name = "USER_ID")
	@ManyToOne
	private AuthentificationEntity userId;
	
//	********************************** User Access ***************************************	
	@Column(name="SOLAR_PERMIT")
	private Boolean solarPermit;
	
	@Column(name="SITE_SURVEY")
	private Boolean siteSurvey;
	
	@Column (name="HAS_SETTING_ACCESS")
	private Boolean hasSettingAccess;
	
	@Column (name="HAS_AHJ_MGT_ACCESS")
	private Boolean hasAhjMgtAccess;
	
	@Column (name="HAS_LIB_VERIF_ACCESS")
	private Boolean hasLibVerifAccess;
	
//	********************************** User Logo & Signature ***************************************	
	
	@Column(name="COMPANY_LOGO")
	private String companyLogoName;

	@Column(name="LOGO_CONFIRMED")
	private Boolean logoConfirmed;
	
	@Column(name="SIGNATURE")
	private String signature;
	
	@Column(name="SIGNATURE_CONFIRMED")
	private Boolean signatureConfirmed;
	
//	********************************** User Setting ***************************************	
	
	@Column (name="REMINDER")
	private String reminder = "6";
	
	@Column (name="MINIMUM_DC_GROUND_CONDUCTOR")
	private String minimumDCGroundCon;
	
	@Column (name="MINIMUM_DC_GROUND_CONDUCTOR_OTHER")
	private String minimumDCGroConOther;
	
	@Column (name="MINIMUM_AC_GROUND_CONDUCTOR")
	private String minimumACGroundCon;
	
	@Column (name="MINIMUM_AC_GROUND_CONDUCTOR_OTHER")
	private String minimumACGroConOther;
	
	@Column (name="USER_GROUND_RAIL_RACKING")
	private String userGroundRailRaking;
	
	@Column (name="USER_SIZE_OF_PIPE")
	private String userSizeOfPipe;
	
	@Column (name="USER_SIZE_OF_PIPE_OTHER")
	private String userSizeOfPipeOther;
	
	@Column (name="USER_THICKNESS_OF_PIPE")
	private String userThicknessOfPipe;
	
	@Column (name="USER_THICKNESS_OF_PIPE_OTHER")
	private String userThicknessOfPipeOther;
	
	@Column (name="USER_BRACED_UNBRACED")
	private String userBracedUnbraced;
	
	@Column (name="USER_FOOTING_DIAMETER")
	private String userFootingDiameter;
	
	@Column (name="USER_FOOTING_DIAMETER_OTHER")
	private String userFootingDiameterOther;
	
	@Column (name="PVM_MODEL_DEFAULT")
	private String pvmModelDefault;
	
	@Column (name="USER_ACDISCONNECT")
    private String userAcDisconnect;
	
	@Column (name="AMP_RATING")
    private String ampRating;
	
	@Column (name="FUSIBLE_NONFUSIBLE")
    private String fusibleNonFusible;
	
	@Column (name="NEMA_RATING")
    private String nemaRating;
	
	// A.B 03-14:  CR-2554-MOD-001
	@Column (name="USE_ROMEX_IN_ATTIC")
	private Boolean useRomexInAttic;
	
	// I.C 10-21:  CR-2972-MOD-001
	@Column (name="ATTIC_TEMPERATURE_ADDER")
	private String atticTemperatureAdder;
	
	// A.B 11-07:  CR-2982-MOD-001
	@Column (name="STANCHIONS_TYPE")
	private String stanchionsType;
	
	// S.B 14-04:  CR-3119-MOD-003
	@Column (name="NEUTRAL_CONDUCTOR_SRTING")
	private Boolean neutralConductorString;
	
	// S.B 14-04:  CR-3119-MOD-003
	@Column (name="STANCHIONS_CONDUCTOR_MICRO")
	private Boolean neutralConductorMicro;
	
	// A.B 09-16-2021:  CR-3403-MOD-001
	@Column
	private String conduitTypeForAttic;
	@Column
	private String conductorTypeForAtticRuns;
	
	// A.B 09-16-2021:  CR-3403-MOD-002
	@Column
	private String minDcConduitSize;
	@Column
	private String minAcConduitSize;
	
	// A.B 09-16-2021:  CR-3403-MOD-002
	@Column
	private Boolean runPvWireToGroundLevel;
	
	// A.B 10-13-2021:  CR-2228
	@Column
	private String minSizeConductorsFromMainServicePanel;
	
	@Column(length = 2250)
	private String pointOfConnectionNote;

	// A.B 03-17-2022:  CR-PM-793
	@Column
	private String microASystemSize;
	@Column
	private String stringASystemSize;
	
	// F.B 04-12-2022:  CR-PM-805-MOD-001
	@Column
	private Boolean wireTapSetting;
	
	// A.B 10-17-2022:  CR-1190-MOD-001
	@Column
	private Boolean acdNotRequired;
	@Column
	private Boolean pvmNotRequired;
	
	// A.B 11-25-2022:  CR-1259-MOD-001
	@Column
	private Boolean archiveAllowed;
	@Column
	private Integer archiveDelay;

}
