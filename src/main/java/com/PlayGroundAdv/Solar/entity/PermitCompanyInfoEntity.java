package com.PlayGroundAdv.Solar.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "PermitCompanyInfoEntity")
public class PermitCompanyInfoEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	

	@Id
	@SequenceGenerator(name="hibernate_sequence5", sequenceName = "hibernate_sequence5", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence5")  
	private Long id;
	
	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity permitEntity;
	
	@Column(name="ACCOUNT_NUMBER")
	private String accountNumber;
	
	@Column(name="SERVICE_AGREEMENT")
	private String serviceAgreement;
	
	@Column(name="EXISTING_RATE")
	private String existingRate;
	
	@Column(name="NEW_RATE")
	private String newRate;
	
	@Column(name="COST_PAID")
	private String costPaid;
	
	@Column(name="CLAIMED_FEDERAL")
	private String claimedFederal;
	
	@Column(name="NAME_DEVELOPER")
	private String nameDeveloper;
	
	@Column(name="CHECK_APPLY")
	private String checkApply;
	
	@Column(name="KWH_USAGE")
	private String kwhUsage;
	
	@Column(name="AUTHORIZATING_ADVANCED")
	private Boolean authorizatingAdvanced;
	
	@Column(name="CONTACT_HOMEOWNER")
	private String contactHomeowner;
	
	@Column(name="SCIR")
	private String scir;
	
	@Column(name="SYSTEM_OWNER")
	private String systemOwner;
	
	@Column(name="PACE_FINANCED")
	private Boolean paceFinanced;
	
	@Column(name="METER_ACCESS")
	private String meterAccess;
	
	@Column(name="PLANNED_ANNUAL")
	private Integer plannedAnnual;
	
	@Column(name="NEW_SERVICE")
	private Boolean newService;

	@Column(name="NEW_SUBDIVISION")
	private Boolean newSubdivition;
	
	@Column(name="NON_PROFILE_STATUS")
	private Boolean nonProfileStatus;
	
	
	@Column(name="SYSTEM_MEET_DIH")
	private Boolean systemMeetDIH;
	
	@Column(name="CONTACT_TYPE")
	private String contactType;
	
	@Column(name="METER_DISCO")
	private Boolean meterDisco;
	
	@Column(name="METER_BUILING")
	private Boolean meterBuilding;
	
	@Column(name="UNDERTRAINED_ANIMAL")
	private Boolean undertrainedAnimal;
	
	@Column(name="OTHER_SYSTEM")
	private Boolean otherSystem;
	
	@Column(name="OTHER_SYSTEM_TEXT")
	private String otherSystemText;
	
	@Column(name="J_BOX_USED_BETWEEN")
	private Boolean jBoxUsedBetween ;
	
	@Column(name="DEVELOPERS_NAME")
	private String developersName;
	
	@Column(name="DEVELOPMENT_NAME")
	private String developmentName;
	
	@Column(name="INTERCONNECTION_TYPE")
	private String InterconnectionType;
	
	@Column(name="PEACE_FINANCED_BY_ENTITY")
	private String peaceFinancedByEntity;
	
	@Column(name="NAME_PARTY_RECEIV_DATA")
	private String namePartyReceivData;
	
	
	@Column(name="TEXT_OTHER_RATE")
	private String textOtherRate; 
	
	
	@Column(name="TEXT_OTHER_NEW_RATE")
	private String textOtherNewRate;
	
	@Column(name="CUSTOMER_PARTICIPATED")
	private String customerParticipated;

	
	@Column(name="PEACE_FINANCED_BY_ENTITY_OTHER")
	private String peaceFinancedByEntityOther;

	//CR_003
	@Column(name="TYPE_CUSTOMER")
	private String typeCustomer;
	
	@Column(name="TYPE_CUSTOMER_OTHER")
	private String typeCustomerOther;
	
	@Column(name="TYPE_CUSTOMER_OTHER_TEXT")
	private String typeCustomerOtherText;
	
	@Column(name="NEW_RATE_COMMERCIAL")
	private String newRateCommercial; 
	
	@Column(name="OTHER_NEW_RATE_COMMERCIAL")
	private String otherNewRateCommercial;
	
	@Column(name="SNEM_APPLICATION")
	private String snemApplication;
	
	@Column(name="THIS_IS_NEW_SERVICE")
	private String thisIsNewService;
	
	@Column(name="DEVELOPERRS_NAME")
	private String developerrsNam;
	
	@Column(name="DEVELOPERS_NAME_SECOND")
	private String developersNameSecond;
	
	@Column(name="THE_PROJECT_IS_LOCATED")
	private String theProjectIsLocated;
	
	@Column(name="DEVELOPEM_NAME")
	private String developemName;
	
	@Column(name="PROJECT_IS")
	private String projectIs;
	
	@Column(name="OTHER_PROJECT_IS")
	private String otherProjectIs;
	
	@Column(name="PROJECT_WAS_PACE")
	private String projectWasPace;
	
	@Column(name="CHOOSE_PACE_FINANCED")
	private String choosePaceFinanced;
	
	@Column(name="TEXT_OTHER_CHOOSE_PACE")
	private String textOtherChoosePace;
	
	@Column(name="ELECTRICC_VEHICULE1")
	private String electriccvehichule1;
	
	@Column(name="ELECTRIC_VEHICULE2")
	private Boolean electricvehichule2;
	
	@Column(name="ELECTRIC_VEHICULE3")
	private Boolean electricvehichule3;
	
	@Column(name="ELECTRIC_VEHICULE4")
	private Boolean electricvehichule4;
	
	@Column(name="ELECTRIC_VEHICULE1_OTHER")
	private Boolean electricvehichuleOther;
	
	@Column(name="OTHER_ELECTRIC_VE")
	private String otherElectricVe;
	
	@Column(name="APPLICATION_TYPE")
	private String applicationType;
	
	@Column(name="THE_AC_DISCONNECT_IS_MORE_THAN")
	private String theAcDisconnectIsMoreThan;
	
	@Column(name="COVERAGE_AMOUNT")
	private String coverageamount;
	
	@Column(name="INSURING_COMPANY_NAME")
	private String insuringcompanyName;
	
	@Column(name="EXPECTED_DATE")
	private String expectedDate;
	
	@Column(name="ONE_OR_MORE_OF_THE_ASS")
	private Boolean oneOrMoreOfTheAss;
	
	@Column(name="THE_LOCAL_PERMITTING")
	private Boolean theLocalPermitting;
	
	@Column(name="REQUEST_PGTO_SHUTDOWN")
	private Boolean requestPGToshutDown;
	
	@Column(name="REQUEST_PGAETO_INSTAL_NEWS")
	private Boolean RequestPGaEToInstalNewS;
	
	@Column(name="NAME_OF_DEVELOPERS_LEASE")
	private String nameOfDevelopersLease;
	
	@Column(name="CLAMED_FEDERAL_INCOME_TAX")
	private Integer clamedfederalIncomeTax;
	
	@Column(name="WHICH_PROGRAM")
	private String whichProgram;
	
	@Column(name="WHAT_RULE_OR_RULES")
	private String whatRuleOrRules;
	
	@Column(name="DE_ENERGIZINF_OFTHE_SERVICE")
	private Boolean deEnergizingOfTheService;
	
	@Column(name="WHAT_DAY_OF_THE_WEEK")
	private String whatDayOfTheWeek;
	
	@Column(name="WHAT_TIME_OF_DAY")
	private String whatTimeOfDay;
	
	@Column(name="WILL_YOU_NEEDTO_OBTAIN")
	private String willYouNeedToObtain;
	
	@Column(name="DESCRIBE_THE_POINT_INT")
	private String describeThePointInt;
	
	@Column(name="THE_SCIR_OF_THE_MAIN")
	private Integer theScirOfTheMain;
	
	@Column(name="POLICY")
	private String policy;
	
	@Column(name="HOW_MANY_HOURS")
	private String howManyHours;
	
	@Column(name="DEVELOPERRRS_NAME")
	private String developerrrsName;
	
	@Column(name="PGAE_PERSONNEL_WILL_NEED")
	private String PGaEPersonnelWilleNeed;
	
	@Column(name="MAY_OUR_REPRESENTAUVES_CONTACT")
	private String mayOurRepresentativesContact;
	
	@Column(name="I_AUTHORIZE_ADVANCED")
	private String iAuthorizeAdvanced;
	
	@Column(name="METERORACDISCONNECTINBUILDING")
	private Boolean meterOrACDisconnectInBuilding;
	
	@Column(name="UNRESTRAINEDANIMAL")
	private Boolean unrestrainedAnimal;
	
	@Column(name="CHECKTHEAPPLICABLEOTHER")
	private Boolean checkTheApplicableOther;
	
	@Column(name="CHECKAPPLICABLEBOXESOTHER")
	private String checkApplicableBoxesOther;
	
	@Column(name="CHECK_If_THE_PROPOSED_SYSTEM_PRODUCE")
	private Boolean checkIfTheProposedSystemProduce;
	
	@Column(name="PERFORMANCE_MONIT_AND_REP")
	private  String performanceMonitAndRep;
	
	@Column(name="AN_EXISTING_SOLAR_WIND")
	private  String anExistingSolarOrWind;
	
	@Column(name="CLAMED_FEDERAL_INCOME_TAX_STR")
	private  String clamedfederalIncomeTaxStr;
	
	@Column(name="LIST_ANY_VARIATIONS")
	private  String listAnyVariations;
	
	@Column(name="TEXT_OTHER_CONTRACT_TYPE")
	private  String textOtherContractType;
	
	@Column(name="NEW_RATE_OTHERS")
	private  String newRateOthers;
	
	@Column(name="NEW_RATE_OTHERS_TEXT")
	private  String newRateOthersText;
	
	@Column(name="uploadCommentsUtility")
	private String uploadCommentsUtility;
	

	@Column(name="uploadFileExisting")
	private String uploadFileExisting;
	
	@Column(name="uploadFileJustification")
	private String uploadFileJustification;
	
	@Column(name="uploadFileListAdreess")
	private String uploadFileListAdreess;
	
	@Column(name="uploadFileSwitchgear")
	private String uploadFileSwitchgear;
	
	@Column(name="uploadFileInterconnection")
	private String uploadFileInterconnection;
	
	
	@Column(name="OPENED")
	private Boolean opened;
	
	@JoinColumn(name = "OPENED_BY")
	@ManyToOne
	private AuthentificationEntity openedBy;
	
	@Column(name="HAS_CLOSE_REQUEST")
	private Boolean hasCloseRequest;
	
	@Column(name="HAS_CLOSE_REQUEST_BY")
	private String hasCloseRequestby;
	
	@Column(length = 550)
	private String webSocketSession;

	@Column(name="IS_CHECKING")
	private Calendar isChecking;
	

	@Column(name="ALL_PAGES_FILE")
	private  byte[] allPagesFile;
	
	
}
