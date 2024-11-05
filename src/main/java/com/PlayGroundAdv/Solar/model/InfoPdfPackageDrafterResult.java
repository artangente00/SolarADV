package com.PlayGroundAdv.Solar.model;

import java.util.List;

import com.PlayGroundAdv.Solar.entity.PermitSketchEntity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class InfoPdfPackageDrafterResult {
	// PermitEntity
	private String company;
	private String homeOwnName;
	// PermitHomeSiteInfoEntity
	private String siteAddress;
	private String addressLine2;
	private String city;
	private String state;
	private String postalCode;
	private String propertyAPN;
	private String cityOther;
	private String utilityCompanyName;
	private String utilityCompanyNameOther;
	// permitPerojectSiteInfoEntity
	private String tallStructure;
	private String otherTallStructure;
	private Long roofMaterialType;
	private String roofMaterialTypeOther;
	private String crossSectionSize;
	private String textOtherSize;
	private String rafterTrussSpacing;
	private String textOtherRatfter;
	// PermitArraysEntity

	private Long pvModuleModEl;
	private String stringOne;
	private String stringTwo;
	private String stringThree;
	private String stringFour;
	private String stringFive;
	private String secondStringOne;
	private String secondStringTwo;
	private String secondStringThree;
	private String secondStringFour;
	private String secondStringFive;
	private Integer thirdStringOne;
	private Integer thirdStringTwo;
	private Integer thirdStringThree;
	private Integer thirdStringFour;
	private Integer thirdStringFive;
	private Integer fourthStringOne;
	private Integer fourthStringTwo;
	private Integer fourthStringThree;
	private Integer fourthStringFour;
	private Integer fourthStringFive;
	private Integer fifthStringOne;
	private Integer fifthStringTwo;
	private Integer fifthStringThree;
	private Integer fifthStringFour;
	private Integer fifthStringFive;
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
	private String inverterModel;
	private Long railRakingModel;
	private Long railRakingModelforGroundMounted;
	private Boolean roofMounted;
	private Boolean groundMounted;
	// PermitConduitContractorEntity
	private Integer qtySegmentOne;
	private Integer qtySegmentSix;
	// PermitAdditionalInfoEntity
	private String formatSize;
	private Boolean tiltLegs;
	// PermitLayoutEntity
	private String sketchNote;
	private String stanchionMaxSpacing;
	private String stanchionMaxSpacingOther;
	// List PermitSketchEntity
	private List<PermitSketchEntity> permitSketchEntity;
	private String homeOwnLastName;
	private String projectName;
	private String stanchionsType;

	private Boolean ignoreVents;
	private Boolean firesetbacks;
	private String firesetbacksNote;
	private Boolean fireVariance;
	private String fireVarianceNote;
	private Boolean modulesEncroaching;

	private String insertRoofNote;
	private String bosUploadComments;
	private String layoutSketchUploadCommentsLayout;
	private String layoutSketchUploadCommentsAddInfo;
	private String advUploadCommentsGoogle;
	private String advUploadCommentsNearMap;
	private String additionalInfoInstallationGuidelines;
	private String additionalInfoUploadComments;
	
	//A.B 05-24-2022 Rev-CR-2982-MOD-002
	private String moduleLayout;
	private String moduleLayoutOther;
	private String projectJurisdiction;
	private String projectJurisOther;

	public InfoPdfPackageDrafterResult(String company, String homeOwnName, String siteAddress, String addressLine2,
			String city, String state, String postalCode, String propertyAPN, String cityOther,
			String utilityCompanyName, String utilityCompanyNameOther, String tallStructure, String otherTallStructure,
			Long roofMaterialType, String roofMaterialTypeOther, String crossSectionSize, String textOtherSize,
			String rafterTrussSpacing, String textOtherRatfter, Long pvModuleModEl, String stringOne,
			String stringTwo, String stringThree, String stringFour, String stringFive, String secondStringOne,
			String secondStringTwo, String secondStringThree, String secondStringFour, String secondStringFive,
			Integer thirdStringOne, Integer thirdStringTwo, Integer thirdStringThree, Integer thirdStringFour,
			Integer thirdStringFive, Integer fourthStringOne, Integer fourthStringTwo, Integer fourthStringThree,
			Integer fourthStringFour, Integer fourthStringFive, Integer fifthStringOne, Integer fifthStringTwo,
			Integer fifthStringThree, Integer fifthStringFour, Integer fifthStringFive, Integer stringSix,
			Integer stringSeven, Integer stringEight, Integer stringNine, Integer stringTen, Integer stringEleven,
			Integer stringTwelve, Integer secondStringSix, Integer secondStringSeven, Integer secondStringEight,
			Integer secondStringNine, Integer secondStringTen, Integer secondStringEleven, Integer secondStringTwelve,
			Integer thirdStringSix, Integer thirdStringSeven, Integer thirdStringEight, Integer thirdStringNine,
			Integer thirdStringTen, Integer thirdStringEleven, Integer thirdStringTwelve, Integer fourthStringSix,
			Integer fourthStringSeven, Integer fourthStringEight, Integer fourthStringNine, Integer fourthStringTen,
			Integer fourthStringEleven, Integer fourthStringTwelve, Integer fifthStringSix, Integer fifthStringSeven,
			Integer fifthStringEight, Integer fifthStringNine, Integer fifthStringTen, Integer fifthStringEleven,
			Integer fifthStringTwelve, String inverterModel, Long railRakingModel,
			Long railRakingModelforGroundMounted, Boolean roofMounted,
			Boolean groundMounted, Integer qtySegmentOne, Integer qtySegmentSix, String formatSize,
			Boolean tiltLegs, String sketchNote, String stanchionMaxSpacing, String stanchionMaxSpacingOther,
			String homeOwnLastName, String projectName, String stanchionsType, Boolean ignoreVents,
			Boolean firesetbacks, String firesetbacksNote, Boolean fireVariance, String fireVarianceNote,
			Boolean modulesEncroaching, String insertRoofNote, String bosUploadComments,
			String layoutSketchUploadCommentsLayout, String layoutSketchUploadCommentsAddInfo,
			String advUploadCommentsGoogle, String advUploadCommentsNearMap,
			String additionalInfoInstallationGuidelines, String additionalInfoUploadComments, 
			String moduleLayout, String moduleLayoutOther, String projectJurisdiction, String projectJurisOther) {
		super();
		this.company = company;
		this.homeOwnName = homeOwnName;
		this.siteAddress = siteAddress;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.propertyAPN = propertyAPN;
		this.tallStructure = tallStructure;
		this.otherTallStructure = otherTallStructure;
		this.roofMaterialType = roofMaterialType;
		this.roofMaterialTypeOther = roofMaterialTypeOther;
		this.crossSectionSize = crossSectionSize;
		this.textOtherSize = textOtherSize;
		this.rafterTrussSpacing = rafterTrussSpacing;
		this.textOtherRatfter = textOtherRatfter;
		this.pvModuleModEl = pvModuleModEl;
		this.stringOne = stringOne;
		this.stringTwo = stringTwo;
		this.stringThree = stringThree;
		this.stringFour = stringFour;
		this.stringFive = stringFive;
		this.secondStringOne = secondStringOne;
		this.secondStringTwo = secondStringTwo;
		this.secondStringThree = secondStringThree;
		this.secondStringFour = secondStringFour;
		this.secondStringFive = secondStringFive;
		this.thirdStringOne = thirdStringOne;
		this.thirdStringTwo = thirdStringTwo;
		this.thirdStringThree = thirdStringThree;
		this.thirdStringFour = thirdStringFour;
		this.thirdStringFive = thirdStringFive;
		this.fourthStringOne = fourthStringOne;
		this.fourthStringTwo = fourthStringTwo;
		this.fourthStringThree = fourthStringThree;
		this.fourthStringFour = fourthStringFour;
		this.fourthStringFive = fourthStringFive;
		this.fifthStringOne = fifthStringOne;
		this.fifthStringTwo = fifthStringTwo;
		this.fifthStringThree = fifthStringThree;
		this.fifthStringFour = fifthStringFour;
		this.fifthStringFive = fifthStringFive;
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
		this.inverterModel = inverterModel;
		this.railRakingModel = railRakingModel;
		this.railRakingModelforGroundMounted = railRakingModelforGroundMounted;
		this.roofMounted = roofMounted;
		this.groundMounted = groundMounted;
		this.qtySegmentOne = qtySegmentOne;
		this.qtySegmentSix = qtySegmentSix;
		this.formatSize = formatSize;
		this.tiltLegs = tiltLegs;
		this.sketchNote = sketchNote;
		this.cityOther = cityOther;
		this.utilityCompanyName = utilityCompanyName;
		this.utilityCompanyNameOther = utilityCompanyNameOther;
		this.stanchionMaxSpacing = stanchionMaxSpacing;
		this.stanchionMaxSpacingOther = stanchionMaxSpacingOther;
		this.homeOwnLastName = homeOwnLastName;
		this.projectName = projectName;
		this.stanchionsType = stanchionsType;
		this.ignoreVents = ignoreVents;
		this.firesetbacks = firesetbacks;
		this.firesetbacksNote = firesetbacksNote;
		this.fireVariance = fireVariance;
		this.fireVarianceNote = fireVarianceNote;
		this.modulesEncroaching = modulesEncroaching;
		this.insertRoofNote = insertRoofNote;
		this.bosUploadComments = bosUploadComments;
		this.layoutSketchUploadCommentsLayout = layoutSketchUploadCommentsLayout;
		this.layoutSketchUploadCommentsAddInfo = layoutSketchUploadCommentsAddInfo;
		this.advUploadCommentsGoogle = advUploadCommentsGoogle;
		this.advUploadCommentsNearMap = advUploadCommentsNearMap;
		this.additionalInfoInstallationGuidelines = additionalInfoInstallationGuidelines;
		this.additionalInfoUploadComments = additionalInfoUploadComments;
		this.moduleLayout = moduleLayout;
		this.moduleLayoutOther = moduleLayoutOther;
		this.projectJurisdiction = projectJurisdiction;
		this.projectJurisOther = projectJurisOther;
	}
}
