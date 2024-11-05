package com.PlayGroundAdv.Solar.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;

@Entity
@Table(name = "PermitArraysEntity")
public class PermitArraysEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "hibernate_sequence4", sequenceName = "hibernate_sequence4", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence4")
	private Long id;

	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity permitEntity;

	@Column(name = "SYSTEM_TYPE")
	private String systemType;

	@Column(name = "REQUEST_QUOTE")
	private Boolean RequestQuote;

	@Column(name = "DEVICE_TO_INCORPORATE")
	private String deviceToIncorporate;

	
	@Column(name = "PV_MODULE_MODEL")//C.I 01-15 to delete
	private String pvModuleModEl;
	
	@JoinColumn(name = "PV_MODULE")
	@ManyToOne 
	private  Cmodulev2 pvModule;
	
	@JoinColumn(name = "first_Inverter")
	@ManyToOne
	private Inverters firstInverter;
	
	@JoinColumn(name = "SECOND_INVERTER")
	@ManyToOne
	private Inverters secondInverter;
	
	@JoinColumn(name = "THIRD_INVERTER")
	@ManyToOne
	private Inverters thirdInverter;
	
	@JoinColumn(name = "FOURTH_INVERTER")
	@ManyToOne
	private Inverters fourthInverter;
	
	@JoinColumn(name = "FIFTH_INVERTER")
	@ManyToOne
	private Inverters fifthInverter;
	
	@JoinColumn(name = "MICRO_INVERTER_ENTITY")
	@ManyToOne
	private Inverters microInverterEntity;

	@Column(name = "INVERT_MODEL")
	private String inverterModel;//A.B 01-14 to delete

	@Column(name = "STRING_ONE")
	private String stringOne;

	@Column(name = "STRING_TWO")
	private String stringTwo;

	@Column(name = "STRING_THREE")
	private String stringThree;

	@Column(name = "STRING_FOUR")
	private String stringFour;

	@Column(name = "STRING_FIVE")
	private String stringFive;

	@Column(name = "SECOND_INVERT_MODEL")
	private String secondInverterModel;//A.B 01-14 to delete

	@Column(name = "SECOND_STRING_ONE")
	private String secondStringOne;

	@Column(name = "SECOND_STRING_TWO")
	private String secondStringTwo;

	@Column(name = "SECOND_STRING_THREE")
	private String secondStringThree;

	@Column(name = "SECOND_STRING_FOUR")
	private String secondStringFour;

	@Column(name = "SECOND_STRING_FIVE")
	private String secondStringFive;

	@Column(name = "ROOF_MOUNTED")
	private Boolean roofMounted;

	@Column(name = "GROUND_MOUNTED")
	private Boolean GroundMounted;

	@Column(name = "CARPORT_MOUNTED")
	private Boolean carportMounted;

	@Column(name = "OTHER_MOUNTED")
	private Boolean otherMounted;

	@Column(name = "FRONTANDBACK")
	private Boolean frontAndBack;

	@Column(name = "CATELEVER")
	private Boolean cantelever;
	
	@Column(name = "ATTACHED_TO_EXTWAL")
	private Boolean attachedToExtWal;

	@Column(name = "ATTACHED_TO_FASCIA")
	private Boolean attachedToFascia;
	
	@Column(name = "ATTACHED_TO_SKYLIFTS")
	private Boolean attachedToSkylifts;

	@Column(name = "FREE_STANDING")
	private Boolean freeStanding;

	@Column(name = "TEXT_OTHER")
	private String textOther;

	@Column(name = "BATTERY_MANUFACTURER_TORJAN")
	private Boolean BatteryManufacturerTrojan;

	@Column(name = "BATTERY_MANUFACTURER_MMK")
	private Boolean BatteryManufacturerMMK;

	@Column(name = "BATTERY_MANUFACTURER_UPG")
	private Boolean BatteryManufacturerUPG;

	@Column(name = "BATTERY_MANUFACTURER_ROLLS")
	private Boolean BatteryManufacturerRolls;

	@Column(name = "BATTERY_MANUFACTURER_CROWN")
	private Boolean BatteryManufacturerCrown;

	@Column(name = "BATTERY_MANUFACTURER_TESLA")
	private Boolean BatteryManufacturerTesla;

	@Column(name = "BATTERY_MANUFACTURER_OUTBACK")
	private Boolean BatteryManufacturerOutback;

	@Column(name = "BATTERY_MANUFACTURER_FULLRIVER")
	private Boolean BatteryManufacturerFullriver;

	@Column(name = "BATTERY_MANUFACTURER_CONCORD")
	private Boolean BatteryManufacturerConcord;

	@Column(name = "BATTERY_MANUFACTURER_OTHER")
	private Boolean BatteryManufacturerOther;

	@Column(name = "TEXT_BATTERY_OTHER")
	private String textBatteryOther;

	@Column(name = "QTE_OF_BATTERY")
	private String qteOfBattery;

	@JoinColumn(name = "SYSTEM_OPTIMIZER_MODEL")
	@ManyToOne
	private DCOptimizerEntity systemOptimizerModel;
	
	@Column(name = "SYSTEM_OPTIMIZER_MODEL_MANUFACTEUR")
	private String systemOptimizerModelManufacturer; //M.A 02-18 to delete


	/************* SR 002 ********************************************/

	@Column(name = "THIRD_INVERTER_MODEL")
	private String thirdInverterModel;//A.B 01-14 to delete

	@Column(name = "THIRD_STRING_ONE")
	private Integer thirdStringOne;

	@Column(name = "ITHIRD_STRING_TWO")
	private Integer thirdStringTwo;

	@Column(name = "THIRD_STRING_THREE")
	private Integer thirdStringThree;

	@Column(name = "THIRD_STRING_FOUR")
	private Integer thirdStringFour;

	@Column(name = "THIRD_STRING_FIVE")
	private Integer thirdStringFive;

	@Column(name = "FOURTH_INVERTER_MODEL")
	private String fourthInverterModel;//A.B 01-14 to delete

	@Column(name = "FOURTH_STRING_ONE")
	private Integer fourthStringOne;

	@Column(name = "FOURTH_STRING_TWO")
	private Integer fourthStringTwo;

	@Column(name = "FOURTH_STRING_THREE")
	private Integer fourthStringThree;

	@Column(name = "FOURTH_STRING_FOUR")
	private Integer fourthStringFour;

	@Column(name = "FOURTH_STRING_FIVE")
	private Integer fourthStringFive;

	@Column(name = "FIFTH_INVERTER_MODEL")
	private String fifthInverterModel;//A.B 01-14 to delete

	@Column(name = "FIFTH_STRING_ONE")
	private Integer fifthStringOne;

	@Column(name = "FIFTH_STRING_TWO")
	private Integer fifthStringTwo;

	@Column(name = "FIFTH_STRING_THREE")
	private Integer fifthStringThree;

	@Column(name = "FIFTH_STRING_FOUR")
	private Integer fifthStringFour;

	@Column(name = "FIFTH_STRING_FIVE")
	private Integer fifthStringFive;

	@Column(name = "MICRO_INVERTER_MANUFACTURER")
	private String microInverterManufacturer;

	@Column(name = "MICRO_INVERTER_MODEL")
	private String microInverterModel;

	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_ONE")
	private String numberModulesACCircuitOne;

	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_TWO")
	private String numberModulesACCircuitTwo;

	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_THREE")
	private String numberModulesACCircuitThree;

	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_FOUR")
	private String numberModulesACCircuitFour;

	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_FIVE")
	private String numberModulesACCircuitFive;

	/************* END SR 002 *************/

	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_SIX")
	private String numberModulesACCircuitSix;

	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_SEVEN")
	private String numberModulesACCircuitSeven;

	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_EIGHT")
	private String numberModulesACCircuitEight;

	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_NINE")
	private String numberModulesACCircuitNine;

	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_TEN")
	private String numberModulesACCircuitTen;

	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_ELEVEN")
	private String numberModulesACCircuitEleven;

	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_TWELEVE")
	private String numberModulesACCircuitTweleve;
	
	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_THIRTEEN")
	private String numberModulesACCircuitThirteen;
	
	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_FOURTEEN")
	private String numberModulesACCircuitFourteen;
	
	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_FIFTEEN")
	private String numberModulesACCircuitFifteen;
	
	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_SIXTEEN")
	private String numberModulesACCircuitSixteen;
	
	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_SEVENTEEN")
	private String numberModulesACCircuitSeventeen;
	
	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_EIGHTTEEN")
	private String numberModulesACCircuitEightteen;
	
	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_NINETEEN")
	private String numberModulesACCircuitNineteen;
	
	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_TWENTY")
	private String numberModulesACCircuitTwenty;
	
	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_TWENTYONE")
	private String numberModulesACCircuitTwentyOne;
	
	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_TWENTYTWO")
	private String numberModulesACCircuitTwentyTwo;
	
	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_TWENTYTHREE")
	private String numberModulesACCircuitTwentyThree;
	
	@Column(name = "NUMBER_MODULES_AC_CIRCUIT_TWENTYFOUR")
	private String numberModulesACCircuitTwentyFour;

	@Column(name = "OCPD_ONE")
	private String ocpdOne;

	@Column(name = "OCPD_TWO")
	private String ocpdTwo;

	@Column(name = "OCPD_THREE")
	private String ocpdThree;

	@Column(name = "OCPD_FOUR")
	private String ocpdFour;

	@Column(name = "OCPD_FIVE")
	private String ocpdFive;

	@Column(name = "OCPD_SIX")
	private String ocpdSix;

	@Column(name = "OCPD_SEVEN")
	private String ocpdSeven;

	@Column(name = "OCPD_EIGHT")
	private String ocpdEight;

	@Column(name = "OCPD_NINE")
	private String ocpdNine;

	@Column(name = "OCPD_TEN")
	private String ocpdTen;

	@Column(name = "OCPD_ELEVEN")
	private String ocpdEleven;

	@Column(name = "OCPD_TWELVE")
	private String ocpdTwelve;
	
	@Column(name = "OCPD_THIRTEEN")
	private String ocpdThirteen;
	
	@Column(name = "OCPD_FOURTEEN")
	private String ocpdFourteen;
	
	@Column(name = "OCPD_FIFTEEN")
	private String ocpdFifteen;
	
	@Column(name = "OCPD_SIXTEEN")
	private String ocpdSixteen;
	
	@Column(name = "OCPD_SEVENTEEN")
	private String ocpdSeventeen;
	
	@Column(name = "OCPD_EIGHTEEN")
	private String ocpdEightteen;
	
	@Column(name = "OCPD_NINETEEN")
	private String ocpdNineteen;
	
	@Column(name = "OCPD_TWENTY")
	private String ocpdTwenty;
	
	@Column(name = "OCPD_TWENTYONE")
	private String ocpdTwentyOne;
	
	@Column(name = "OCPD_TWENTYTWO")
	private String ocpdTwentyTwo;
	
	@Column(name = "OCPD_TWENTYTHREE")
	private String ocpdTwentyThree;
	
	@Column(name = "OCPD_TWENTYFOUR")
	private String ocpdTwentyFour;
	
	@Column(name = "uploadCommentsLayout")
	private String uploadCommentsLayout;
	
	@Column(name = "uploadCommentsAddInfo")
	private String uploadCommentsAddInfo;

	@Column(name = "inverterLocation")
	private String inverterLocation;
	
	@Column(name = "inverterLocationOther")
	private String inverterLocationOther;
	
	@Column(name = "inverterSameLocation")
	private String inverterSameLocation;
    
	//CR-2222   Add UP TO 12 String Inputs for Number of Modules in Each String 
	@Column(name = "STRING_SIX")
	private Integer stringSix ;
	
	@Column(name = "STRING_SEVEN")
    private Integer stringSeven ;
	
	@Column(name = "STRING_EIGHT")
    private Integer stringEight ;
	
	@Column(name = "STRING_NINE")
    private Integer stringNine ;
	
	@Column(name = "STRIN_GTEN")
    private Integer stringTen ;
	
	@Column(name = "STRING_ELEVEN")
    private Integer stringEleven ;
	
	@Column(name = "STRING_TWELVE")
    private Integer stringTwelve ;
	
	@Column(name = "SECOND_STRIN_GSIX")
    private Integer secondStringSix ;
	
	@Column(name = "SECOND_STRING_SEVEN")
    private Integer secondStringSeven ;
	
	@Column(name = "SECOND_STRING_EIGHT")
    private Integer secondStringEight ;
	
	@Column(name = "SECOND_STRING_NINE")
    private Integer secondStringNine ;
	
	@Column(name = "SECOND_STRING_TEN")
    private Integer secondStringTen ;
	
	@Column(name = "SECOND_STRING_ELEVEN")
    private Integer secondStringEleven ;
	
	@Column(name = "SECOND_STRING_TWELVE")
    private Integer secondStringTwelve ;
	
	@Column(name = "THIRD_STRING_SIX")
    private Integer thirdStringSix ;
	
	@Column(name = "THIRD_STRING_SEVEN")
    private Integer thirdStringSeven ;
	
	@Column(name = "THIRD_STRING_EIGHT")
    private Integer thirdStringEight ;
	
	@Column(name = "THIRD_STRING_NINE")
    private Integer thirdStringNine ;
	
	@Column(name = "THIRD_STRING_TEN")
    private Integer thirdStringTen ;
	
	@Column(name = "THIRD_STRING_ELEVEN")
    private Integer thirdStringEleven ;
	
	@Column(name = "THIRD_STRING_TWELVE")
    private Integer thirdStringTwelve ;
	
	@Column(name = "FOURTH_STRING_SIX")
    private Integer fourthStringSix ;
	
	@Column(name = "FOURTH_STRING_SEVEN")
    private Integer fourthStringSeven ;
	
	@Column(name = "FOURTH_STRING_EIGHT")
    private Integer fourthStringEight ;
	
	@Column(name = "FOURTH_STRING_NINE")
    private Integer fourthStringNine ;
	
	@Column(name = "FOURTH_STRING_TEN")
    private Integer fourthStringTen ;
	
	@Column(name = "FOURTH_STRING_ELEVEN")
    private Integer fourthStringEleven ;
	
	@Column(name = "FOURTH_STRING_TWELVE")
    private Integer fourthStringTwelve ;
	
	@Column(name = "FIFTH_STRING_SIX")
    private Integer fifthStringSix ;
	
	@Column(name = "FIFTH_STRING_SEVEN")
    private Integer fifthStringSeven ;
	
	@Column(name = "FIFTH_STRING_EIGHT")
    private Integer fifthStringEight ;
	
	@Column(name = "FIFTH_STRING_NINE")
    private Integer fifthStringNine ;
	
	@Column(name = "FIFTH_STRING_TEN")
    private Integer fifthStringTen ;
	
	@Column(name = "FIFTH_STRING_ELEVEN")
    private Integer fifthStringEleven ;
	
	@Column(name = "FIFTH_STRING_TWELVE")
    private Integer fifthStringTwelve ;
	
	@Column(name = "MICRO_INVERTER")
	private String microInverter;
	
	@Column(name = "ROOF_OR_OPEN_FRAME")
	private String roofOrOpenFrame;

	@Column(name = "CIRCUIT_UNDER_GROUND")
	private Boolean circuitUnderGround;
	
	@Column(name = "INVERTER_INSTALLED_ON_ROOF")
	private Boolean inverterInstalledOnRoof;
	
	@Column(name = "ENTER_ANY_TRANSFORMER")
	private String enteranyTransformer;
	
	@Column(name = "PATIO_MOUNTED")
	private Boolean patioMounted;
	
	public Boolean getInverterInstalledOnRoof() {
		return inverterInstalledOnRoof;
	}

	public void setInverterInstalledOnRoof(Boolean inverterInstalledOnRoof) {
		this.inverterInstalledOnRoof = inverterInstalledOnRoof;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PermitEntity getPermitEntity() {
		return permitEntity;
	}

	public void setPermitEntity(PermitEntity permitEntity) {
		this.permitEntity = permitEntity;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public Boolean getRequestQuote() {
		return RequestQuote;
	}

	public void setRequestQuote(Boolean requestQuote) {
		RequestQuote = requestQuote;
	}

	public String getDeviceToIncorporate() {
		return deviceToIncorporate;
	}

	public void setDeviceToIncorporate(String deviceToIncorporate) {
		this.deviceToIncorporate = deviceToIncorporate;
	}

	public String getPvModuleModEl() {
		return pvModuleModEl;
	}

	public void setPvModuleModEl(String pvModuleModEl) {
		this.pvModuleModEl = pvModuleModEl;
	}

	public String getInverterModel() {
		return inverterModel;
	}

	public void setInverterModel(String inverterModel) {
		this.inverterModel = inverterModel;
	}

	public String getStringOne() {
		return stringOne;
	}

	public void setStringOne(String stringOne) {
		this.stringOne = stringOne;
	}

	public String getStringTwo() {
		return stringTwo;
	}

	public void setStringTwo(String stringTwo) {
		this.stringTwo = stringTwo;
	}

	public String getStringThree() {
		return stringThree;
	}

	public void setStringThree(String stringThree) {
		this.stringThree = stringThree;
	}

	public String getStringFour() {
		return stringFour;
	}

	public void setStringFour(String stringFour) {
		this.stringFour = stringFour;
	}

	public String getStringFive() {
		return stringFive;
	}

	public void setStringFive(String stringFive) {
		this.stringFive = stringFive;
	}

	public String getSecondInverterModel() {
		return secondInverterModel;
	}

	public void setSecondInverterModel(String secondInverterModel) {
		this.secondInverterModel = secondInverterModel;
	}

	public String getSecondStringOne() {
		return secondStringOne;
	}

	public void setSecondStringOne(String secondStringOne) {
		this.secondStringOne = secondStringOne;
	}

	public String getSecondStringTwo() {
		return secondStringTwo;
	}

	public void setSecondStringTwo(String secondStringTwo) {
		this.secondStringTwo = secondStringTwo;
	}

	public String getSecondStringThree() {
		return secondStringThree;
	}

	public void setSecondStringThree(String secondStringThree) {
		this.secondStringThree = secondStringThree;
	}

	public String getSecondStringFour() {
		return secondStringFour;
	}

	public void setSecondStringFour(String secondStringFour) {
		this.secondStringFour = secondStringFour;
	}

	public String getSecondStringFive() {
		return secondStringFive;
	}

	public void setSecondStringFive(String secondStringFive) {
		this.secondStringFive = secondStringFive;
	}

	public Boolean getRoofMounted() {
		return roofMounted;
	}

	public void setRoofMounted(Boolean roofMounted) {
		this.roofMounted = roofMounted;
	}

	public Boolean getGroundMounted() {
		return GroundMounted;
	}

	public void setGroundMounted(Boolean groundMounted) {
		GroundMounted = groundMounted;
	}

	public Boolean getCarportMounted() {
		return carportMounted;
	}

	public void setCarportMounted(Boolean carportMounted) {
		this.carportMounted = carportMounted;
	}

	public Boolean getOtherMounted() {
		return otherMounted;
	}

	public void setOtherMounted(Boolean otherMounted) {
		this.otherMounted = otherMounted;
	}

	public String getTextOther() {
		return textOther;
	}

	public void setTextOther(String textOther) {
		this.textOther = textOther;
	}

	public Boolean getBatteryManufacturerTrojan() {
		return BatteryManufacturerTrojan;
	}

	public void setBatteryManufacturerTrojan(Boolean batteryManufacturerTrojan) {
		BatteryManufacturerTrojan = batteryManufacturerTrojan;
	}

	public Boolean getBatteryManufacturerMMK() {
		return BatteryManufacturerMMK;
	}

	public void setBatteryManufacturerMMK(Boolean batteryManufacturerMMK) {
		BatteryManufacturerMMK = batteryManufacturerMMK;
	}

	public Boolean getBatteryManufacturerUPG() {
		return BatteryManufacturerUPG;
	}

	public void setBatteryManufacturerUPG(Boolean batteryManufacturerUPG) {
		BatteryManufacturerUPG = batteryManufacturerUPG;
	}

	public Boolean getBatteryManufacturerRolls() {
		return BatteryManufacturerRolls;
	}

	public void setBatteryManufacturerRolls(Boolean batteryManufacturerRolls) {
		BatteryManufacturerRolls = batteryManufacturerRolls;
	}

	public Boolean getBatteryManufacturerCrown() {
		return BatteryManufacturerCrown;
	}

	public void setBatteryManufacturerCrown(Boolean batteryManufacturerCrown) {
		BatteryManufacturerCrown = batteryManufacturerCrown;
	}

	public Boolean getBatteryManufacturerTesla() {
		return BatteryManufacturerTesla;
	}

	public void setBatteryManufacturerTesla(Boolean batteryManufacturerTesla) {
		BatteryManufacturerTesla = batteryManufacturerTesla;
	}

	public Boolean getBatteryManufacturerOutback() {
		return BatteryManufacturerOutback;
	}

	public void setBatteryManufacturerOutback(Boolean batteryManufacturerOutback) {
		BatteryManufacturerOutback = batteryManufacturerOutback;
	}

	public Boolean getBatteryManufacturerFullriver() {
		return BatteryManufacturerFullriver;
	}

	public void setBatteryManufacturerFullriver(Boolean batteryManufacturerFullriver) {
		BatteryManufacturerFullriver = batteryManufacturerFullriver;
	}

	public Boolean getBatteryManufacturerConcord() {
		return BatteryManufacturerConcord;
	}

	public void setBatteryManufacturerConcord(Boolean batteryManufacturerConcord) {
		BatteryManufacturerConcord = batteryManufacturerConcord;
	}

	public Boolean getBatteryManufacturerOther() {
		return BatteryManufacturerOther;
	}

	public void setBatteryManufacturerOther(Boolean batteryManufacturerOther) {
		BatteryManufacturerOther = batteryManufacturerOther;
	}

	public String getTextBatteryOther() {
		return textBatteryOther;
	}

	public void setTextBatteryOther(String textBatteryOther) {
		this.textBatteryOther = textBatteryOther;
	}

	public String getQteOfBattery() {
		return qteOfBattery;
	}

	public void setQteOfBattery(String qteOfBattery) {
		this.qteOfBattery = qteOfBattery;
	}

	public DCOptimizerEntity getSystemOptimizerModel() {
		return systemOptimizerModel;
	}

	public void setSystemOptimizerModel(DCOptimizerEntity systemOptimizerModel) {
		this.systemOptimizerModel = systemOptimizerModel;
	}

	public String getThirdInverterModel() {
		return thirdInverterModel;
	}

	public void setThirdInverterModel(String thirdInverterModel) {
		this.thirdInverterModel = thirdInverterModel;
	}

	public Integer getThirdStringOne() {
		return thirdStringOne;
	}

	public void setThirdStringOne(Integer thirdStringOne) {
		this.thirdStringOne = thirdStringOne;
	}

	public Integer getThirdStringTwo() {
		return thirdStringTwo;
	}

	public void setThirdStringTwo(Integer thirdStringTwo) {
		this.thirdStringTwo = thirdStringTwo;
	}

	public Integer getThirdStringThree() {
		return thirdStringThree;
	}

	public void setThirdStringThree(Integer thirdStringThree) {
		this.thirdStringThree = thirdStringThree;
	}

	public Integer getThirdStringFour() {
		return thirdStringFour;
	}

	public void setThirdStringFour(Integer thirdStringFour) {
		this.thirdStringFour = thirdStringFour;
	}

	public Integer getThirdStringFive() {
		return thirdStringFive;
	}

	public void setThirdStringFive(Integer thirdStringFive) {
		this.thirdStringFive = thirdStringFive;
	}

	public String getFourthInverterModel() {
		return fourthInverterModel;
	}

	public void setFourthInverterModel(String fourthInverterModel) {
		this.fourthInverterModel = fourthInverterModel;
	}

	public Integer getFourthStringOne() {
		return fourthStringOne;
	}

	public void setFourthStringOne(Integer fourthStringOne) {
		this.fourthStringOne = fourthStringOne;
	}

	public Integer getFourthStringTwo() {
		return fourthStringTwo;
	}

	public void setFourthStringTwo(Integer fourthStringTwo) {
		this.fourthStringTwo = fourthStringTwo;
	}

	public Integer getFourthStringThree() {
		return fourthStringThree;
	}

	public void setFourthStringThree(Integer fourthStringThree) {
		this.fourthStringThree = fourthStringThree;
	}

	public Integer getFourthStringFour() {
		return fourthStringFour;
	}

	public void setFourthStringFour(Integer fourthStringFour) {
		this.fourthStringFour = fourthStringFour;
	}

	public Integer getFourthStringFive() {
		return fourthStringFive;
	}

	public void setFourthStringFive(Integer fourthStringFive) {
		this.fourthStringFive = fourthStringFive;
	}

	public String getFifthInverterModel() {
		return fifthInverterModel;
	}

	public void setFifthInverterModel(String fifthInverterModel) {
		this.fifthInverterModel = fifthInverterModel;
	}

	public Integer getFifthStringOne() {
		return fifthStringOne;
	}

	public void setFifthStringOne(Integer fifthStringOne) {
		this.fifthStringOne = fifthStringOne;
	}

	public Integer getFifthStringTwo() {
		return fifthStringTwo;
	}

	public void setFifthStringTwo(Integer fifthStringTwo) {
		this.fifthStringTwo = fifthStringTwo;
	}

	public Integer getFifthStringThree() {
		return fifthStringThree;
	}

	public void setFifthStringThree(Integer fifthStringThree) {
		this.fifthStringThree = fifthStringThree;
	}

	public Integer getFifthStringFour() {
		return fifthStringFour;
	}

	public void setFifthStringFour(Integer fifthStringFour) {
		this.fifthStringFour = fifthStringFour;
	}

	public Integer getFifthStringFive() {
		return fifthStringFive;
	}

	public void setFifthStringFive(Integer fifthStringFive) {
		this.fifthStringFive = fifthStringFive;
	}

	public String getMicroInverterManufacturer() {
		return microInverterManufacturer;
	}

	public void setMicroInverterManufacturer(String microInverterManufacturer) {
		this.microInverterManufacturer = microInverterManufacturer;
	}

	public String getMicroInverterModel() {
		return microInverterModel;
	}

	public void setMicroInverterModel(String microInverterModel) {
		this.microInverterModel = microInverterModel;
	}

	public String getNumberModulesACCircuitOne() {
		return numberModulesACCircuitOne;
	}

	public void setNumberModulesACCircuitOne(String numberModulesACCircuitOne) {
		this.numberModulesACCircuitOne = numberModulesACCircuitOne;
	}

	public String getNumberModulesACCircuitTwo() {
		return numberModulesACCircuitTwo;
	}

	public void setNumberModulesACCircuitTwo(String numberModulesACCircuitTwo) {
		this.numberModulesACCircuitTwo = numberModulesACCircuitTwo;
	}

	public String getNumberModulesACCircuitThree() {
		return numberModulesACCircuitThree;
	}

	public void setNumberModulesACCircuitThree(String numberModulesACCircuitThree) {
		this.numberModulesACCircuitThree = numberModulesACCircuitThree;
	}

	public String getNumberModulesACCircuitFour() {
		return numberModulesACCircuitFour;
	}

	public void setNumberModulesACCircuitFour(String numberModulesACCircuitFour) {
		this.numberModulesACCircuitFour = numberModulesACCircuitFour;
	}

	public String getNumberModulesACCircuitFive() {
		return numberModulesACCircuitFive;
	}

	public void setNumberModulesACCircuitFive(String numberModulesACCircuitFive) {
		this.numberModulesACCircuitFive = numberModulesACCircuitFive;
	}

	public String getNumberModulesACCircuitSix() {
		return numberModulesACCircuitSix;
	}

	public void setNumberModulesACCircuitSix(String numberModulesACCircuitSix) {
		this.numberModulesACCircuitSix = numberModulesACCircuitSix;
	}

	public String getNumberModulesACCircuitSeven() {
		return numberModulesACCircuitSeven;
	}

	public void setNumberModulesACCircuitSeven(String numberModulesACCircuitSeven) {
		this.numberModulesACCircuitSeven = numberModulesACCircuitSeven;
	}

	public String getNumberModulesACCircuitEight() {
		return numberModulesACCircuitEight;
	}

	public void setNumberModulesACCircuitEight(String numberModulesACCircuitEight) {
		this.numberModulesACCircuitEight = numberModulesACCircuitEight;
	}

	public String getNumberModulesACCircuitNine() {
		return numberModulesACCircuitNine;
	}

	public void setNumberModulesACCircuitNine(String numberModulesACCircuitNine) {
		this.numberModulesACCircuitNine = numberModulesACCircuitNine;
	}

	public String getNumberModulesACCircuitTen() {
		return numberModulesACCircuitTen;
	}

	public void setNumberModulesACCircuitTen(String numberModulesACCircuitTen) {
		this.numberModulesACCircuitTen = numberModulesACCircuitTen;
	}

	public String getNumberModulesACCircuitEleven() {
		return numberModulesACCircuitEleven;
	}

	public void setNumberModulesACCircuitEleven(String numberModulesACCircuitEleven) {
		this.numberModulesACCircuitEleven = numberModulesACCircuitEleven;
	}

	public String getNumberModulesACCircuitTweleve() {
		return numberModulesACCircuitTweleve;
	}

	public void setNumberModulesACCircuitTweleve(String numberModulesACCircuitTweleve) {
		this.numberModulesACCircuitTweleve = numberModulesACCircuitTweleve;
	}

	public String getOcpdOne() {
		return ocpdOne;
	}

	public void setOcpdOne(String ocpdOne) {
		this.ocpdOne = ocpdOne;
	}

	public String getOcpdTwo() {
		return ocpdTwo;
	}

	public void setOcpdTwo(String ocpdTwo) {
		this.ocpdTwo = ocpdTwo;
	}

	public String getOcpdThree() {
		return ocpdThree;
	}

	public void setOcpdThree(String ocpdThree) {
		this.ocpdThree = ocpdThree;
	}

	public String getOcpdFour() {
		return ocpdFour;
	}

	public void setOcpdFour(String ocpdFour) {
		this.ocpdFour = ocpdFour;
	}

	public String getOcpdFive() {
		return ocpdFive;
	}

	public void setOcpdFive(String ocpdFive) {
		this.ocpdFive = ocpdFive;
	}

	public String getOcpdSix() {
		return ocpdSix;
	}

	public void setOcpdSix(String ocpdSix) {
		this.ocpdSix = ocpdSix;
	}

	public String getOcpdSeven() {
		return ocpdSeven;
	}

	public void setOcpdSeven(String ocpdSeven) {
		this.ocpdSeven = ocpdSeven;
	}

	public String getOcpdEight() {
		return ocpdEight;
	}

	public void setOcpdEight(String ocpdEight) {
		this.ocpdEight = ocpdEight;
	}

	public String getOcpdNine() {
		return ocpdNine;
	}

	public void setOcpdNine(String ocpdNine) {
		this.ocpdNine = ocpdNine;
	}

	public String getOcpdTen() {
		return ocpdTen;
	}

	public void setOcpdTen(String ocpdTen) {
		this.ocpdTen = ocpdTen;
	}

	public String getOcpdEleven() {
		return ocpdEleven;
	}

	public void setOcpdEleven(String ocpdEleven) {
		this.ocpdEleven = ocpdEleven;
	}

	public String getOcpdTwelve() {
		return ocpdTwelve;
	}

	public void setOcpdTwelve(String ocpdTwelve) {
		this.ocpdTwelve = ocpdTwelve;
	}


	public String getUploadCommentsLayout() {
		return uploadCommentsLayout;
	}

	public void setUploadCommentsLayout(String uploadCommentsLayout) {
		this.uploadCommentsLayout = uploadCommentsLayout;
	}

	public String getUploadCommentsAddInfo() {
		return uploadCommentsAddInfo;
	}

	public void setUploadCommentsAddInfo(String uploadCommentsAddInfo) {
		this.uploadCommentsAddInfo = uploadCommentsAddInfo;
	}
	

	public String getInverterLocation() {
		return inverterLocation;
	}

	public void setInverterLocation(String inverterLocation) {
		this.inverterLocation = inverterLocation;
	}
	
	public String getInverterLocationOther() {
		return inverterLocationOther;
	}

	public void setInverterLocationOther(String inverterLocationOther) {
		this.inverterLocationOther = inverterLocationOther;
	}
	
	

	public String getInverterSameLocation() {
		return inverterSameLocation;
	}

	public void setInverterSameLocation(String inverterSameLocation) {
		this.inverterSameLocation = inverterSameLocation;
	}

	
	public String getNumberModulesACCircuitThirteen() {
		return numberModulesACCircuitThirteen;
	}

	public void setNumberModulesACCircuitThirteen(String numberModulesACCircuitThirteen) {
		this.numberModulesACCircuitThirteen = numberModulesACCircuitThirteen;
	}

	public String getNumberModulesACCircuitFourteen() {
		return numberModulesACCircuitFourteen;
	}

	public void setNumberModulesACCircuitFourteen(String numberModulesACCircuitFourteen) {
		this.numberModulesACCircuitFourteen = numberModulesACCircuitFourteen;
	}

	public String getNumberModulesACCircuitFifteen() {
		return numberModulesACCircuitFifteen;
	}

	public void setNumberModulesACCircuitFifteen(String numberModulesACCircuitFifteen) {
		this.numberModulesACCircuitFifteen = numberModulesACCircuitFifteen;
	}

	public String getNumberModulesACCircuitSixteen() {
		return numberModulesACCircuitSixteen;
	}

	public void setNumberModulesACCircuitSixteen(String numberModulesACCircuitSixteen) {
		this.numberModulesACCircuitSixteen = numberModulesACCircuitSixteen;
	}

	public String getNumberModulesACCircuitSeventeen() {
		return numberModulesACCircuitSeventeen;
	}

	public void setNumberModulesACCircuitSeventeen(String numberModulesACCircuitSeventeen) {
		this.numberModulesACCircuitSeventeen = numberModulesACCircuitSeventeen;
	}

	public String getNumberModulesACCircuitEightteen() {
		return numberModulesACCircuitEightteen;
	}

	public void setNumberModulesACCircuitEightteen(String numberModulesACCircuitEightteen) {
		this.numberModulesACCircuitEightteen = numberModulesACCircuitEightteen;
	}

	public String getNumberModulesACCircuitNineteen() {
		return numberModulesACCircuitNineteen;
	}

	public void setNumberModulesACCircuitNineteen(String numberModulesACCircuitNineteen) {
		this.numberModulesACCircuitNineteen = numberModulesACCircuitNineteen;
	}

	public String getNumberModulesACCircuitTwenty() {
		return numberModulesACCircuitTwenty;
	}

	public void setNumberModulesACCircuitTwenty(String numberModulesACCircuitTwenty) {
		this.numberModulesACCircuitTwenty = numberModulesACCircuitTwenty;
	}

	public String getNumberModulesACCircuitTwentyOne() {
		return numberModulesACCircuitTwentyOne;
	}

	public void setNumberModulesACCircuitTwentyOne(String numberModulesACCircuitTwentyOne) {
		this.numberModulesACCircuitTwentyOne = numberModulesACCircuitTwentyOne;
	}

	public String getNumberModulesACCircuitTwentyTwo() {
		return numberModulesACCircuitTwentyTwo;
	}

	public void setNumberModulesACCircuitTwentyTwo(String numberModulesACCircuitTwentyTwo) {
		this.numberModulesACCircuitTwentyTwo = numberModulesACCircuitTwentyTwo;
	}

	public String getNumberModulesACCircuitTwentyThree() {
		return numberModulesACCircuitTwentyThree;
	}

	public void setNumberModulesACCircuitTwentyThree(String numberModulesACCircuitTwentyThree) {
		this.numberModulesACCircuitTwentyThree = numberModulesACCircuitTwentyThree;
	}

	public String getNumberModulesACCircuitTwentyFour() {
		return numberModulesACCircuitTwentyFour;
	}

	public void setNumberModulesACCircuitTwentyFour(String numberModulesACCircuitTwentyFour) {
		this.numberModulesACCircuitTwentyFour = numberModulesACCircuitTwentyFour;
	}

	public String getOcpdThirteen() {
		return ocpdThirteen;
	}

	public void setOcpdThirteen(String ocpdThirteen) {
		this.ocpdThirteen = ocpdThirteen;
	}

	public String getOcpdFourteen() {
		return ocpdFourteen;
	}

	public void setOcpdFourteen(String ocpdFourteen) {
		this.ocpdFourteen = ocpdFourteen;
	}

	public String getOcpdFifteen() {
		return ocpdFifteen;
	}

	public void setOcpdFifteen(String ocpdFifteen) {
		this.ocpdFifteen = ocpdFifteen;
	}

	public String getOcpdSixteen() {
		return ocpdSixteen;
	}

	public void setOcpdSixteen(String ocpdSixteen) {
		this.ocpdSixteen = ocpdSixteen;
	}

	public String getOcpdSeventeen() {
		return ocpdSeventeen;
	}

	public void setOcpdSeventeen(String ocpdSeventeen) {
		this.ocpdSeventeen = ocpdSeventeen;
	}

	public String getOcpdEightteen() {
		return ocpdEightteen;
	}

	public void setOcpdEightteen(String ocpdEightteen) {
		this.ocpdEightteen = ocpdEightteen;
	}

	public String getOcpdNineteen() {
		return ocpdNineteen;
	}

	public void setOcpdNineteen(String ocpdNineteen) {
		this.ocpdNineteen = ocpdNineteen;
	}

	public String getOcpdTwenty() {
		return ocpdTwenty;
	}

	public void setOcpdTwenty(String ocpdTwenty) {
		this.ocpdTwenty = ocpdTwenty;
	}

	public String getOcpdTwentyOne() {
		return ocpdTwentyOne;
	}

	public void setOcpdTwentyOne(String ocpdTwentyOne) {
		this.ocpdTwentyOne = ocpdTwentyOne;
	}

	public String getOcpdTwentyTwo() {
		return ocpdTwentyTwo;
	}

	public void setOcpdTwentyTwo(String ocpdTwentyTwo) {
		this.ocpdTwentyTwo = ocpdTwentyTwo;
	}

	public String getOcpdTwentyThree() {
		return ocpdTwentyThree;
	}

	public void setOcpdTwentyThree(String ocpdTwentyThree) {
		this.ocpdTwentyThree = ocpdTwentyThree;
	}

	public String getOcpdTwentyFour() {
		return ocpdTwentyFour;
	}

	public void setOcpdTwentyFour(String ocpdTwentyFour) {
		this.ocpdTwentyFour = ocpdTwentyFour;
	}

	
	public Integer getStringSix() {
		return stringSix;
	}

	public void setStringSix(Integer stringSix) {
		this.stringSix = stringSix;
	}

	public Integer getStringSeven() {
		return stringSeven;
	}

	public void setStringSeven(Integer stringSeven) {
		this.stringSeven = stringSeven;
	}

	public Integer getStringEight() {
		return stringEight;
	}

	public void setStringEight(Integer stringEight) {
		this.stringEight = stringEight;
	}

	public Integer getStringNine() {
		return stringNine;
	}

	public void setStringNine(Integer stringNine) {
		this.stringNine = stringNine;
	}

	public Integer getStringTen() {
		return stringTen;
	}

	public void setStringTen(Integer stringTen) {
		this.stringTen = stringTen;
	}

	public Integer getStringEleven() {
		return stringEleven;
	}

	public void setStringEleven(Integer stringEleven) {
		this.stringEleven = stringEleven;
	}

	public Integer getStringTwelve() {
		return stringTwelve;
	}

	public void setStringTwelve(Integer stringTwelve) {
		this.stringTwelve = stringTwelve;
	}

	public Integer getSecondStringSix() {
		return secondStringSix;
	}

	public void setSecondStringSix(Integer secondStringSix) {
		this.secondStringSix = secondStringSix;
	}

	public Integer getSecondStringSeven() {
		return secondStringSeven;
	}

	public void setSecondStringSeven(Integer secondStringSeven) {
		this.secondStringSeven = secondStringSeven;
	}

	public Integer getSecondStringEight() {
		return secondStringEight;
	}

	public void setSecondStringEight(Integer secondStringEight) {
		this.secondStringEight = secondStringEight;
	}

	public Integer getSecondStringNine() {
		return secondStringNine;
	}

	public void setSecondStringNine(Integer secondStringNine) {
		this.secondStringNine = secondStringNine;
	}

	public Integer getSecondStringTen() {
		return secondStringTen;
	}

	public void setSecondStringTen(Integer secondStringTen) {
		this.secondStringTen = secondStringTen;
	}

	public Integer getSecondStringEleven() {
		return secondStringEleven;
	}

	public void setSecondStringEleven(Integer secondStringEleven) {
		this.secondStringEleven = secondStringEleven;
	}

	public Integer getSecondStringTwelve() {
		return secondStringTwelve;
	}

	public void setSecondStringTwelve(Integer secondStringTwelve) {
		this.secondStringTwelve = secondStringTwelve;
	}

	public Integer getThirdStringSix() {
		return thirdStringSix;
	}

	public void setThirdStringSix(Integer thirdStringSix) {
		this.thirdStringSix = thirdStringSix;
	}

	public Integer getThirdStringSeven() {
		return thirdStringSeven;
	}

	public void setThirdStringSeven(Integer thirdStringSeven) {
		this.thirdStringSeven = thirdStringSeven;
	}

	public Integer getThirdStringEight() {
		return thirdStringEight;
	}

	public void setThirdStringEight(Integer thirdStringEight) {
		this.thirdStringEight = thirdStringEight;
	}

	public Integer getThirdStringNine() {
		return thirdStringNine;
	}

	public void setThirdStringNine(Integer thirdStringNine) {
		this.thirdStringNine = thirdStringNine;
	}

	public Integer getThirdStringTen() {
		return thirdStringTen;
	}

	public void setThirdStringTen(Integer thirdStringTen) {
		this.thirdStringTen = thirdStringTen;
	}

	public Integer getThirdStringEleven() {
		return thirdStringEleven;
	}

	public void setThirdStringEleven(Integer thirdStringEleven) {
		this.thirdStringEleven = thirdStringEleven;
	}

	public Integer getThirdStringTwelve() {
		return thirdStringTwelve;
	}

	public void setThirdStringTwelve(Integer thirdStringTwelve) {
		this.thirdStringTwelve = thirdStringTwelve;
	}

	public Integer getFourthStringSix() {
		return fourthStringSix;
	}

	public void setFourthStringSix(Integer fourthStringSix) {
		this.fourthStringSix = fourthStringSix;
	}

	public Integer getFourthStringSeven() {
		return fourthStringSeven;
	}

	public void setFourthStringSeven(Integer fourthStringSeven) {
		this.fourthStringSeven = fourthStringSeven;
	}

	public Integer getFourthStringEight() {
		return fourthStringEight;
	}

	public void setFourthStringEight(Integer fourthStringEight) {
		this.fourthStringEight = fourthStringEight;
	}

	public Integer getFourthStringNine() {
		return fourthStringNine;
	}

	public void setFourthStringNine(Integer fourthStringNine) {
		this.fourthStringNine = fourthStringNine;
	}

	public Integer getFourthStringTen() {
		return fourthStringTen;
	}

	public void setFourthStringTen(Integer fourthStringTen) {
		this.fourthStringTen = fourthStringTen;
	}

	public Integer getFourthStringEleven() {
		return fourthStringEleven;
	}

	public void setFourthStringEleven(Integer fourthStringEleven) {
		this.fourthStringEleven = fourthStringEleven;
	}

	public Integer getFourthStringTwelve() {
		return fourthStringTwelve;
	}

	public void setFourthStringTwelve(Integer fourthStringTwelve) {
		this.fourthStringTwelve = fourthStringTwelve;
	}

	public Integer getFifthStringSix() {
		return fifthStringSix;
	}

	public void setFifthStringSix(Integer fifthStringSix) {
		this.fifthStringSix = fifthStringSix;
	}

	public Integer getFifthStringSeven() {
		return fifthStringSeven;
	}

	public void setFifthStringSeven(Integer fifthStringSeven) {
		this.fifthStringSeven = fifthStringSeven;
	}

	public Integer getFifthStringEight() {
		return fifthStringEight;
	}

	public void setFifthStringEight(Integer fifthStringEight) {
		this.fifthStringEight = fifthStringEight;
	}

	public Integer getFifthStringNine() {
		return fifthStringNine;
	}

	public void setFifthStringNine(Integer fifthStringNine) {
		this.fifthStringNine = fifthStringNine;
	}

	public Integer getFifthStringTen() {
		return fifthStringTen;
	}

	public void setFifthStringTen(Integer fifthStringTen) {
		this.fifthStringTen = fifthStringTen;
	}

	public Integer getFifthStringEleven() {
		return fifthStringEleven;
	}

	public void setFifthStringEleven(Integer fifthStringEleven) {
		this.fifthStringEleven = fifthStringEleven;
	}

	public Integer getFifthStringTwelve() {
		return fifthStringTwelve;
	}

	public void setFifthStringTwelve(Integer fifthStringTwelve) {
		this.fifthStringTwelve = fifthStringTwelve;
	}
    
	public String getMicroInverter() {
		return microInverter;
	}

	public void setMicroInverter(String microInverter) {
		this.microInverter = microInverter;
	}

	public String getRoofOrOpenFrame() {
		return roofOrOpenFrame;
	}

	public void setRoofOrOpenFrame(String roofOrOpenFrame) {
		this.roofOrOpenFrame = roofOrOpenFrame;
	}

	public Boolean getCircuitUnderGround() {
		return circuitUnderGround;
	}

	public void setCircuitUnderGround(Boolean circuitUnderGround) {
		this.circuitUnderGround = circuitUnderGround;
	}

	public Inverters getFirstInverter() {
		return firstInverter;
	}

	public void setFirstInverter(Inverters firstInverter) {
		this.firstInverter = firstInverter;
	}

	public Inverters getSecondInverter() {
		return secondInverter;
	}

	public void setSecondInverter(Inverters secondInverter) {
		this.secondInverter = secondInverter;
	}

	public Inverters getThirdInverter() {
		return thirdInverter;
	}

	public void setThirdInverter(Inverters thirdInverter) {
		this.thirdInverter = thirdInverter;
	}

	public Inverters getFourthInverter() {
		return fourthInverter;
	}

	public void setFourthInverter(Inverters fourthInverter) {
		this.fourthInverter = fourthInverter;
	}

	public Inverters getFifthInverter() {
		return fifthInverter;
	}

	public void setFifthInverter(Inverters fifthInverter) {
		this.fifthInverter = fifthInverter;
	}

	public Cmodulev2 getPvModule() {
		return pvModule;
	}

	public void setPvModule(Cmodulev2 pvModule) {
		this.pvModule = pvModule;
	}

	
	public Inverters getMicroInverterEntity() {
		return microInverterEntity;
	}

	public void setMicroInverterEntity(Inverters microInverterEntity) {
		this.microInverterEntity = microInverterEntity;
	}

	
	
	public String getSystemOptimizerModelManufacturer() {
		return systemOptimizerModelManufacturer;
	}

	public void setSystemOptimizerModelManufacturer(String systemOptimizerModelManufacturer) {
		this.systemOptimizerModelManufacturer = systemOptimizerModelManufacturer;
	}
	
	public String getEnteranyTransformer() {
		return enteranyTransformer;
	}

	public void setEnteranyTransformer(String enteranyTransformer) {
		this.enteranyTransformer = enteranyTransformer;
	}
	
	public Boolean getPatioMounted() {
		return patioMounted;
	}

	public void setPatioMounted(Boolean patioMounted) {
		this.patioMounted = patioMounted;
	}
	
	public Boolean getFrontAndBack() {
		return frontAndBack;
	}

	public void setFrontAndBack(Boolean frontAndBack) {
		this.frontAndBack = frontAndBack;
	}

	public Boolean getCantelever() {
		return cantelever;
	}

	public void setCantelever(Boolean cantelever) {
		this.cantelever = cantelever;
	}

	public Boolean getAttachedToExtWal() {
		return attachedToExtWal;
	}

	public void setAttachedToExtWal(Boolean attachedToExtWal) {
		this.attachedToExtWal = attachedToExtWal;
	}

	public Boolean getAttachedToFascia() {
		return attachedToFascia;
	}

	public void setAttachedToFascia(Boolean attachedToFascia) {
		this.attachedToFascia = attachedToFascia;
	}

	public Boolean getAttachedToSkylifts() {
		return attachedToSkylifts;
	}

	public void setAttachedToSkylifts(Boolean attachedToSkylifts) {
		this.attachedToSkylifts = attachedToSkylifts;
	}

	public Boolean getFreeStanding() {
		return freeStanding;
	}

	public void setFreeStanding(Boolean freeStanding) {
		this.freeStanding = freeStanding;
	}

	@Override
	public String toString() {
		return "PermitArraysEntity [id=" + id + ", permitEntity=" + permitEntity + ", systemType=" + systemType
				+ ", RequestQuote=" + RequestQuote + ", deviceToIncorporate=" + deviceToIncorporate + ", pvModuleModEl="
				+ pvModuleModEl + ", pvModule=" + pvModule + ", firstInverter=" + firstInverter + ", secondInverter="
				+ secondInverter + ", thirdInverter=" + thirdInverter + ", fourthInverter=" + fourthInverter
				+ ", fifthInverter=" + fifthInverter + ", inverterModel=" + inverterModel + ", stringOne=" + stringOne
				+ ", stringTwo=" + stringTwo + ", stringThree=" + stringThree + ", stringFour=" + stringFour
				+ ", stringFive=" + stringFive + ", secondInverterModel=" + secondInverterModel + ", secondStringOne="
				+ secondStringOne + ", secondStringTwo=" + secondStringTwo + ", secondStringThree=" + secondStringThree
				+ ", secondStringFour=" + secondStringFour + ", secondStringFive=" + secondStringFive + ", roofMounted="
				+ roofMounted + ", GroundMounted=" + GroundMounted + ", carportMounted=" + carportMounted
				+ ", otherMounted=" + otherMounted + ", carportMountedAttached="
				+ ", textOther="
				+ textOther + ", BatteryManufacturerTrojan=" + BatteryManufacturerTrojan + ", BatteryManufacturerMMK="
				+ BatteryManufacturerMMK + ", BatteryManufacturerUPG=" + BatteryManufacturerUPG
				+ ", BatteryManufacturerRolls=" + BatteryManufacturerRolls + ", BatteryManufacturerCrown="
				+ BatteryManufacturerCrown + ", BatteryManufacturerTesla=" + BatteryManufacturerTesla
				+ ", BatteryManufacturerOutback=" + BatteryManufacturerOutback + ", BatteryManufacturerFullriver="
				+ BatteryManufacturerFullriver + ", BatteryManufacturerConcord=" + BatteryManufacturerConcord
				+ ", BatteryManufacturerOther=" + BatteryManufacturerOther + ", textBatteryOther=" + textBatteryOther
				+ ", qteOfBattery=" + qteOfBattery + ", systemOptimizerModel=" + systemOptimizerModel
				+ ", thirdInverterModel=" + thirdInverterModel + ", thirdStringOne=" + thirdStringOne
				+ ", thirdStringTwo=" + thirdStringTwo + ", thirdStringThree=" + thirdStringThree + ", thirdStringFour="
				+ thirdStringFour + ", thirdStringFive=" + thirdStringFive + ", fourthInverterModel="
				+ fourthInverterModel + ", fourthStringOne=" + fourthStringOne + ", fourthStringTwo=" + fourthStringTwo
				+ ", fourthStringThree=" + fourthStringThree + ", fourthStringFour=" + fourthStringFour
				+ ", fourthStringFive=" + fourthStringFive + ", fifthInverterModel=" + fifthInverterModel
				+ ", fifthStringOne=" + fifthStringOne + ", fifthStringTwo=" + fifthStringTwo + ", fifthStringThree="
				+ fifthStringThree + ", fifthStringFour=" + fifthStringFour + ", fifthStringFive=" + fifthStringFive
				+ ", microInverterManufacturer=" + microInverterManufacturer + ", microInverterModel="
				+ microInverterModel + ", numberModulesACCircuitOne=" + numberModulesACCircuitOne
				+ ", numberModulesACCircuitTwo=" + numberModulesACCircuitTwo + ", numberModulesACCircuitThree="
				+ numberModulesACCircuitThree + ", numberModulesACCircuitFour=" + numberModulesACCircuitFour
				+ ", numberModulesACCircuitFive=" + numberModulesACCircuitFive + ", numberModulesACCircuitSix="
				+ numberModulesACCircuitSix + ", numberModulesACCircuitSeven=" + numberModulesACCircuitSeven
				+ ", numberModulesACCircuitEight=" + numberModulesACCircuitEight + ", numberModulesACCircuitNine="
				+ numberModulesACCircuitNine + ", numberModulesACCircuitTen=" + numberModulesACCircuitTen
				+ ", numberModulesACCircuitEleven=" + numberModulesACCircuitEleven + ", numberModulesACCircuitTweleve="
				+ numberModulesACCircuitTweleve + ", numberModulesACCircuitThirteen=" + numberModulesACCircuitThirteen
				+ ", numberModulesACCircuitFourteen=" + numberModulesACCircuitFourteen
				+ ", numberModulesACCircuitFifteen=" + numberModulesACCircuitFifteen
				+ ", numberModulesACCircuitSixteen=" + numberModulesACCircuitSixteen
				+ ", numberModulesACCircuitSeventeen=" + numberModulesACCircuitSeventeen
				+ ", numberModulesACCircuitEightteen=" + numberModulesACCircuitEightteen
				+ ", numberModulesACCircuitNineteen=" + numberModulesACCircuitNineteen
				+ ", numberModulesACCircuitTwenty=" + numberModulesACCircuitTwenty
				+ ", numberModulesACCircuitTwentyOne=" + numberModulesACCircuitTwentyOne
				+ ", numberModulesACCircuitTwentyTwo=" + numberModulesACCircuitTwentyTwo
				+ ", numberModulesACCircuitTwentyThree=" + numberModulesACCircuitTwentyThree
				+ ", numberModulesACCircuitTwentyFour=" + numberModulesACCircuitTwentyFour + ", ocpdOne=" + ocpdOne
				+ ", ocpdTwo=" + ocpdTwo + ", ocpdThree=" + ocpdThree + ", ocpdFour=" + ocpdFour + ", ocpdFive="
				+ ocpdFive + ", ocpdSix=" + ocpdSix + ", ocpdSeven=" + ocpdSeven + ", ocpdEight=" + ocpdEight
				+ ", ocpdNine=" + ocpdNine + ", ocpdTen=" + ocpdTen + ", ocpdEleven=" + ocpdEleven + ", ocpdTwelve="
				+ ocpdTwelve + ", ocpdThirteen=" + ocpdThirteen + ", ocpdFourteen=" + ocpdFourteen + ", ocpdFifteen="
				+ ocpdFifteen + ", ocpdSixteen=" + ocpdSixteen + ", ocpdSeventeen=" + ocpdSeventeen + ", ocpdEightteen="
				+ ocpdEightteen + ", ocpdNineteen=" + ocpdNineteen + ", ocpdTwenty=" + ocpdTwenty + ", ocpdTwentyOne="
				+ ocpdTwentyOne + ", ocpdTwentyTwo=" + ocpdTwentyTwo + ", ocpdTwentyThree=" + ocpdTwentyThree
				+ ", ocpdTwentyFour=" + ocpdTwentyFour + ", uploadCommentsLayout=" + uploadCommentsLayout
				+ ", uploadCommentsAddInfo=" + uploadCommentsAddInfo + ", inverterLocation=" + inverterLocation
				+ ", inverterLocationOther=" + inverterLocationOther + ", inverterSameLocation=" + inverterSameLocation
				+ ", stringSix=" + stringSix + ", stringSeven=" + stringSeven + ", stringEight=" + stringEight
				+ ", stringNine=" + stringNine + ", stringTen=" + stringTen + ", stringEleven=" + stringEleven
				+ ", stringTwelve=" + stringTwelve + ", secondStringSix=" + secondStringSix + ", secondStringSeven="
				+ secondStringSeven + ", secondStringEight=" + secondStringEight + ", secondStringNine="
				+ secondStringNine + ", secondStringTen=" + secondStringTen + ", secondStringEleven="
				+ secondStringEleven + ", secondStringTwelve=" + secondStringTwelve + ", thirdStringSix="
				+ thirdStringSix + ", thirdStringSeven=" + thirdStringSeven + ", thirdStringEight=" + thirdStringEight
				+ ", thirdStringNine=" + thirdStringNine + ", thirdStringTen=" + thirdStringTen + ", thirdStringEleven="
				+ thirdStringEleven + ", thirdStringTwelve=" + thirdStringTwelve + ", fourthStringSix="
				+ fourthStringSix + ", fourthStringSeven=" + fourthStringSeven + ", fourthStringEight="
				+ fourthStringEight + ", fourthStringNine=" + fourthStringNine + ", fourthStringTen=" + fourthStringTen
				+ ", fourthStringEleven=" + fourthStringEleven + ", fourthStringTwelve=" + fourthStringTwelve
				+ ", fifthStringSix=" + fifthStringSix + ", fifthStringSeven=" + fifthStringSeven
				+ ", fifthStringEight=" + fifthStringEight + ", fifthStringNine=" + fifthStringNine
				+ ", fifthStringTen=" + fifthStringTen + ", fifthStringEleven=" + fifthStringEleven
				+ ", fifthStringTwelve=" + fifthStringTwelve + ", microInverter=" + microInverter + ", roofOrOpenFrame="
				+ roofOrOpenFrame + ", circuitUnderGround=" + circuitUnderGround + ", inverterInstalledOnRoof="
				+ inverterInstalledOnRoof + ",microInverterEntity "+microInverterEntity+"]";
	}


}
