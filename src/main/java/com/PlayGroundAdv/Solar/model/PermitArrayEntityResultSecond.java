package com.PlayGroundAdv.Solar.model;

public class PermitArrayEntityResultSecond {

	private String systemType;
	private Boolean RequestQuote;
	private String deviceToIncorporate;
	private Long pvModuleModEl;
	private Long inverterModel;
	private String stringOneStr;
	private String stringTwoStr;
	private String stringThreeStr;
	private String stringFourStr;
	private String stringFiveStr;

	private Long secondInverterModel;
	private String secondStringOneStr;
	private String secondStringTwoStr;
	private String secondStringThreeStr;
	private String secondStringFourStr;
	private String secondStringFiveStr;
	private Boolean roofMounted;
	private Boolean GroundMounted;
	private Boolean carportMounted;
	private Boolean otherMounted;
	private String textOther;

	private Boolean frontAndBack;
	private Boolean cantelever;
	private Boolean attachedToExtWal;
	private Boolean attachedToFascia;
	private Boolean attachedToSkylifts;
	private Boolean freeStanding;
	private String qteOfBattery;
	// new avriable boolean hidden
	private Boolean BatteryManufacturerTrojan;
	private Boolean BatteryManufacturerMMK;
	private Boolean BatteryManufacturerUPG;
	private Boolean BatteryManufacturerRolls;
	private Boolean BatteryManufacturerCrown;
	private Boolean BatteryManufacturerTesla;
	private Boolean BatteryManufacturerOutback;
	private Boolean BatteryManufacturerFullriver;
	private Boolean BatteryManufacturerConcord;
	private Boolean BatteryManufacturerOther;
	private String textBatteryOther;
	// new input
	private Long systemOptimizerModel;
	// format variable string to int
	private Integer stringOne;
	private Integer stringTwo;
	private Integer stringThree;
	private Integer stringFour;
	private Integer stringFive;
	private Integer secondStringOne;
	private Integer secondStringTwo;
	private Integer secondStringThree;
	private Integer secondStringFour;
	private Integer secondStringFive;
	// new input SR 002

	private Long thirdInverterModel;

	private Integer thirdStringOne;
	private Integer thirdStringTwo;
	private Integer thirdStringThree;
	private Integer thirdStringFour;
	private Integer thirdStringFive;

	private Long fourthInverterModel;

	private Integer fourthStringOne;
	private Integer fourthStringTwo;
	private Integer fourthStringThree;
	private Integer fourthStringFour;
	private Integer fourthStringFive;

	private Long fifthInverterModel;

	private Integer fifthStringOne;
	private Integer fifthStringTwo;
	private Integer fifthStringThree;
	private Integer fifthStringFour;
	private Integer fifthStringFive;

	private String microInverterModel;
	private String microInverterManufacturer;
	private String numberModulesACCircuitOne;
	private String numberModulesACCircuitTwo;
	private String numberModulesACCircuitThree;
	private String numberModulesACCircuitFour;
	private String numberModulesACCircuitFive;
	private String numberModulesACCircuitSix;
	private String numberModulesACCircuitSeven;
	private String numberModulesACCircuitEight;
	private String numberModulesACCircuitNine;
	private String numberModulesACCircuitTen;
	private String numberModulesACCircuitEleven;
	private String numberModulesACCircuitTweleve;

	private String ocpdOne;
	private String ocpdTwo;
	private String ocpdThree;
	private String ocpdFour;
	private String ocpdFive;
	private String ocpdSix;
	private String ocpdSeven;
	private String ocpdEight;
	private String ocpdNine;
	private String ocpdTen;
	private String ocpdEleven;
	private String ocpdTwelve;

	private String uploadCommentsLayout;

	private String uploadCommentsAddInfo;
	private String inverterLocation;
	private String inverterLocationOther;
	private String inverterSameLocation;

	private String numberModulesACCircuitThirteen;
	private String numberModulesACCircuitFourteen;
	private String numberModulesACCircuitFifteen;
	private String numberModulesACCircuitSixteen;
	private String numberModulesACCircuitSeventeen;
	private String numberModulesACCircuitEightteen;
	private String numberModulesACCircuitNineteen;
	private String numberModulesACCircuitTwenty;
	private String numberModulesACCircuitTwentyOne;
	private String numberModulesACCircuitTwentyTwo;
	private String numberModulesACCircuitTwentyThree;
	private String numberModulesACCircuitTwentyFour;
	private String ocpdThirteen;
	private String ocpdFourteen;
	private String ocpdFifteen;
	private String ocpdSixteen;
	private String ocpdSeventeen;
	private String ocpdEightteen;
	private String ocpdNineteen;
	private String ocpdTwenty;
	private String ocpdTwentyOne;
	private String ocpdTwentyTwo;
	private String ocpdTwentyThree;
	private String ocpdTwentyFour;

	// CR-2222 Add UP TO 12 String Inputs for Number of Modules in Each String
	private Integer stringSix;
	private Integer stringSeven;
	private Integer stringEight;
	private Integer stringNine;
	private Integer stringTen;
	private Integer stringEleven;
	private Integer stringTwelve;
	private Integer secondStringSix;
	private Integer secondStringSeven;
	private Integer secondStringEight;
	private Integer secondStringNine;
	private Integer secondStringTen;
	private Integer secondStringEleven;
	private Integer secondStringTwelve;
	private Integer thirdStringSix;
	private Integer thirdStringSeven;
	private Integer thirdStringEight;
	private Integer thirdStringNine;
	private Integer thirdStringTen;
	private Integer thirdStringEleven;
	private Integer thirdStringTwelve;
	private Integer fourthStringSix;
	private Integer fourthStringSeven;
	private Integer fourthStringEight;
	private Integer fourthStringNine;
	private Integer fourthStringTen;
	private Integer fourthStringEleven;
	private Integer fourthStringTwelve;
	private Integer fifthStringSix;
	private Integer fifthStringSeven;
	private Integer fifthStringEight;
	private Integer fifthStringNine;
	private Integer fifthStringTen;
	private Integer fifthStringEleven;
	private Integer fifthStringTwelve;
	private Long microInverter;

