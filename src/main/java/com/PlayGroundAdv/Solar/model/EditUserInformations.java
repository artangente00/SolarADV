package com.PlayGroundAdv.Solar.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EditUserInformations {

	private Long id;
	private String contractorCode;
	private String firstName;
	private String lastName;
	private String country;
	private String company;
	private String email;
	private Boolean active;
	private String address;
	private String secondAddressLine;
	private String city;
	private String state;
	private String postalCode;
	private Long idRole;

//	*********** Contractor Informations Entity ************
	private String date;
	private Boolean sameMalingAddress;
	private String mailingAddress;
	private String secondMailingAddress;
	private String mailingCity;
	private String mailingState;
	private String mailingZipCode;
	private String contactFirstName;
	private String contactLastName;
	private String contactEmail;
	private String contactPhone;
	private String contactAddPhone;
	private String secondContactFirstName;
	private String secondContactLastName;
	private String secondContactEmail;
	private String secondContactPhone;
	private String secondContactAddPhone;
	private Boolean includeSecondContact;
	private String thirdContact;
	private String thirdContactEmail;
	private String thirdContactPhone;
	private String thirdContactAddPhone;
	private Boolean includeThirdContact;
	private String businessPhone;
	private String otherPhone;
	private String designBy;
	private String licenseNumber;
	private String licenseExpiration;
	private Boolean contractorLic;
	private String qualifyingIndividual;
	private String additionalQualifying;
	private Boolean includeSecondContactOnly;
	private Boolean includeSecondContactWhen;
	private Boolean includeThirdContactOnly;
	private Boolean includeThirdContactWhen;
	private String designByOther;
	private Boolean contractorLicC10;
	private Boolean contractorLicB;
	private String qualifyingIndividualOther;
	private String additionalQualifyingOther;
	private String contractorLicenceState;
	private Boolean isProjectAddInclud;
	private String lastNameContact;
	private String reminder;
	private String reminderOther;
	private String compPhoneNum;
	private String contactOptions;
	private String contactOptionsOther;
	private String minimumDCGroundCon;
	private String minimumDCGroConOther;
	private String minimumACGroundCon;
	private String minimumACGroConOther;
	private String userGroundRailRaking;
	private String userSizeOfPipe;
	private String userSizeOfPipeOther;
	private String userThicknessOfPipe;
	private String userThicknessOfPipeOther;
	private String userBracedUnbraced;
	private String userFootingDiameter;
	private String userFootingDiameterOther;
	private Boolean siteSurveyAccess;
	private Boolean permitAccess;
	private Boolean hasSettingAccess;
	private Boolean hasAhjMgtAccess;
	private String pvmModelDefault;
	private String userAcDisconnect;
	private String ampRating;
	private String fusibleNonFusible;
	private String nemaRating;
	// A.B 03-14: CR-2554-MOD-001
	private Boolean useRomexInAttic;
	// I.C 10-21: CR-2972-MOD-001
	private String atticTemperatureAdder;
	private String stanchionsType;
	private Boolean neutralConductorString;
	private Boolean neutralConductorMicro;

	// A.B 10-13-2021: CR-2228
	private String minSizeConductorsFromMainServicePanel;
	private String pointOfConnectionNote;

	// A.B 09-16-2021: CR-3403-MOD-001
//	private String conduitTypeForAttic;
//	private String conductorTypeForAtticRuns;
	// A.B 09-16-2021: CR-3403-MOD-002
//	private String minDcConduitSize;
//	private String minAcConduitSize;
	// A.B 09-16-2021: CR-3403-MOD-002
//	private Boolean runPvWireToGroundLevel;

	private Boolean hasLibVerifAccess;

	// A.B 03-17-2022: CR-PM-793
	private String microASystemSize;
	private String stringASystemSize;

	// A.B 03-17-2022: CR-PM-793
	private List<String> customUploads;

	// F.B 04-11-2022: CR-817-MOD-001
	private String phoneNumber;

	// F.B 04-12-2022: CR-PM-805-MOD-001
	private Boolean wireTapSetting;

	// A.B 10-17-2022: CR-1190-MOD-001
	private Boolean acdNotRequired;
	private Boolean pvmNotRequired;

	// A.B 11-25-2022: CR-1259-MOD-001
	private Boolean archiveAllowed;
	private Integer archiveDelay;

}