	// 02-22-2019 A.B: CR-2313
	private String roofOrOpenFrame;
	private Boolean circuitUnderGround;
	private Boolean inverterInstalledOnRoof;
	private String enteranyTransformer;
	private Boolean patioMounted;

	public PermitArrayEntityResultSecond(String systemType, Boolean requestQuote, String deviceToIncorporate,
			Long pvModuleModEl, Long inverterModel, String stringOne, String stringTwo, String stringThree,
			String stringFour, String stringFive, Long secondInverterModel, String secondStringOne,
			String secondStringTwo, String secondStringThree, String secondStringFour, String secondStringFive,
			Boolean roofMounted, Boolean groundMounted, Boolean otherMounted, String textOther,
			Boolean batteryManufacturerTrojan, Boolean batteryManufacturerMMK, Boolean batteryManufacturerUPG,
			Boolean batteryManufacturerRolls, Boolean batteryManufacturerCrown, Boolean batteryManufacturerTesla,
			Boolean batteryManufacturerOutback, Boolean batteryManufacturerFullriver,
			Boolean batteryManufacturerConcord, Boolean batteryManufacturerOther, String textBatteryOther,
			String qteOfBattery, Long systemOptimizerModel,

			Long thirdInverterModel, Integer thirdStringOne, Integer thirdStringTwo, Integer thirdStringThree,
			Integer thirdStringFour, Integer thirdStringFive, Long fourthInverterModel, Integer fourthStringOne,
			Integer fourthStringTwo, Integer fourthStringThree, Integer fourthStringFour, Integer fourthStringFive,
			Long fifthInverterModel, Integer fifthStringOne, Integer fifthStringTwo, Integer fifthStringThree,
			Integer fifthStringFour, Integer fifthStringFive, String microInverterManufacturer,
			String microInverterModel, String numberModulesACCircuitOne, String numberModulesACCircuitTwo,
			String numberModulesACCircuitThree, String numberModulesACCircuitFour, String numberModulesACCircuitFive,

			String numberModulesACCircuitSix, String numberModulesACCircuitSeven, String numberModulesACCircuitEight,
			String numberModulesACCircuitNine, String numberModulesACCircuitTen, String numberModulesACCircuitEleven,
			String numberModulesACCircuitTweleve, Boolean carportMounted, String ocpdOne, String ocpdTwo, String ocpdThree, String ocpdFour,
			String ocpdFive, String ocpdSix, String ocpdSeven, String ocpdEight, String ocpdNine, String ocpdTen,
			String ocpdEleven, String ocpdTwelve, String uploadCommentsLayout, String uploadCommentsAddInfo,
			String inverterLocation, String inverterLocationOther, String inverterSameLocation,
			String numberModulesACCircuitThirteen, String numberModulesACCircuitFourteen,
			String numberModulesACCircuitFifteen, String numberModulesACCircuitSixteen,
			String numberModulesACCircuitSeventeen, String numberModulesACCircuitEightteen,
			String numberModulesACCircuitNineteen, String numberModulesACCircuitTwenty,
			String numberModulesACCircuitTwentyOne, String numberModulesACCircuitTwentyTwo,
			String numberModulesACCircuitTwentyThree, String numberModulesACCircuitTwentyFour, String ocpdThirteen,
			String ocpdFourteen, String ocpdFifteen, String ocpdSixteen, String ocpdSeventeen, String ocpdEightteen,
			String ocpdNineteen, String ocpdTwenty, String ocpdTwentyOne, String ocpdTwentyTwo, String ocpdTwentyThree,
			String ocpdTwentyFour, Integer stringSix, Integer stringSeven, Integer stringEight, Integer stringNine,
			Integer stringTen, Integer stringEleven, Integer stringTwelve, Integer secondStringSix,
			Integer secondStringSeven, Integer secondStringEight, Integer secondStringNine, Integer secondStringTen,
			Integer secondStringEleven, Integer secondStringTwelve, Integer thirdStringSix, Integer thirdStringSeven,
			Integer thirdStringEight, Integer thirdStringNine, Integer thirdStringTen, Integer thirdStringEleven,
			Integer thirdStringTwelve, Integer fourthStringSix, Integer fourthStringSeven, Integer fourthStringEight,
			Integer fourthStringNine, Integer fourthStringTen, Integer fourthStringEleven, Integer fourthStringTwelve,
			Integer fifthStringSix, Integer fifthStringSeven, Integer fifthStringEight, Integer fifthStringNine,
			Integer fifthStringTen, Integer fifthStringEleven, Integer fifthStringTwelve, Long microInverter,
			String roofOrOpenFrame, Boolean circuitUnderGround, Boolean inverterInstalledOnRoof, String enteranyTransformer,
			Boolean patioMounted, Boolean frontAndBack, Boolean cantelever, Boolean attachedToExtWal,
			Boolean attachedToFascia, Boolean attachedToSkylifts, Boolean freeStanding) {
		super();
		this.systemType = systemType;
		RequestQuote = requestQuote;
		this.deviceToIncorporate = deviceToIncorporate;
		this.pvModuleModEl = pvModuleModEl;
		this.inverterModel = inverterModel;
		this.stringOneStr = stringOne;
		this.stringTwoStr = stringTwo;
		this.stringThreeStr = stringThree;
		this.stringFourStr = stringFour;
		this.stringFiveStr = stringFive;
		this.secondInverterModel = secondInverterModel;
		this.secondStringOneStr = secondStringOne;
		this.secondStringTwoStr = secondStringTwo;
		this.secondStringThreeStr = secondStringThree;
		this.secondStringFourStr = secondStringFour;
		this.secondStringFiveStr = secondStringFive;
		this.roofMounted = roofMounted;
		GroundMounted = groundMounted;
		this.otherMounted = otherMounted;
		this.textOther = textOther;
		BatteryManufacturerTrojan = batteryManufacturerTrojan;
		BatteryManufacturerMMK = batteryManufacturerMMK;
		BatteryManufacturerUPG = batteryManufacturerUPG;
		BatteryManufacturerRolls = batteryManufacturerRolls;
		BatteryManufacturerCrown = batteryManufacturerCrown;
		BatteryManufacturerTesla = batteryManufacturerTesla;
		BatteryManufacturerOutback = batteryManufacturerOutback;
		BatteryManufacturerFullriver = batteryManufacturerFullriver;
		BatteryManufacturerConcord = batteryManufacturerConcord;
		BatteryManufacturerOther = batteryManufacturerOther;
		this.textBatteryOther = textBatteryOther;
		this.qteOfBattery = qteOfBattery;
		this.systemOptimizerModel = systemOptimizerModel;

		this.uploadCommentsLayout = uploadCommentsLayout;
		this.uploadCommentsAddInfo = uploadCommentsAddInfo;

		this.thirdInverterModel = thirdInverterModel;

		this.thirdStringOne = thirdStringOne;
		this.thirdStringTwo = thirdStringTwo;
		this.thirdStringThree = thirdStringThree;
		this.thirdStringFour = thirdStringFour;
		this.thirdStringFive = thirdStringFive;

		this.fourthInverterModel = fourthInverterModel;

		this.fourthStringOne = fourthStringOne;
		this.fourthStringTwo = fourthStringTwo;
		this.fourthStringThree = fourthStringThree;
		this.fourthStringFour = fourthStringFour;
		this.fourthStringFive = fourthStringFive;

		this.fifthInverterModel = fifthInverterModel;

		this.fifthStringOne = fifthStringOne;
		this.fifthStringTwo = fifthStringTwo;
		this.fifthStringThree = fifthStringThree;
		this.fifthStringFour = fifthStringFour;
		this.fifthStringFive = fifthStringFive;

		this.microInverterModel = microInverterModel;
		this.microInverterManufacturer = microInverterManufacturer;
		this.numberModulesACCircuitOne = numberModulesACCircuitOne;
		this.numberModulesACCircuitTwo = numberModulesACCircuitTwo;
		this.numberModulesACCircuitThree = numberModulesACCircuitThree;
		this.numberModulesACCircuitFour = numberModulesACCircuitFour;
		this.numberModulesACCircuitFive = numberModulesACCircuitFive;
		this.numberModulesACCircuitSix = numberModulesACCircuitSix;
		this.numberModulesACCircuitSeven = numberModulesACCircuitSeven;
		this.numberModulesACCircuitEight = numberModulesACCircuitEight;
		this.numberModulesACCircuitNine = numberModulesACCircuitNine;
		this.numberModulesACCircuitTen = numberModulesACCircuitTen;
		this.numberModulesACCircuitEleven = numberModulesACCircuitEleven;
		this.numberModulesACCircuitTweleve = numberModulesACCircuitTweleve;
		this.carportMounted = carportMounted;
		this.frontAndBack = frontAndBack;
		this.cantelever=cantelever;
		this.attachedToExtWal = attachedToExtWal;
		this.attachedToFascia=attachedToFascia;
		this.attachedToSkylifts = attachedToSkylifts;
		this.freeStanding=freeStanding;

		this.ocpdOne = ocpdOne;
		this.ocpdTwo = ocpdTwo;

		this.ocpdThree = ocpdThree;
		this.ocpdFour = ocpdFour;
		this.ocpdFive = ocpdFive;
		this.ocpdSix = ocpdSix;
		this.ocpdSeven = ocpdSeven;
		this.ocpdEight = ocpdEight;
		this.ocpdNine = ocpdNine;
		this.ocpdTen = ocpdTen;
		this.ocpdEleven = ocpdEleven;
		this.ocpdTwelve = ocpdTwelve;
		this.inverterLocation = inverterLocation;
		this.inverterLocationOther = inverterLocationOther;
		this.inverterSameLocation = inverterSameLocation;
		this.numberModulesACCircuitThirteen = numberModulesACCircuitThirteen;
		this.numberModulesACCircuitFourteen = numberModulesACCircuitFourteen;
		this.numberModulesACCircuitFifteen = numberModulesACCircuitFifteen;
		this.numberModulesACCircuitSixteen = numberModulesACCircuitSixteen;
		this.numberModulesACCircuitSeventeen = numberModulesACCircuitSeventeen;
		this.numberModulesACCircuitEightteen = numberModulesACCircuitEightteen;
		this.numberModulesACCircuitNineteen = numberModulesACCircuitNineteen;
		this.numberModulesACCircuitTwenty = numberModulesACCircuitTwenty;
		this.numberModulesACCircuitTwentyOne = numberModulesACCircuitTwentyOne;
		this.numberModulesACCircuitTwentyTwo = numberModulesACCircuitTwentyTwo;
		this.numberModulesACCircuitTwentyThree = numberModulesACCircuitTwentyThree;
		this.numberModulesACCircuitTwentyFour = numberModulesACCircuitTwentyFour;
		this.ocpdThirteen = ocpdThirteen;
		this.ocpdFourteen = ocpdFourteen;
		this.ocpdFifteen = ocpdFifteen;
		this.ocpdSixteen = ocpdSixteen;
		this.ocpdSeventeen = ocpdSeventeen;
		this.ocpdEightteen = ocpdEightteen;
		this.ocpdNineteen = ocpdNineteen;
		this.ocpdTwenty = ocpdTwenty;
		this.ocpdTwentyOne = ocpdTwentyOne;
		this.ocpdTwentyTwo = ocpdTwentyTwo;
		this.ocpdTwentyThree = ocpdTwentyThree;
		this.ocpdTwentyFour = ocpdTwentyFour;

		this.stringSix = stringSix;
		this.stringSeven = stringSeven;
		this.stringEight = stringEight;
		this.stringNine = stringNine;
		this.stringTen = stringTen;
		this.stringEleven = stringEleven;
		this.stringTwelve = stringTwelve;
		this.secondStringSix = secondStringSix;
		this.secondStringSeven = secondStringSeven;
		this.secondStringEight = secondStringEight;
		this.secondStringNine = secondStringNine;
		this.secondStringTen = secondStringTen;
		this.secondStringEleven = secondStringEleven;
		this.secondStringTwelve = secondStringTwelve;
		this.thirdStringSix = thirdStringSix;
		this.thirdStringSeven = thirdStringSeven;
		this.thirdStringEight = thirdStringEight;
		this.thirdStringNine = thirdStringNine;
		this.thirdStringTen = thirdStringTen;
		this.thirdStringEleven = thirdStringEleven;
		this.thirdStringTwelve = thirdStringTwelve;
		this.fourthStringSix = fourthStringSix;
		this.fourthStringSeven = fourthStringSeven;
		this.fourthStringEight = fourthStringEight;
		this.fourthStringNine = fourthStringNine;
		this.fourthStringTen = fourthStringTen;
		this.fourthStringEleven = fourthStringEleven;
		this.fourthStringTwelve = fourthStringTwelve;
		this.fifthStringSix = fifthStringSix;
		this.fifthStringSeven = fifthStringSeven;
		this.fifthStringEight = fifthStringEight;
		this.fifthStringNine = fifthStringNine;
		this.fifthStringTen = fifthStringTen;
		this.fifthStringEleven = fifthStringEleven;
		this.fifthStringTwelve = fifthStringTwelve;
		this.microInverter = microInverter;

		setStringFiveInt(stringFive);
		setStringFourInt(stringFour);
		setStringThreeInt(stringThree);
		setStringTwoInt(stringTwo);
		setStringOneInt(stringOne);
		setSecondStringFiveInt(secondStringFive);
		setSecondStringFourInt(secondStringFour);
		setSecondStringThreeInt(secondStringThree);
		setSecondStringTwoInt(secondStringTwo);
		setSecondStringOneInt(secondStringOne);

		this.roofOrOpenFrame = roofOrOpenFrame;
		this.circuitUnderGround = circuitUnderGround;
		this.inverterInstalledOnRoof = inverterInstalledOnRoof;
		this.enteranyTransformer = enteranyTransformer;
		this.patioMounted = patioMounted;
	}

	public PermitArrayEntityResultSecond() {
		super();
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

	public Long getPvModuleModEl() {
		return pvModuleModEl;
	}

	public void setPvModuleModEl(Long pvModuleModEl) {
		this.pvModuleModEl = pvModuleModEl;
	}

	public Long getInverterModel() {
		return inverterModel;
	}

	public void setInverterModel(Long inverterModel) {
		this.inverterModel = inverterModel;
	}

	public Long getSecondInverterModel() {
		return secondInverterModel;
	}

	public void setSecondInverterModel(Long secondInverterModel) {
		this.secondInverterModel = secondInverterModel;
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

	/**
	 * @return the qteOfBattery
	 */
	public String getQteOfBattery() {
		return qteOfBattery;
	}

	/**
	 * @param qteOfBattery the qteOfBattery to set
	 */
	public void setQteOfBattery(String qteOfBattery) {
		this.qteOfBattery = qteOfBattery;
	}

	/**
	 * @return the systemOptimizerModel
	 */
	public Long getSystemOptimizerModel() {
		return systemOptimizerModel;
	}

	/**
	 * @param systemOptimizerModel the systemOptimizerModel to set
	 */
	public void setSystemOptimizerModel(Long systemOptimizerModel) {
		this.systemOptimizerModel = systemOptimizerModel;
	}

	/************************************
	 * geter & setter variable formatage
	 **********************************************/

	/**
	 * @param stringOne the stringOne to set
	 */
	public void setStringOneInt(String stringOne) {
		if (stringOne != null && !stringOne.equals("null") && !stringOne.equals("")) {
			this.stringOne = Integer.parseInt(stringOne);
		} else {
			this.stringOne = null;
		}

	}

	public Integer getStringOneInt() {
		return stringOne;
	}

	/**
	 * @param stringTwo the stringTwo to set
	 */
	public void setStringTwoInt(String stringTwo) {
		if (stringTwo != null && !stringTwo.equals("null") && !stringTwo.equals("")) {
			this.stringTwo = Integer.parseInt(stringTwo);
		} else {
			this.stringTwo = null;
		}
	}

	public Integer getStringTwoInt() {
		return stringTwo;
	}

	/**
	 * @param stringThree the stringThree to set
	 */
	public void setStringThreeInt(String stringThree) {
		if (stringThree != null && !stringThree.equals("null") && !stringThree.equals("")) {
			this.stringThree = Integer.parseInt(stringThree);
		} else {
			this.stringThree = null;
		}
	}

	public Integer getStringThreeInt() {
		return stringThree;
	}

	/**
	 * @param stringFour the stringFour to set
	 */
	public void setStringFourInt(String stringFour) {
		if (stringFour != null && !stringFour.equals("null") && !stringFour.equals("")) {
			this.stringFour = Integer.parseInt(stringFour);
		} else {
			this.stringFour = null;
		}
	}

	public Integer getStringFourInt() {
		return stringFour;
	}

	/**
	 * @param stringFive the stringFive to set
	 */
	public void setStringFiveInt(String stringFive) {
		if (stringFive != null && !stringFive.equals("null") && !stringFive.equals("")) {
			this.stringFive = Integer.parseInt(stringFive);
		} else {
			this.stringFive = null;
		}
	}

	public Integer getStringFiveInt() {
		return stringFive;
	}

	/**
	 * @param secondStringOne the secondStringOne to set
	 */
	public void setSecondStringOneInt(String secondStringOne) {
		if (secondStringOne != null && !secondStringOne.equals("null") && !secondStringOne.equals("")) {
			this.secondStringOne = Integer.parseInt(secondStringOne);
		} else {
			this.secondStringOne = null;
		}
	}

	public Integer getSecondStringOneInt() {
		return secondStringOne;
	}

	/**
	 * @param secondStringTwo the secondStringTwo to set
	 */
	public void setSecondStringTwoInt(String secondStringTwo) {
		if (secondStringTwo != null && !secondStringTwo.equals("null") && !secondStringTwo.equals("")) {
			this.secondStringTwo = Integer.parseInt(secondStringTwo);
		} else {
			this.secondStringTwo = null;
		}
	}

	public Integer getSecondStringTwoInt() {
		return secondStringTwo;
	}

	/**
	 * @param secondStringThree the secondStringThree to set
	 */
	public void setSecondStringThreeInt(String secondStringThree) {
		if (secondStringThree != null && !secondStringThree.equals("null") && !secondStringThree.equals("")) {
			this.secondStringThree = Integer.parseInt(secondStringThree);
		} else {
			this.secondStringThree = null;
		}
	}

	public Integer getSecondStringThreeInt() {
		return secondStringThree;
	}

	/**
	 * @param secondStringFour the secondStringFour to set
	 */
	public void setSecondStringFourInt(String secondStringFour) {
		if (secondStringFour != null && !secondStringFour.equals("null") && !secondStringFour.equals("")) {
			this.secondStringFour = Integer.parseInt(secondStringFour);
		} else {
			this.secondStringFour = null;
		}
	}

	public Integer getSecondStringFourInt() {
		return secondStringFour;
	}

	/**
	 * @param secondStringFive the secondStringFive to set
	 */
	public void setSecondStringFiveInt(String secondStringFive) {
		if (secondStringFive != null && !secondStringFive.equals("null") && !secondStringFive.equals("")) {
			this.secondStringFive = Integer.parseInt(secondStringFive);
		} else {
			this.secondStringFive = null;
		}
	}

	public Integer getSecondStringFiveInt() {
		return secondStringFive;
	}

	/**
	 * @return the thirdInverterModel
	 */
	public Long getThirdInverterModel() {
		return thirdInverterModel;
	}

	/**
	 * @return the fourthInverterModel
	 */
	public Long getFourthInverterModel() {
		return fourthInverterModel;
	}

	/**
	 * @return the fifthInverterModel
	 */
	public Long getFifthInverterModel() {
		return fifthInverterModel;
	}

	/**
	 * @return the microInverterModel
	 */
	public String getMicroInverterModel() {
		return microInverterModel;
	}

	/**
	 * @return the numberModulesACCircuitOne
	 */
	public String getNumberModulesACCircuitOne() {
		return numberModulesACCircuitOne;
	}

	/**
	 * @return the numberModulesACCircuitTwo
	 */
	public String getNumberModulesACCircuitTwo() {
		return numberModulesACCircuitTwo;
	}

	/**
	 * @return the numberModulesACCircuitThree
	 */
	public String getNumberModulesACCircuitThree() {
		return numberModulesACCircuitThree;
	}

	/**
	 * @return the numberModulesACCircuitFour
	 */
	public String getNumberModulesACCircuitFour() {
		return numberModulesACCircuitFour;
	}

	/**
	 * @return the numberModulesACCircuitFive
	 */
	public String getNumberModulesACCircuitFive() {
		return numberModulesACCircuitFive;
	}

	/**
	 * @param thirdInverterModel the thirdInverterModel to set
	 */
	public void setThirdInverterModel(Long thirdInverterModel) {
		this.thirdInverterModel = thirdInverterModel;
	}

	/**
	 * @param thirdStringOne the thirdStringOne to set
	 */
	public void setThirdStringOne(Integer thirdStringOne) {
		this.thirdStringOne = thirdStringOne;
	}

	/**
	 * @param thirdStringTwo the thirdStringTwo to set
	 */
	public void setThirdStringTwo(Integer thirdStringTwo) {
		this.thirdStringTwo = thirdStringTwo;
	}

	/**
	 * @param thirdStringThree the thirdStringThree to set
	 */
	public void setThirdStringThree(Integer thirdStringThree) {
		this.thirdStringThree = thirdStringThree;
	}

	/**
	 * @param thirdStringFour the thirdStringFour to set
	 */
	public void setThirdStringFour(Integer thirdStringFour) {
		this.thirdStringFour = thirdStringFour;
	}

	/**
	 * @param thirdStringFive the thirdStringFive to set
	 */
	public void setThirdStringFive(Integer thirdStringFive) {
		this.thirdStringFive = thirdStringFive;
	}

	/**
	 * @param fourthInverterModel the fourthInverterModel to set
	 */
	public void setFourthInverterModel(Long fourthInverterModel) {
		this.fourthInverterModel = fourthInverterModel;
	}

	/**
	 * @param fourthStringOne the fourthStringOne to set
	 */
	public void setFourthStringOne(Integer fourthStringOne) {
		this.fourthStringOne = fourthStringOne;
	}

	/**
	 * @param fourthStringTwo the fourthStringTwo to set
	 */
	public void setFourthStringTwo(Integer fourthStringTwo) {
		this.fourthStringTwo = fourthStringTwo;
	}

	/**
	 * @param fourthStringThree the fourthStringThree to set
	 */
	public void setFourthStringThree(Integer fourthStringThree) {
		this.fourthStringThree = fourthStringThree;
	}

	/**
	 * @param fourthStringFour the fourthStringFour to set
	 */
	public void setFourthStringFour(Integer fourthStringFour) {
		this.fourthStringFour = fourthStringFour;
	}

	/**
	 * @param fourthStringFive the fourthStringFive to set
	 */
	public void setFourthStringFive(Integer fourthStringFive) {
		this.fourthStringFive = fourthStringFive;
	}

	/**
	 * @param fifthInverterModel the fifthInverterModel to set
	 */
	public void setFifthInverterModel(Long fifthInverterModel) {
		this.fifthInverterModel = fifthInverterModel;
	}

	/**
	 * @param fifthStringOne the fifthStringOne to set
	 */
	public void setFifthStringOne(Integer fifthStringOne) {
		this.fifthStringOne = fifthStringOne;
	}

	/**
	 * @param fifthStringTwo the fifthStringTwo to set
	 */
	public void setFifthStringTwo(Integer fifthStringTwo) {
		this.fifthStringTwo = fifthStringTwo;
	}

	/**
	 * @param fifthStringThree the fifthStringThree to set
	 */
	public void setFifthStringThree(Integer fifthStringThree) {
		this.fifthStringThree = fifthStringThree;
	}

	/**
	 * @param fifthStringFour the fifthStringFour to set
	 */
	public void setFifthStringFour(Integer fifthStringFour) {
		this.fifthStringFour = fifthStringFour;
	}

	/**
	 * @param fifthStringFive the fifthStringFive to set
	 */
	public void setFifthStringFive(Integer fifthStringFive) {
		this.fifthStringFive = fifthStringFive;
	}

	/**
	 * @param microInverterModel the microInverterModel to set
	 */
	public void setMicroInverterModel(String microInverterModel) {
		this.microInverterModel = microInverterModel;
	}

	public String getMicroInverterManufacturer() {
		return microInverterManufacturer;
	}

	public void setMicroInverterManufacturer(String microInverterManufacturer) {
		this.microInverterManufacturer = microInverterManufacturer;
	}

	/**
	 * @param numberModulesACCircuitOne the numberModulesACCircuitOne to set
	 */
	public void setNumberModulesACCircuitOne(String numberModulesACCircuitOne) {
		this.numberModulesACCircuitOne = numberModulesACCircuitOne;
	}

	/**
	 * @param numberModulesACCircuitTwo the numberModulesACCircuitTwo to set
	 */
	public void setNumberModulesACCircuitTwo(String numberModulesACCircuitTwo) {
		this.numberModulesACCircuitTwo = numberModulesACCircuitTwo;
	}

	/**
	 * @param numberModulesACCircuitThree the numberModulesACCircuitThree to set
	 */
	public void setNumberModulesACCircuitThree(String numberModulesACCircuitThree) {
		this.numberModulesACCircuitThree = numberModulesACCircuitThree;
	}

	/**
	 * @param numberModulesACCircuitFour the numberModulesACCircuitFour to set
	 */
	public void setNumberModulesACCircuitFour(String numberModulesACCircuitFour) {
		this.numberModulesACCircuitFour = numberModulesACCircuitFour;
	}

	/**
	 * @param numberModulesACCircuitFive the numberModulesACCircuitFive to set
	 */
	public void setNumberModulesACCircuitFive(String numberModulesACCircuitFive) {
		this.numberModulesACCircuitFive = numberModulesACCircuitFive;
	}

	/**
	 * @return the numberModulesACCircuitSix
	 */
	public String getNumberModulesACCircuitSix() {
		return numberModulesACCircuitSix;
	}

	/**
	 * @param numberModulesACCircuitSix the numberModulesACCircuitSix to set
	 */
	public void setNumberModulesACCircuitSix(String numberModulesACCircuitSix) {
		this.numberModulesACCircuitSix = numberModulesACCircuitSix;
	}

	/**
	 * @return the numberModulesACCircuitSeven
	 */
	public String getNumberModulesACCircuitSeven() {
		return numberModulesACCircuitSeven;
	}

	/**
	 * @param numberModulesACCircuitSeven the numberModulesACCircuitSeven to set
	 */
	public void setNumberModulesACCircuitSeven(String numberModulesACCircuitSeven) {
		this.numberModulesACCircuitSeven = numberModulesACCircuitSeven;
	}

	/**
	 * @return the numberModulesACCircuitEight
	 */
	public String getNumberModulesACCircuitEight() {
		return numberModulesACCircuitEight;
	}

	/**
	 * @param numberModulesACCircuitEight the numberModulesACCircuitEight to set
	 */
	public void setNumberModulesACCircuitEight(String numberModulesACCircuitEight) {
		this.numberModulesACCircuitEight = numberModulesACCircuitEight;
	}

	/**
	 * @return the numberModulesACCircuitNine
	 */
	public String getNumberModulesACCircuitNine() {
		return numberModulesACCircuitNine;
	}

	/**
	 * @param numberModulesACCircuitNine the numberModulesACCircuitNine to set
	 */
	public void setNumberModulesACCircuitNine(String numberModulesACCircuitNine) {
		this.numberModulesACCircuitNine = numberModulesACCircuitNine;
	}

	/**
	 * @return the numberModulesACCircuitTen
	 */
	public String getNumberModulesACCircuitTen() {
		return numberModulesACCircuitTen;
	}

	/**
	 * @param numberModulesACCircuitTen the numberModulesACCircuitTen to set
	 */
	public void setNumberModulesACCircuitTen(String numberModulesACCircuitTen) {
		this.numberModulesACCircuitTen = numberModulesACCircuitTen;
	}

	/**
	 * @return the numberModulesACCircuitEleven
	 */
	public String getNumberModulesACCircuitEleven() {
		return numberModulesACCircuitEleven;
	}

	/**
	 * @param numberModulesACCircuitEleven the numberModulesACCircuitEleven to set
	 */
	public void setNumberModulesACCircuitEleven(String numberModulesACCircuitEleven) {
		this.numberModulesACCircuitEleven = numberModulesACCircuitEleven;
	}

	/**
	 * @return the numberModulesACCircuitTweleve
	 */
	public String getNumberModulesACCircuitTweleve() {
		return numberModulesACCircuitTweleve;
	}

	/**
	 * @param numberModulesACCircuitTweleve the numberModulesACCircuitTweleve to set
	 */
	public void setNumberModulesACCircuitTweleve(String numberModulesACCircuitTweleve) {
		this.numberModulesACCircuitTweleve = numberModulesACCircuitTweleve;
	}

	public Boolean getCarportMounted() {
		return carportMounted;
	}

	public void setCarportMounted(Boolean carportMounted) {
		this.carportMounted = carportMounted;
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

	public String getStringOneStr() {
		return stringOneStr;
	}

	public void setStringOneStr(String stringOneStr) {
		this.stringOneStr = stringOneStr;
	}

	public String getStringTwoStr() {
		return stringTwoStr;
	}

	public void setStringTwoStr(String stringTwoStr) {
		this.stringTwoStr = stringTwoStr;
	}

	public String getStringThreeStr() {
		return stringThreeStr;
	}

	public void setStringThreeStr(String stringThreeStr) {
		this.stringThreeStr = stringThreeStr;
	}

	public String getStringFourStr() {
		return stringFourStr;
	}

	public void setStringFourStr(String stringFourStr) {
		this.stringFourStr = stringFourStr;
	}

	public String getStringFiveStr() {
		return stringFiveStr;
	}

	public void setStringFiveStr(String stringFiveStr) {
		this.stringFiveStr = stringFiveStr;
	}

	public String getSecondStringOneStr() {
		return secondStringOneStr;
	}

	public void setSecondStringOneStr(String secondStringOneStr) {
		this.secondStringOneStr = secondStringOneStr;
	}

	public String getSecondStringTwoStr() {
		return secondStringTwoStr;
	}

	public void setSecondStringTwoStr(String secondStringTwoStr) {
		this.secondStringTwoStr = secondStringTwoStr;
	}

	public String getSecondStringThreeStr() {
		return secondStringThreeStr;
	}

	public void setSecondStringThreeStr(String secondStringThreeStr) {
		this.secondStringThreeStr = secondStringThreeStr;
	}

	public String getSecondStringFourStr() {
		return secondStringFourStr;
	}

	public void setSecondStringFourStr(String secondStringFourStr) {
		this.secondStringFourStr = secondStringFourStr;
	}

	public String getSecondStringFiveStr() {
		return secondStringFiveStr;
	}

	public void setSecondStringFiveStr(String secondStringFiveStr) {
		this.secondStringFiveStr = secondStringFiveStr;
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

//	public void setStringOne(Integer stringOne) {
//		this.stringOne = stringOne;
//	}
//
//
//
//
//	public void setStringTwo(Integer stringTwo) {
//		this.stringTwo = stringTwo;
//	}
//
//
//
//
//	public void setStringThree(Integer stringThree) {
//		this.stringThree = stringThree;
//	}
//
//
//
//
//	public void setStringFour(Integer stringFour) {
//		this.stringFour = stringFour;
//	}
//
//
//
//
//	public void setStringFive(Integer stringFive) {
//		this.stringFive = stringFive;
//	}
//
//
//
//
//	public void setSecondStringOne(Integer secondStringOne) {
//		this.secondStringOne = secondStringOne;
//	}
//
//
//
//
//	public void setSecondStringTwo(Integer secondStringTwo) {
//		this.secondStringTwo = secondStringTwo;
//	}
//
//
//
//
//	public void setSecondStringThree(Integer secondStringThree) {
//		this.secondStringThree = secondStringThree;
//	}
//
//
//
//
//	public void setSecondStringFour(Integer secondStringFour) {
//		this.secondStringFour = secondStringFour;
//	}
//
//
//
//
//	public void setSecondStringFive(Integer secondStringFive) {
//		this.secondStringFive = secondStringFive;
//	}

	/**
	 * @return the thirdStringOne
	 */
	public Integer getThirdStringOne() {
		return thirdStringOne;
	}

	/**
	 * @return the thirdStringTwo
	 */
	public Integer getThirdStringTwo() {
		return thirdStringTwo;
	}

	/**
	 * @return the thirdStringThree
	 */
	public Integer getThirdStringThree() {
		return thirdStringThree;
	}

	/**
	 * @return the thirdStringFour
	 */
	public Integer getThirdStringFour() {
		return thirdStringFour;
	}

	/**
	 * @return the thirdStringFive
	 */
	public Integer getThirdStringFive() {
		return thirdStringFive;
	}

	/**
	 * @return the fourthStringOne
	 */
	public Integer getFourthStringOne() {
		return fourthStringOne;
	}

	/**
	 * @return the fourthStringTwo
	 */
	public Integer getFourthStringTwo() {
		return fourthStringTwo;
	}

	/**
	 * @return the fourthStringThree
	 */
	public Integer getFourthStringThree() {
		return fourthStringThree;
	}

	/**
	 * @return the fourthStringFour
	 */
	public Integer getFourthStringFour() {
		return fourthStringFour;
	}

	/**
	 * @return the fourthStringFive
	 */
	public Integer getFourthStringFive() {
		return fourthStringFive;
	}

	/**
	 * @return the fifthStringOne
	 */
	public Integer getFifthStringOne() {
		return fifthStringOne;
	}

	/**
	 * @return the fifthStringTwo
	 */
	public Integer getFifthStringTwo() {
		return fifthStringTwo;
	}

	/**
	 * @return the fifthStringThree
	 */
	public Integer getFifthStringThree() {
		return fifthStringThree;
	}

	/**
	 * @return the fifthStringFour
	 */
	public Integer getFifthStringFour() {
		return fifthStringFour;
	}

	/**
	 * @return the fifthStringFive
	 */
	public Integer getFifthStringFive() {
		return fifthStringFive;
	}

	public String getStringOne() {
		return stringOneStr;
	}

	public void setStringOne(String stringOne) {
		this.stringOneStr = stringOne;
	}

	public String getStringTwo() {
		return stringTwoStr;
	}

	public void setStringTwo(String stringTwo) {
		this.stringTwoStr = stringTwo;
	}

	public String getStringThree() {
		return stringThreeStr;
	}

	public void setStringThree(String stringThree) {
		this.stringThreeStr = stringThree;
	}

	public String getStringFour() {
		return stringFourStr;
	}

	public void setStringFour(String stringFour) {
		this.stringFourStr = stringFour;
	}

	public String getStringFive() {
		return stringFiveStr;
	}

	public void setStringFive(String stringFive) {
		this.stringFiveStr = stringFive;
	}

	public String getSecondStringOne() {
		return secondStringOneStr;
	}

	public void setSecondStringOne(String secondStringOne) {
		this.secondStringOneStr = secondStringOne;
	}

	public String getSecondStringTwo() {
		return secondStringTwoStr;
	}

	public void setSecondStringTwo(String secondStringTwo) {
		this.secondStringTwoStr = secondStringTwo;
	}

	public String getSecondStringThree() {
		return secondStringThreeStr;
	}

	public void setSecondStringThree(String secondStringThree) {
		this.secondStringThreeStr = secondStringThree;
	}

	public String getSecondStringFour() {
		return secondStringFourStr;
	}

	public void setSecondStringFour(String secondStringFour) {
		this.secondStringFourStr = secondStringFour;
	}

	public String getSecondStringFive() {
		return secondStringFiveStr;
	}

	public void setSecondStringFive(String secondStringFive) {
		this.secondStringFiveStr = secondStringFive;
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

	public Long getMicroInverter() {
		return microInverter;
	}

	public void setMicroInverter(Long microInverter) {
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

	public Boolean getInverterInstalledOnRoof() {
		return inverterInstalledOnRoof;
	}

	public void setInverterInstalledOnRoof(Boolean inverterInstalledOnRoof) {
		this.inverterInstalledOnRoof = inverterInstalledOnRoof;
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

	public void setStringOne(Integer stringOne) {
		this.stringOne = stringOne;
	}

	public void setStringTwo(Integer stringTwo) {
		this.stringTwo = stringTwo;
	}

	public void setStringThree(Integer stringThree) {
		this.stringThree = stringThree;
	}

	public void setStringFour(Integer stringFour) {
		this.stringFour = stringFour;
	}

	public void setStringFive(Integer stringFive) {
		this.stringFive = stringFive;
	}

	public void setSecondStringOne(Integer secondStringOne) {
		this.secondStringOne = secondStringOne;
	}

	public void setSecondStringTwo(Integer secondStringTwo) {
		this.secondStringTwo = secondStringTwo;
	}

	public void setSecondStringThree(Integer secondStringThree) {
		this.secondStringThree = secondStringThree;
	}

	public void setSecondStringFour(Integer secondStringFour) {
		this.secondStringFour = secondStringFour;
	}

	public void setSecondStringFive(Integer secondStringFive) {
		this.secondStringFive = secondStringFive;
	}
	
}
