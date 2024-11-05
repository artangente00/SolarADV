package com.PlayGroundAdv.Solar.service.generate_planset.pv_sheets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.AuthorityHavingJurisdctionEntity;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.ContractorInformationEntity;
import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;
import com.PlayGroundAdv.Solar.entity.Engineers;
import com.PlayGroundAdv.Solar.entity.Flashing;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.LeasePPAMeter;
import com.PlayGroundAdv.Solar.entity.PermitDrafterDataEntity;
import com.PlayGroundAdv.Solar.entity.PermitEngineerEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitSketchEntity;
import com.PlayGroundAdv.Solar.entity.ProposedSubPanel;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.entity.RoofAttachmentsEntity;
import com.PlayGroundAdv.Solar.entity.RoofMaterialType;
import com.PlayGroundAdv.Solar.entity.TiltLegs;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.entity.users.UserLicSectionsEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.AHJNotesModel;
import com.PlayGroundAdv.Solar.model.PermitAdditionalInfoEntityResult;
import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.TitleBlockValues;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJDesignCriteriaModel;
import com.PlayGroundAdv.Solar.model.ahj_library.GoverningCodesModel;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.repositories.AuthorityHavingJurisdctionRepository;
import com.PlayGroundAdv.Solar.repositories.MountTypeRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.AcCombinerSLCRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ProposedSubPanelRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.TiltLegsRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserLicSectionRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserSettingRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.ImageMapping;
import com.PlayGroundAdv.Solar.service.generate_planset.PlansetLogo_SignatureMappingService;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.GetPDFReaderService;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.ModuleInverterMfgQty;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e003.EssSystemMapping;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@Service
@Transactional
public class PlansetServicePV001 {

	final CheckValueTypesService checkValue;
	final GetPDFReaderService getPDFReaderService;
	final PlansetLogo_SignatureMappingService logoSignMapping;
	final ImageMapping imageMapping;
	final MountTypeRepository mountingRepo;
	final EssSystemMapping essSystemMapping;
	final ModuleInverterMfgQty moduleInverterMfgQty;
	final UserLicSectionRepository userLicSRepo;
	final AcCombinerSLCRepository acCombinerSLCRepo;
	final AuthorityHavingJurisdctionRepository ahjRepo;
	final TechnicalProblemMsg technicalProblemMsg;
	final UnverifiedEquipment unverifiedEquipment;
	final ProposedSubPanelRepository proposedSubPanelRepo;
	final TiltLegsRepository tiltLegsRepo;
	final SystemSize systemSize;
	final UserSettingRepository userSettingRepo;
	public PlansetServicePV001(CheckValueTypesService checkValue, 
			GetPDFReaderService getPDFReaderService, PlansetLogo_SignatureMappingService logoSignMapping,
			ImageMapping imageMapping, MountTypeRepository mountingRepo, EssSystemMapping essSystemMapping,
			ModuleInverterMfgQty moduleInverterMfgQty, UserLicSectionRepository userLicSRepo,
			AcCombinerSLCRepository acCombinerSLCRepo, AuthorityHavingJurisdctionRepository ahjRepo,
			TechnicalProblemMsg technicalProblemMsg, UnverifiedEquipment unverifiedEquipment,
			TiltLegsRepository tiltLegsRepo, ProposedSubPanelRepository proposedSubPanelRepo, 
			SystemSize systemSize, UserSettingRepository userSettingRepo) {
		super();
		this.checkValue = checkValue;
		this.getPDFReaderService = getPDFReaderService;
		this.logoSignMapping = logoSignMapping;
		this.imageMapping = imageMapping;
		this.mountingRepo = mountingRepo;
		this.essSystemMapping = essSystemMapping;
		this.moduleInverterMfgQty = moduleInverterMfgQty;
		this.userLicSRepo = userLicSRepo;
		this.acCombinerSLCRepo = acCombinerSLCRepo;
		this.ahjRepo = ahjRepo;
		this.unverifiedEquipment = unverifiedEquipment;
		this.tiltLegsRepo = tiltLegsRepo;
		this.proposedSubPanelRepo = proposedSubPanelRepo;
		this.technicalProblemMsg = technicalProblemMsg;
		this.systemSize = systemSize;
		this.userSettingRepo = userSettingRepo;
	}

	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	private static final String SOLAR_EDGE = "SolarEdge";
	private static final String CARPORT_INCLUDE_ROOF_MATERIAL = "The carport/patio cover will include roofing material under the modules";

	public void mapTitleBlock(PermitHomeSiteInfoEntity permitHomeSite, PermitEntity permitEntity, AcroFields form,
			int pv001Index, TitleBlockValues titleBlockValues) {
		try {
			if (permitHomeSite != null && permitHomeSite.getCity() != null) {

				if (permitEntity != null && checkValue.NotEquals(permitEntity.getReleasev2(), "")) {
					form.setField(pv001Index + "-" + "PV001-REVISION-1-SUBMITTAL-NAME", permitEntity.getReleasev2());
					titleBlockValues.setRevision1SubmittalName(permitEntity.getReleasev2());
					if (permitEntity.getUpdatedDatev2() != null) {
						form.setField(pv001Index + "-" + "PV001-REVISION-1", "REV #2");
						form.setField(pv001Index + "-" + "PV001-REVISION-1-DATE", permitEntity.getUpdatedDatev2());
						titleBlockValues.setRevision1("REV #2");
						titleBlockValues.setRevision1Date(permitEntity.getUpdatedDatev2());
					} else {
						form.setField(pv001Index + "-" + "PV001-REVISION-1", "");
						form.setField(pv001Index + "-" + "PV001-REVISION-1-DATE", "");
						titleBlockValues.setRevision1("");
						titleBlockValues.setRevision1Date("");
					}
				} else {
					form.setField(pv001Index + "-" + "PV001-REVISION-1-SUBMITTAL-NAME", "");
					form.setField(pv001Index + "-" + "PV001-REVISION-1", "");
					form.setField(pv001Index + "-" + "PV001-REVISION-1-DATE", "");
					titleBlockValues.setRevision1SubmittalName("");
					titleBlockValues.setRevision1("");
					titleBlockValues.setRevision1Date("");
				}

				if (permitEntity != null && checkValue.NotEquals(permitEntity.getReleasev3(), "")) {
					form.setField(pv001Index + "-" + "PV001-REVISION-2-SUBMITTAL-NAME", permitEntity.getReleasev3());
					titleBlockValues.setRevision2submittalName(permitEntity.getReleasev3());
					if (permitEntity.getUpdatedDatev3() != null) {
						form.setField(pv001Index + "-" + "PV001-REVISION-2", "REV #3");
						form.setField(pv001Index + "-" + "PV001-REVISION-2-DATE", permitEntity.getUpdatedDatev3());
						titleBlockValues.setRevision2("REV #3");
						titleBlockValues.setRevision2Date(permitEntity.getUpdatedDatev3());
					} else {
						form.setField(pv001Index + "-" + "PV001-REVISION-2", "");
						form.setField(pv001Index + "-" + "PV001-REVISION-2-DATE", "");
						titleBlockValues.setRevision2("");
						titleBlockValues.setRevision2Date("");
					}
				} else {
					form.setField(pv001Index + "-" + "PV001-REVISION-2-SUBMITTAL-NAME", "");
					form.setField(pv001Index + "-" + "PV001-REVISION-2", "");
					form.setField(pv001Index + "-" + "PV001-REVISION-2-DATE", "");
					titleBlockValues.setRevision2submittalName("");
					titleBlockValues.setRevision2("");
					titleBlockValues.setRevision2Date("");
				}

				if (permitEntity != null) {
					form.setField(pv001Index + "-" + "PV001-SUMBMITTALDATE",
							formatter.format(permitEntity.getUpdateDate()));
					titleBlockValues.setSumbmittaldate(formatter.format(permitEntity.getUpdateDate()));
				}
				form.setField(pv001Index + "-" + "PV001-SUBMITTALFORPERMIT", "SUBMIT FOR PERMIT");
				titleBlockValues.setSubmittalforpermit("SUBMIT FOR PERMIT");

				if (checkValue.Equals(permitHomeSite.getProjectJurisdiction(), "Other")
						&& permitHomeSite.getProjectJurisOther() != null) {
					form.setField(pv001Index + "-" + "BUILDINGDEPT", permitHomeSite.getProjectJurisOther());
				} else if (checkValue.Equals(permitHomeSite.getProjectJurisdiction(), "city")) {

					if (checkValue.NotEquals(permitHomeSite.getCity(), "Other")) {
						form.setField(pv001Index + "-" + "BUILDINGDEPT", "City of " + permitHomeSite.getCity());

					} else if (permitHomeSite.getProjectJurisOther() != null) {
						form.setField(pv001Index + "-" + "BUILDINGDEPT",
								"City of " + permitHomeSite.getProjectJurisOther());
					} else
						form.setField(pv001Index + "-" + "BUILDINGDEPT", "City of " + "");

				} else if (permitHomeSite.getProjectJurisdiction() != null
						&& permitHomeSite.getProjectJurisdiction().contains("County")) {

					if (checkValue.NotEquals(permitHomeSite.getCity(), "Other")) {
						form.setField(pv001Index + "-" + "BUILDINGDEPT", permitHomeSite.getProjectJurisdiction());
					} else if (permitHomeSite.getProjectJurisOther() != null) {
						form.setField(pv001Index + "-" + "BUILDINGDEPT",
								"County of " + permitHomeSite.getProjectJurisOther());
					} else
						form.setField(pv001Index + "-" + "BUILDINGDEPT", "County of " + "");

				}
				form.setField(pv001Index + "-" + "PV001-SHEET-INDEX", "PV-001");
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapContractorInfo(ContractorInformationEntity userContactInfo, AuthentificationEntity userAuthInfo,
			AcroFields form, PermitHomeSiteInfoEntity permitHomeSite, int pv001Index,
			TitleBlockValues titleBlockValues) {
		try {
			if (userAuthInfo != null && userContactInfo != null) {
				form.setField(pv001Index + "-" + "CONTRACTOR-NAME", userAuthInfo.getCompany());
			} else
				form.setField(pv001Index + "-" + "CONTRACTOR-NAME", "");

			if (userAuthInfo != null && userContactInfo != null) {
				String userInformationsAdress = "";
				String userInformationsCity = "";
				String userInformationsState = "";
				String userInformationsZipCode = "";
				if (userContactInfo.getMailingAddress() != null) {
					if (userContactInfo.getSecondMailingAddress() != null) {
						userInformationsAdress = userContactInfo.getMailingAddress() + " "
								+ userContactInfo.getSecondMailingAddress();
					} else {
						userInformationsAdress = userContactInfo.getMailingAddress() + " ";
					}
				}
				if (userContactInfo.getMailingCity() != null) {
					userInformationsCity = userContactInfo.getMailingCity() + ", ";
				}
				if (userContactInfo.getMailingState() != null) {
					userInformationsState = userContactInfo.getMailingState() + " ";
				}
				if (userContactInfo.getMailingZipCode() != null) {
					userInformationsZipCode = userContactInfo.getMailingZipCode() + "";
				}

				String contractorFirstAddress = userInformationsAdress;
				String contractorSecondAddress = userInformationsCity + userInformationsState + userInformationsZipCode;

				form.setField(pv001Index + "-" + "CONTRACTOR-ADDRESS1", contractorFirstAddress);
				form.setField(pv001Index + "-" + "CONTRACTOR-ADDRESS2", contractorSecondAddress);

				// CR-2199

				String contractorLN = "";
				try {
					if (permitHomeSite != null && checkValue.NotEquals(permitHomeSite.getState(), "")) {

						List<UserLicSectionsEntity> userLicSectionList = userLicSRepo
								.findByAuthentificationEntityIdAndContractorLicenceState(userAuthInfo.getId(),
										permitHomeSite.getState());
						if (userLicSectionList != null && !userLicSectionList.isEmpty()) {
							UserLicSectionsEntity userLicSection = userLicSectionList.get(0);

							if (userLicSection.getLicenseNumber() != null) {
								form.setField(pv001Index + "-" + "CONTRACTOR-LICENSE",
										userLicSection.getLicenseNumber());
							} else {
								form.setField(pv001Index + "-" + "CONTRACTOR-LICENSE", "");
							}

							if (checkValue.NotEquals(userLicSection.getLicenseNumber(), "")) {
								contractorLN = contractorLN + userLicSection.getLicenseNumber();
							}

							if (checkValue.Equals(userLicSection.getContractorLicenceState(), "CA")) {
								if (userLicSection.getLicTypeCode() != null) {
									for (int i = 0; i < userLicSection.getLicTypeCode().length; i++) {
										if (checkValue.NotEquals(userLicSection.getLicTypeCode()[i], "")) {
											contractorLN = contractorLN + " " + userLicSection.getLicTypeCode()[i];
										}
									}
								}
							} else {
								if (checkValue.NotEquals(userLicSection.getFirstLicTypeCodeOther(), "")) {
									contractorLN = contractorLN + " " + userLicSection.getFirstLicTypeCodeOther();
								}
								if (checkValue.NotEquals(userLicSection.getSecondLicTypeCodeOther(), "")) {
									contractorLN = contractorLN + " " + userLicSection.getSecondLicTypeCodeOther();
								}
								if (checkValue.NotEquals(userLicSection.getThirdLicTypeCodeOther(), "")) {
									contractorLN = contractorLN + " " + userLicSection.getThirdLicTypeCodeOther();
								}
							}
							if (contractorLN.length() <= 17) {
								form.setField(pv001Index + "-" + "PV001-CONTRACTOR-LN", contractorLN);
								titleBlockValues.setContractorLn(contractorLN);
							} else {
								form.setField(pv001Index + "-" + "PV001-CONTRACTOR-LN",
										userLicSection.getLicenseNumber());
								titleBlockValues.setContractorLn(userLicSection.getLicenseNumber());
							}
						} else {
							if (userContactInfo != null && checkValue
									.Equals(userContactInfo.getContractorLicenceState(), permitHomeSite.getState())) {
								if (userAuthInfo.getContractorCode() != null) {
									form.setField(pv001Index + "-" + "CONTRACTOR-LICENSE",
											userAuthInfo.getContractorCode());
								} else
									form.setField(pv001Index + "-" + "CONTRACTOR-LICENSE", "");

								String c46 = "";
								String c10 = "";
								String b = "";
								String contractorLicense = "";
								if (Boolean.TRUE.equals(userContactInfo.getContractorLic())) {
									c46 = "C46";
								}
								if (Boolean.TRUE.equals(userContactInfo.getContractorLicC10())) {
									c10 = "C10";
								}
								if (Boolean.TRUE.equals(userContactInfo.getContractorLicB())) {
									b = "B";
								}
								contractorLicense = userAuthInfo.getContractorCode() + " " + c46 + " " + c10 + " " + b;
								if (contractorLicense.length() <= 17) {
									form.setField(pv001Index + "-" + "PV001-CONTRACTOR-LN",
											userAuthInfo.getContractorCode() + " " + c46 + " " + c10 + " " + b);
									titleBlockValues.setContractorLn(
											userAuthInfo.getContractorCode() + " " + c46 + " " + c10 + " " + b);
								} else {
									form.setField(pv001Index + "-" + "PV001-CONTRACTOR-LN",
											userAuthInfo.getContractorCode());
									titleBlockValues.setContractorLn(userAuthInfo.getContractorCode());
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					technicalProblemMsg.traiterException(e);
				}

				// End CR-2199

				form.setField(pv001Index + "-" + "CONTRACTOR-PHONENUMBER",
						userContactInfo.getCompPhoneNum() != null ? userContactInfo.getCompPhoneNum() : "");
				form.setField(pv001Index + "-" + "CONTRACTOR-CONTACTNAME",
						userContactInfo.getContactFirstName() != null && userContactInfo.getContactLastName() != null
								? userContactInfo.getContactFirstName() + " " + userContactInfo.getContactLastName()
								: "");

				if (userContactInfo.getContactOptions() != null) {
					if (checkValue.Equals(userContactInfo.getContactOptions(), "firstContact")) {

						form.setField(pv001Index + "-" + "CONTRACTOR-CONTACTNUMBER",
								userContactInfo.getContactPhone() != null ? userContactInfo.getContactPhone() : "");
						form.setField(pv001Index + "-" + "CONTRACTOR-CONTACTEMAIL",
								userContactInfo.getContactEmail() != null ? userContactInfo.getContactEmail() : "");

					} else if (checkValue.Equals(userContactInfo.getContactOptions(), "secondContact")) {

						form.setField(pv001Index + "-" + "CONTRACTOR-CONTACTNUMBER",
								userContactInfo.getSecondContactPhone() != null
										? userContactInfo.getSecondContactPhone()
										: "");
						form.setField(pv001Index + "-" + "CONTRACTOR-CONTACTEMAIL",
								userContactInfo.getSecondContactEmail() != null
										? userContactInfo.getSecondContactEmail()
										: "");

					} else if (checkValue.Equals(userContactInfo.getContactOptions(), "thirdContact")) {

						form.setField(pv001Index + "-" + "CONTRACTOR-CONTACTNUMBER",
								userContactInfo.getThirdContactPhone() != null ? userContactInfo.getThirdContactPhone()
										: "");
						form.setField(pv001Index + "-" + "CONTRACTOR-CONTACTEMAIL",
								userContactInfo.getThirdContactEmail() != null ? userContactInfo.getThirdContactEmail()
										: "");

					} else
						form.setField(pv001Index + "-" + "CONTRACTOR-CONTACTNUMBER",
								userContactInfo.getContactOptionsOther());

				} else {
					form.setField(pv001Index + "-" + "CONTRACTOR-CONTACTNUMBER",
							userContactInfo.getContactPhone() != null ? userContactInfo.getContactPhone() : "");
					form.setField(pv001Index + "-" + "CONTRACTOR-CONTACTEMAIL",
							userContactInfo.getContactEmail() != null ? userContactInfo.getContactEmail() : "");
				}

				// A.B Reset Fields
				form.setField(pv001Index + "-" + "PV001-CONTRACTOR-CN",
						userAuthInfo.getCompany() != null ? userAuthInfo.getCompany() : "");
				form.setField(pv001Index + "-" + "PV001-CONTRACTOR-AD1", contractorFirstAddress);
				form.setField(pv001Index + "-" + "PV001-CONTRACTOR-AD2", contractorSecondAddress);
				form.setField(pv001Index + "-" + "PV001-CONTRACTOR-PH",
						userContactInfo.getContactPhone() != null ? userContactInfo.getContactPhone() : "");

				titleBlockValues.setContractorAd1(contractorFirstAddress);
				titleBlockValues.setContractorAd2(contractorSecondAddress);
				titleBlockValues.setContractorCn(userAuthInfo.getCompany() != null ? userAuthInfo.getCompany() : "");
				titleBlockValues.setContractorPh(
						userContactInfo.getContactPhone() != null ? userContactInfo.getContactPhone() : "");

				if (userContactInfo.getDesignBy() == null
						|| checkValue.Equals(userContactInfo.getDesignBy(), "firstContact")) {
					form.setField(pv001Index + "-" + "DESIGNBY-NAME",
							userContactInfo.getContactFirstName() + " " + userContactInfo.getContactLastName());
					form.setField(pv001Index + "-" + "DESIGNBY-NUMBER",
							userContactInfo.getContactPhone() != null ? userContactInfo.getContactPhone() : "");
					form.setField(pv001Index + "-" + "DESIGNBY-EMAIL", userContactInfo.getContactEmail());// PR-058

				} else if (checkValue.Equals(userContactInfo.getDesignBy(), "secondContact")) {
					form.setField(pv001Index + "-" + "DESIGNBY-NAME", userContactInfo.getSecondContactFirstName() + " "
							+ userContactInfo.getSecondContactLastName());
					form.setField(pv001Index + "-" + "DESIGNBY-NUMBER",
							userContactInfo.getSecondContactPhone() != null ? userContactInfo.getSecondContactPhone()
									: "");
					form.setField(pv001Index + "-" + "DESIGNBY-EMAIL", userContactInfo.getSecondContactEmail());

				} else if (checkValue.Equals(userContactInfo.getDesignBy(), "thirdContact")) {
					form.setField(pv001Index + "-" + "DESIGNBY-NAME", userContactInfo.getThirdContact());
					form.setField(pv001Index + "-" + "DESIGNBY-NUMBER",
							userContactInfo.getThirdContactPhone() != null ? userContactInfo.getThirdContactPhone()
									: "");
					form.setField(pv001Index + "-" + "DESIGNBY-EMAIL", userContactInfo.getThirdContactEmail());

				} else if (checkValue.Equals(userContactInfo.getDesignBy(), "OtherDesignBy")) {
					if (userContactInfo.getDesignByOther() != null) {
						// 04-03-2019: M.A : PR-566
						form.setField(pv001Index + "-" + "DESIGNBY-NAME", userContactInfo.getDesignByOther());
						form.setField(pv001Index + "-" + "DESIGNBY-NUMBER", "");
						form.setField(pv001Index + "-" + "DESIGNBY-EMAIL", "");
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapPermitHomeInfo(PermitHomeSiteInfoEntity permitHomeSite, AcroFields form, PermitEntity permitEntity,
			PermitArrayEntityResultSecond permitArraysEntityResult, PermitAdvEntityResult permitAdvInfo,
			ElectricalUtilityEntity electricalCompany, int pv001Index, TitleBlockValues titleBlockValues,
			AHJDesignCriteriaModel designCriteria) {
		try {
			if (permitHomeSite != null) {
				String address = "";
				String secondAddress = "";
				String city = "";
				String state = "";
				String zipCode = "";

				if (permitHomeSite.getSiteAddress() != null) {
					address = permitHomeSite.getSiteAddress() + ", ";
					if (permitHomeSite.getAddressLine2() != null) {
						secondAddress = permitHomeSite.getAddressLine2();
					}
				}
				if (permitHomeSite.getCity() != null) {
					if (checkValue.NotEquals(permitHomeSite.getCity(), "Other")) {
						city = permitHomeSite.getCity() + ", ";
					} else {
						city = permitHomeSite.getCityOther() + ", ";
					}
				}
				if (permitHomeSite.getState() != null) {
					state = permitHomeSite.getState() + " ";
				}
				if (permitHomeSite.getPostalCode() != null) {
					zipCode = permitHomeSite.getPostalCode();
				}

				// CR-2114

				if ((checkValue.NotEquals(permitEntity.getHomeOwnName(), "")) && (permitEntity != null
						&& checkValue.NotEquals(permitEntity.getProjectName(), ""))) {
					form.setField(pv001Index + "-" + "HOMEOWNER-NAME-LABEL", "PROJECT CONTACT & LOCATION:");
					if (checkValue.NotEquals(permitEntity.getHomeOwnLastName(), "")) {
						form.setField(pv001Index + "-" + "HOMEOWNER-NAME",
								permitEntity.getHomeOwnLastName() + ", " + permitEntity.getHomeOwnName());
					} else {
						form.setField(pv001Index + "-" + "HOMEOWNER-NAME", permitEntity.getHomeOwnName());
					}
				} else if ((checkValue.NotEquals(permitEntity.getHomeOwnName(), "")) && permitEntity != null
						&& !(checkValue.NotEquals(permitEntity.getProjectName(), ""))) {
					form.setField(pv001Index + "-" + "HOMEOWNER-NAME-LABEL", "HOMEOWNER CONTACT & LOCATION:");
					if (checkValue.NotEquals(permitEntity.getHomeOwnLastName(), "")) {
						form.setField(pv001Index + "-" + "HOMEOWNER-NAME",
								permitEntity.getHomeOwnLastName() + ", " + permitEntity.getHomeOwnName());
					} else {
						form.setField(pv001Index + "-" + "HOMEOWNER-NAME", permitEntity.getHomeOwnName());
					}
				} else if (!(checkValue.NotEquals(permitEntity.getHomeOwnName(), ""))
						&& (permitEntity != null
								&& checkValue.NotEquals(permitEntity.getProjectName(), ""))) {
					if (checkValue.NotEquals(permitEntity.getHomeOwnLastName(), "")) {
						form.setField(pv001Index + "-" + "HOMEOWNER-NAME-LABEL", "HOMEOWNER CONTACT & LOCATION:");
						form.setField(pv001Index + "-" + "HOMEOWNER-NAME", permitEntity.getHomeOwnLastName());
					} else {
						form.setField(pv001Index + "-" + "HOMEOWNER-NAME-LABEL", "PROJECT CONTACT & LOCATION:");
						form.setField(pv001Index + "-" + "HOMEOWNER-NAME", "");
					}

				}

				if (permitEntity != null && checkValue.NotEquals(permitEntity.getProjectName(), "")) {
					form.setField(pv001Index + "-" + "PV001-HOMEOWNER-NAME1", permitEntity.getProjectName());
					titleBlockValues.setHomeownerName1(permitEntity.getProjectName());
				} else {
					if (checkValue.NotEquals(permitEntity.getHomeOwnLastName(), "")) {
						form.setField(pv001Index + "-" + "PV001-HOMEOWNER-NAME1",
								permitEntity.getHomeOwnLastName() + ", " + permitEntity.getHomeOwnName());
						titleBlockValues.setHomeownerName1(
								permitEntity.getHomeOwnLastName() + ", " + permitEntity.getHomeOwnName());
					} else {
						form.setField(pv001Index + "-" + "PV001-HOMEOWNER-NAME1", permitEntity.getHomeOwnName());
						titleBlockValues.setHomeownerName1(permitEntity.getHomeOwnName());
					}

				}

				if (checkValue.Equals(secondAddress, "")) {
					form.setField(pv001Index + "-" + "HOMEOWNER-ADDRESS1", address + city + state + zipCode);
					form.setField(pv001Index + "-" + "HOMEOWNER-ADDRESS1-1", address + city + state + zipCode);
					titleBlockValues.setHomeownerAd2(city + state + zipCode);
				} else {
					form.setField(pv001Index + "-" + "HOMEOWNER-ADDRESS1", address + secondAddress);
					form.setField(pv001Index + "-" + "HOMEOWNER-ADDRESS1-1", address + secondAddress);
					titleBlockValues.setHomeownerAd2(secondAddress);
				}
				if (checkValue.NotEquals(permitEntity.getHomeOwnLastName(), "")) {
					if (checkValue.NotEquals(permitEntity.getHomeOwnName(), "")) {
						form.setField(pv001Index + "-" + "HOMEOWNER-CONTACTNAME",
								permitEntity.getHomeOwnLastName() + ", " + permitEntity.getHomeOwnName());
					} else {
						form.setField(pv001Index + "-" + "HOMEOWNER-CONTACTNAME", permitEntity.getHomeOwnLastName());
					}

				} else {
					if (checkValue.NotEquals(permitEntity.getHomeOwnName(), "")) {
						form.setField(pv001Index + "-" + "HOMEOWNER-CONTACTNAME", permitEntity.getHomeOwnName());
					}
				}

				form.setField(pv001Index + "-" + "HOMEOWNER-NUMBER", permitHomeSite.getHomePhone());
				form.setField(pv001Index + "-" + "HOMEOWNER-EMAIL-ADDRESS", permitHomeSite.getEmailPhone());
				form.setField(pv001Index + "-" + "PROJECTAPN-SITEPLAN", permitHomeSite.getPropertyAPN());

				titleBlockValues.setHomeownerAd1(permitHomeSite.getSiteAddress());
				titleBlockValues.setProjectApn(permitHomeSite.getPropertyAPN());

				if (checkValue.Equals(permitHomeSite.getBuildingRisk(), "Other")) {
					form.setField(pv001Index + "-" + "RISK-CATEGORY", permitHomeSite.getBuildingRiskOther());
				} else if (checkValue.contains(permitHomeSite.getBuildingRisk(), "Risk Category ")) {
					// A.B 08-19-2021 PR-41
					form.setField(pv001Index + "-" + "RISK-CATEGORY",
							permitHomeSite.getBuildingRisk().replace("Risk Category ", ""));
				}
				if (permitArraysEntityResult != null
						&& checkValue.Equals(permitArraysEntityResult.getRoofMounted(), true)) {
					if (checkValue.Equals(permitHomeSite.getBuildingOccupancy(), "Other")) {
						form.setField(pv001Index + "-" + "BUILDING-OCCUPANCY", permitHomeSite.getTextOtherBuildOccup());
					} else {
						form.setField(pv001Index + "-" + "BUILDING-OCCUPANCY", permitHomeSite.getBuildingOccupancy());
					}
				}

				// FBM 8-31-2021 MOD-ADVP-2234-002 starts here
				if (permitArraysEntityResult != null
						&& (checkValue.Equals(permitArraysEntityResult.getGroundMounted(), true)
								|| checkValue.Equals(permitArraysEntityResult.getCarportMounted(), true)
								|| checkValue.Equals(permitArraysEntityResult.getPatioMounted(), true))) {
					form.setField(pv001Index + "-" + "BUILDING-OCCUPANCY", "U");
				}

				// FBM 8-31-2021 MOD-ADVP-2234-002 ends here
				if (designCriteria != null
						&& checkValue.isStringNotEmpty(designCriteria.getWindSpeed())) {
					form.setField(pv001Index + "-" + "ASCE7-10-WIND-SPEED", designCriteria.getWindSpeed());
				} else if (permitAdvInfo != null) {
					if (checkValue.NotEquals(permitAdvInfo.getWindSpeed(), "Other")) {
						form.setField(pv001Index + "-" + "ASCE7-10-WIND-SPEED", permitAdvInfo.getWindSpeed());
					} else {
						form.setField(pv001Index + "-" + "ASCE7-10-WIND-SPEED", permitAdvInfo.getWindSpeedOther());
					}
				}

				if (designCriteria != null
						&& checkValue.isStringNotEmpty(designCriteria.getExposureCategory())) {
					form.setField(pv001Index + "-" + "EXPOSURE-CATEGORY", designCriteria.getExposureCategory());
				} else if (permitHomeSite.getResidenceBindingCategory() != null) {
					if (checkValue.Equals(permitHomeSite.getResidenceBindingCategory(), "Other")
							&& permitHomeSite.getTextOtherExpo() != null) {
						form.setField(pv001Index + "-" + "EXPOSURE-CATEGORY", permitHomeSite.getTextOtherExpo());
					} else {
						form.setField(pv001Index + "-" + "EXPOSURE-CATEGORY",
								permitHomeSite.getResidenceBindingCategory());
					}
				}
				
				//A.B CR-PM-798-MOD-002
				
				form.setField(pv001Index + "-METER NUMBER", "METER NUMBER:");
				form.setField(pv001Index + "-PV001-METERNUMBER", permitHomeSite.getMeterNumber());
				String utility = checkValue.Equals(permitHomeSite.getUtilityCompanyName(), "Other") ? permitHomeSite.getUtilityCompanyNameOther() : electricalCompany.getUtilityCompanyName();
				if(checkValue.containsCaseInsensitive(utility,"Oncor") || checkValue.EqualsCaseInsensitive(utility, "CenterPoint Energy Houston Electric LLC")){
					form.setField(pv001Index + "-ESID NUMBER", "ESI ID NUMBER:");
					form.setField(pv001Index + "-PV001-ESIDNUMBER", permitHomeSite.getEsiidNumber());
				}
				if (designCriteria != null && checkValue.isStringNotEmpty(designCriteria.getSnowLoad())) {
					form.setField(pv001Index + "-" + "SNOW-LOAD", designCriteria.getSnowLoad());
					if (checkValue.Equals(designCriteria.getSnowLoad(), "0")) {
						form.setField(pv001Index + "-" + "SNOW-EXPOSURE", "N/A");
					} else {
						form.setField(pv001Index + "-" + "SNOW-EXPOSURE", "");
					}
				} else if (permitAdvInfo != null) {
					if (permitAdvInfo.getSnowLoad() == null) {
						form.setField(pv001Index + "-" + "SNOW-LOAD", "N/A");
						form.setField(pv001Index + "-" + "SNOW-EXPOSURE", "N/A");
					} else if (checkValue.Equals(permitAdvInfo.getSnowLoad(), "0")) {
						form.setField(pv001Index + "-" + "SNOW-LOAD", permitAdvInfo.getSnowLoad());
						form.setField(pv001Index + "-" + "SNOW-EXPOSURE", "N/A");
					} else {
						if (checkValue.Equals(permitAdvInfo.getSnowLoad(), "Other")) {
							form.setField(pv001Index + "-" + "SNOW-LOAD", permitAdvInfo.getSnowLoadOther());
						} else {
							form.setField(pv001Index + "-" + "SNOW-LOAD", permitAdvInfo.getSnowLoad());
						}

						form.setField(pv001Index + "-" + "SNOW-EXPOSURE", "");
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}


	public String getMountType(PermitArrayEntityResultSecond permitArraysEntityResult) {
		String mountType = "";
		try {
			
			if (permitArraysEntityResult != null
					&& checkValue.Equals(permitArraysEntityResult.getRoofMounted(), true)) {
				mountType = mountType + "ROOF";
			}
			if (permitArraysEntityResult != null
					&& checkValue.Equals(permitArraysEntityResult.getGroundMounted(), true)) {
				if (checkValue.Equals(mountType, "")) {
					mountType = mountType + "GROUND";
				} else
					mountType = mountType + ", GROUND";
			}
			if (permitArraysEntityResult != null
					&& checkValue.Equals(permitArraysEntityResult.getPatioMounted(), true)) {
				if (checkValue.Equals(mountType, "")) {
					mountType = mountType + "PATIO";
				} else
					mountType = mountType + ", PATIO";
			}
			if (permitArraysEntityResult != null
					&& checkValue.Equals(permitArraysEntityResult.getCarportMounted(), true)) {
				if (checkValue.Equals(mountType, "")) {
					mountType = mountType + "CARPORT";
				} else
					mountType = mountType + ", CARPORT";
			}
			int ind = mountType.lastIndexOf(",");
			if (ind >= 0)
				mountType = new StringBuilder(mountType).replace(ind, ind + 1, " AND").toString();
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}

		return mountType;
	}

	public void engineerMapping(PermitEngineerEntity permitNewInputs, AcroFields form, Engineers engineer,
			int pv001Index) {
		try {
			 if (engineer != null && permitNewInputs != null && checkValue
					.Equals(permitNewInputs.getApplicablEngineering(), "true")) {
				String engineercity = "";
				String engineerstate = "";
				String engineerzipCode = "";
				String address1 = "";
				if (engineer.getCity() != null) {
					engineercity = engineer.getCity()+ ", ";
				}
				if (engineer.getAddress() != null) {
					address1 = engineer.getAddress();
				}
				if (engineer.getState() != null) {
					engineerstate = engineer.getState();
				}
				if (engineer.getZipCode() != null) {
					engineerzipCode = engineer.getZipCode();
				}
				form.setField(pv001Index + "-" + "ENGINEER-NAME", engineer.getContact());
				form.setField(pv001Index + "-" + "ENGINEER-ADDRESS1", address1);
				form.setField(pv001Index + "-" + "ENGINER-ADDRESS2",
						engineercity + engineerstate + " " + engineerzipCode);
				form.setField(pv001Index + "-" + "ENGINEER-LICENSE", engineer.getLicense());
				form.setField(pv001Index + "-" + "ENGINEER-LICENSE-TYPE", engineer.getLicenseType());
				form.setField(pv001Index + "-" + "ENGINEER-PHONE", engineer.getPhone());
				form.setField(pv001Index + "-" + "ENGINEER-MOBILE-PHONE", engineer.getPhone());
				form.setField(pv001Index + "-" + "ENGINEER-EMAIL", engineer.getEmail());
			} else {
				form.setField(pv001Index + "-" + "ENGINEER-NAME", "");
				form.setField(pv001Index + "-" + "ENGINEER-ADDRESS1", "");
				form.setField(pv001Index + "-" + "ENGINER-ADDRESS2", "");
				form.setField(pv001Index + "-" + "ENGINEER-LICENSE", "");
				form.setField(pv001Index + "-" + "ENGINEER-LICENSE-TYPE", "");
				form.setField(pv001Index + "-" + "ENGINEER-PHONE", "");
				form.setField(pv001Index + "-" + "ENGINEER-MOBILE-PHONE", "");
				form.setField(pv001Index + "-" + "ENGINEER-EMAIL", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	// 05/15/2019 : CI :CR 2566 : SCOPE OF WORK mapping
	//A.B 04/11/2022 REV-CR-PM-2566-MOD-001 replace W/ with WITH
	public String getScopeOfWork(String mountType, PermitAdditionalInfoEntityResult permitAdditionalInfo) {
		String scopeOfWork = "";
		try {
			if (checkValue.NotEquals(mountType, "") && permitAdditionalInfo != null
					&& checkValue.Equals(permitAdditionalInfo.getGridTiedOrStandalone(), "Grid-Tied")
					&& !checkValue.NotEquals(permitAdditionalInfo.getBatteryStorage(), false)) {
				scopeOfWork = mountType + " MOUNTED PV (SOLAR) PROJECT GRID-TIED  W/O BATTERY STORAGE";

			} else if (checkValue.NotEquals(mountType, "") && permitAdditionalInfo != null
					&& checkValue.Equals(permitAdditionalInfo.getGridTiedOrStandalone(),
							"Off-Grid/Standalone")
					&& checkValue.Equals(permitAdditionalInfo.getBatteryStorage(), true)) {
				scopeOfWork = mountType + " MOUNTED PV (SOLAR) PROJECT STAND ALONE (OFF-GRID)  WITH BATTERY STORAGE";

			} else if (checkValue.NotEquals(mountType, "") && permitAdditionalInfo != null
					&& checkValue.Equals(permitAdditionalInfo.getGridTiedOrStandalone(), "Grid-Tied")
					&& checkValue.Equals(permitAdditionalInfo.getBatteryStorage(), true)) {
				scopeOfWork = mountType + " MOUNTED PV (SOLAR) PROJECT GRID-TIED  WITH BATTERY STORAGE";

			} else if (checkValue.NotEquals(mountType, "") && permitAdditionalInfo != null
					&& checkValue.Equals(permitAdditionalInfo.getGridTiedOrStandalone(),
							"Off-Grid/Standalone")
					&& checkValue.Equals(permitAdditionalInfo.getBatteryStorage(), false)) {
				scopeOfWork = mountType + " MOUNTED PV (SOLAR) PROJECT STAND ALONE (OFF-GRID)  W/O BATTERY STORAGE";
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}

		return scopeOfWork;
	}

	// 15/05/2019 :CI :CR 2566 Proposed Solar Equipment mapping
	public void proposedSolarEquipMapping(AcroFields form, int pv001Index,
			PermitArrayEntityResultSecond permitArraysEntityResult, Cmodulev2 moduleInfo, Inverters inverterInfo,
			Inverters microInverterInfo, RoofAttachmentsEntity stanchionFoot,
			PermitDrafterDataEntity permitDrafterDatanfo, RailRacking railRacking, RailRacking railRackingGround,
			RailRacking railRackingPatio, RailRacking railRackingCarport, DCCombinerDisconnectEntity rapidShutdownModel,
			PlansetUtils plansetUtils, DCOptimizerEntity dcOptimizer,
			Inverters secondInverterInfo, Inverters thirdInverterInfo, Inverters fourthInverterInfo,
			Inverters fifthInverterInfo, Long projectId) {
		try {

			// PV-MODULE-MODEL mapping
			if (permitArraysEntityResult != null
					&& checkValue.NotEquals(permitArraysEntityResult.getPvModuleModEl(), "")) {

				if (moduleInfo != null) {
					form.setField(pv001Index + "-" + "PV-MODULE-MODEL",
							moduleInfo.getManufacturerMappingValue() + " " + moduleInfo.getModelMappingValue());
				} else {
					form.setField(pv001Index + "-" + "PV-MODULE-MODEL", "");
				}
				form.setField(pv001Index + "-" + "PV-Module-QTY", plansetUtils.getModuleQty() + "");
			}

			// Inverter-MODEL mapping
			if (permitArraysEntityResult != null
					&& (checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "Neither")
							|| checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(),
									"System Optimizer"))) {
				if (inverterInfo != null) {
					form.setField(pv001Index + "-" + "INVERTER-MODEL",
							inverterInfo.getManufacturerMappingValue() + " " + inverterInfo.getModelMappingValue());
					form.setField(pv001Index + "-" + "INVERTER-QTY", plansetUtils.getInverterQty() + "");
				}
			} else if (permitArraysEntityResult != null
					&& checkValue.NotEquals(permitArraysEntityResult.getMicroInverter(), "")
					&& checkValue.NotEquals(permitArraysEntityResult.getMicroInverter(), "Fav Removed")
					&& checkValue.NotEquals(permitArraysEntityResult.getMicroInverter(), "Removed")
					&& checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(),
							"Micro Inverter")) {
				if (microInverterInfo != null) {
					form.setField(pv001Index + "-" + "INVERTER-MODEL", microInverterInfo.getManufacturerMappingValue()
							+ " " + microInverterInfo.getModelMappingValue());

				}
				form.setField(pv001Index + "-" + "INVERTER-QTY", plansetUtils.getModulePerMicroInverter() + "");

			}

			// S.B 04:08:2020 Update RAIL-RACKING-MODEL mapping CR-2996-MOD-011
			if (checkValue.Equals(permitArraysEntityResult.getRoofMounted(), true)
					&& checkValue.notChecked(permitArraysEntityResult.getGroundMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getPatioMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getCarportMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getOtherMounted())) {
				if (railRacking != null) {
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL",
							railRacking.getManufacturerMappingValue() + " " + railRacking.getModelMappingValue());
				} else
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL", "");
			} else if (permitArraysEntityResult != null
					&& checkValue.Equals(permitArraysEntityResult.getGroundMounted(), true)
					&& checkValue.notChecked(permitArraysEntityResult.getRoofMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getPatioMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getCarportMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getOtherMounted())) {
				if (railRackingGround != null) {
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL",
							railRackingGround.getManufacturerMappingValue() + " "
									+ railRackingGround.getModelMappingValue());
				} else
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL", "");
			} else if (permitArraysEntityResult != null
					&& checkValue.notChecked(permitArraysEntityResult.getGroundMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getRoofMounted())
					&& checkValue.Equals(permitArraysEntityResult.getPatioMounted(), true)
					&& checkValue.notChecked(permitArraysEntityResult.getCarportMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getOtherMounted())) {
				if (railRackingPatio != null) {
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL",
							railRackingPatio.getManufacturerMappingValue() + " "
									+ railRackingPatio.getModelMappingValue());
				} else
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL", "");
			} else if (permitArraysEntityResult != null
					&& checkValue.notChecked(permitArraysEntityResult.getGroundMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getRoofMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getPatioMounted())
					&& checkValue.Equals(permitArraysEntityResult.getCarportMounted(), true)
					&& checkValue.notChecked(permitArraysEntityResult.getOtherMounted())) {
				if (railRackingCarport != null) {
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL",
							railRackingCarport.getManufacturerMappingValue() + " "
									+ railRackingCarport.getModelMappingValue());
				} else
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL", "");
			} else if (permitArraysEntityResult != null
					&& checkValue.notChecked(permitArraysEntityResult.getGroundMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getRoofMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getPatioMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getCarportMounted())
					&& checkValue.Equals(permitArraysEntityResult.getOtherMounted(), true)) {
				form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL", permitArraysEntityResult.getTextOther());
			} else if (permitArraysEntityResult != null
					&& checkValue.Equals(permitArraysEntityResult.getGroundMounted(), true)
					&& checkValue.Equals(permitArraysEntityResult.getRoofMounted(), true)
					&& checkValue.notChecked(permitArraysEntityResult.getPatioMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getCarportMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getOtherMounted())) {
				if (railRackingGround != null && railRacking != null) {
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL",
							railRacking.getModelMappingValue() + " " + railRackingGround.getModelMappingValue());
				} else
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL", "");
			} else if (permitArraysEntityResult != null
					&& checkValue.Equals(permitArraysEntityResult.getGroundMounted(), true)
					&& checkValue.notChecked(permitArraysEntityResult.getRoofMounted())
					&& checkValue.Equals(permitArraysEntityResult.getPatioMounted(), true)
					&& checkValue.notChecked(permitArraysEntityResult.getCarportMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getOtherMounted())) {
				if (railRackingGround != null && railRackingPatio != null) {
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL",
							railRackingGround.getModelMappingValue() + " " + railRackingPatio.getModelMappingValue());
				} else
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL", "");
			} else if (permitArraysEntityResult != null
					&& checkValue.Equals(permitArraysEntityResult.getGroundMounted(), true)
					&& checkValue.notChecked(permitArraysEntityResult.getRoofMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getPatioMounted())
					&& checkValue.Equals(permitArraysEntityResult.getCarportMounted(), true)
					&& checkValue.notChecked(permitArraysEntityResult.getOtherMounted())) {
				if (railRackingGround != null && railRackingCarport != null) {
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL",
							railRackingGround.getModelMappingValue() + " " + railRackingCarport.getModelMappingValue());
				} else
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL", "");
			} else if (permitArraysEntityResult != null
					&& checkValue.Equals(permitArraysEntityResult.getGroundMounted(), true)
					&& checkValue.notChecked(permitArraysEntityResult.getRoofMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getPatioMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getCarportMounted())
					&& checkValue.Equals(permitArraysEntityResult.getOtherMounted(), true)) {
				if (railRackingGround != null) {
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL",
							railRackingGround.getModelMappingValue() + " " + permitArraysEntityResult.getTextOther());
				} else
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL", "");
			} else if (permitArraysEntityResult != null
					&& checkValue.notChecked(permitArraysEntityResult.getGroundMounted())
					&& checkValue.Equals(permitArraysEntityResult.getRoofMounted(), true)
					&& checkValue.Equals(permitArraysEntityResult.getPatioMounted(), true)
					&& checkValue.notChecked(permitArraysEntityResult.getCarportMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getOtherMounted())) {
				if (railRackingPatio != null && railRacking != null) {
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL",
							railRacking.getModelMappingValue() + " " + railRackingPatio.getModelMappingValue());
				} else
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL", "");
			} else if (permitArraysEntityResult != null
					&& checkValue.notChecked(permitArraysEntityResult.getGroundMounted())
					&& checkValue.Equals(permitArraysEntityResult.getRoofMounted(), true)
					&& checkValue.notChecked(permitArraysEntityResult.getPatioMounted())
					&& checkValue.Equals(permitArraysEntityResult.getCarportMounted(), true)
					&& checkValue.notChecked(permitArraysEntityResult.getOtherMounted())) {
				if (railRackingCarport != null && railRacking != null) {
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL",
							railRacking.getModelMappingValue() + " " + railRackingCarport.getModelMappingValue());
				} else
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL", "");
			} else if (permitArraysEntityResult != null
					&& checkValue.notChecked(permitArraysEntityResult.getGroundMounted())
					&& checkValue.Equals(permitArraysEntityResult.getRoofMounted(), true)
					&& checkValue.notChecked(permitArraysEntityResult.getPatioMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getCarportMounted())
					&& checkValue.Equals(permitArraysEntityResult.getOtherMounted(), true)) {
				if (railRacking != null) {
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL",
							railRacking.getModelMappingValue() + " " + permitArraysEntityResult.getTextOther());
				} else
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL", "");
			} else if (permitArraysEntityResult != null
					&& checkValue.notChecked(permitArraysEntityResult.getGroundMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getRoofMounted())
					&& checkValue.Equals(permitArraysEntityResult.getPatioMounted(), true)
					&& checkValue.Equals(permitArraysEntityResult.getCarportMounted(), true)
					&& checkValue.notChecked(permitArraysEntityResult.getOtherMounted())) {
				if (railRackingCarport != null && railRackingPatio != null) {
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL",
							railRackingPatio.getModelMappingValue() + " " + railRackingCarport.getModelMappingValue());
				} else
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL", "");
			} else if (permitArraysEntityResult != null
					&& checkValue.notChecked(permitArraysEntityResult.getGroundMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getRoofMounted())
					&& checkValue.Equals(permitArraysEntityResult.getPatioMounted(), true)
					&& checkValue.notChecked(permitArraysEntityResult.getCarportMounted())
					&& checkValue.Equals(permitArraysEntityResult.getOtherMounted(), true)) {
				if (railRackingPatio != null) {
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL",
							railRackingPatio.getModelMappingValue() + " " + permitArraysEntityResult.getTextOther());
				} else
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL", "");
			} else if (permitArraysEntityResult != null
					&& checkValue.notChecked(permitArraysEntityResult.getGroundMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getRoofMounted())
					&& checkValue.notChecked(permitArraysEntityResult.getPatioMounted())
					&& checkValue.Equals(permitArraysEntityResult.getCarportMounted(), true)
					&& checkValue.Equals(permitArraysEntityResult.getOtherMounted(), true)) {
				if (railRackingCarport != null) {
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL",
							railRackingCarport.getModelMappingValue() + " " + permitArraysEntityResult.getTextOther());
				} else
					form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL", "");
			} else {
				form.setField(pv001Index + "-" + "RAIL-RACKING-MODEL", "MULITPLE MOUNTING TYPES");
			}

			form.setField(pv001Index + "-" + "RAIL-RACKING-QTY", "N/A");

			// STANCHIONS mapping
			if (permitArraysEntityResult != null
					&& (checkValue.Equals(permitArraysEntityResult.getRoofMounted(), true)
							|| (checkValue.Equals(permitArraysEntityResult.getCarportMounted(), true)
									&& (checkValue.Equals(permitArraysEntityResult.getRoofOrOpenFrame(),
											CARPORT_INCLUDE_ROOF_MATERIAL))))) {
				if (stanchionFoot != null) {
					form.setField(pv001Index + "-" + "STANCHIONS-MODEL",
							stanchionFoot.getManufacturerMappingValue() + " " + stanchionFoot.getModelMappingValue());
				} else
					form.setField(pv001Index + "-" + "STANCHIONS-MODEL", "");
			} else
				form.setField(pv001Index + "-" + "STANCHIONS-MODEL", "");
			if (permitDrafterDatanfo.getStanchionQuantity() != null) {
				form.setField(pv001Index + "-" + "STANCHION-QTY", permitDrafterDatanfo.getStanchionQuantity() + "");
			} else
				form.setField(pv001Index + "-" + "STANCHION-QTY", "TBD");
			
			// RSD DEVICE mapping
			//W.A 11-29-2022 CR-PM-1264
			if (checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "Micro Inverter")) {
				form.setField(pv001Index + "-" + "RAPID-SHUTDOWN-MODEL", "INTEGRATED IN INV");
			}else if (dcOptimizer != null && checkValue.Equals(dcOptimizer.getType(), "DC/DC Rapid Shutdown")) {
				// S.B CR-3154-MOD-10 Update RSD DEVICE mapping
				form.setField(pv001Index + "-" + "RAPID-SHUTDOWN-MODEL", dcOptimizer.getModelMappingValue() + "");
			} else if (rapidShutdownModel != null && (checkValue.Equals(rapidShutdownModel.getRsd(), "Y")
					|| checkValue.Equals(rapidShutdownModel.getTypeDc(), "Rapid Shutdown"))) {
				form.setField(pv001Index + "-" + "RAPID-SHUTDOWN-MODEL",
						rapidShutdownModel.getManufacturerMappingValue() + " "
								+ rapidShutdownModel.getModelMappingValue());
			} else {
				// S.B CR-3154-MOD-001 Update RSD mapping
				if ((inverterInfo != null && checkValue.notContains(inverterInfo.getMake(), SOLAR_EDGE))
						&& (secondInverterInfo == null || (secondInverterInfo != null
								&& checkValue.notContains(secondInverterInfo.getMake(), SOLAR_EDGE)))
						&& (thirdInverterInfo == null || (thirdInverterInfo != null
								&& checkValue.notContains(thirdInverterInfo.getMake(), SOLAR_EDGE)))
						&& (fourthInverterInfo == null || (fourthInverterInfo != null
								&& checkValue.notContains(fourthInverterInfo.getMake(), SOLAR_EDGE)))
						&& (fifthInverterInfo == null || (fifthInverterInfo != null
								&& checkValue.notContains(fifthInverterInfo.getMake(), SOLAR_EDGE)))) {
					form.setField(pv001Index + "-" + "RAPID-SHUTDOWN-MODEL", "N/A");
				} else if ((inverterInfo != null && checkValue.contains(inverterInfo.getMake(), SOLAR_EDGE))
						|| (secondInverterInfo != null
								&& checkValue.contains(secondInverterInfo.getMake(), SOLAR_EDGE))
						|| (thirdInverterInfo != null
								&& checkValue.contains(thirdInverterInfo.getMake(), SOLAR_EDGE))
						|| (fourthInverterInfo != null
								&& checkValue.contains(fourthInverterInfo.getMake(), SOLAR_EDGE))
						|| (fifthInverterInfo != null
								&& checkValue.contains(fifthInverterInfo.getMake(), SOLAR_EDGE))) {
					if (checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(),
							"System Optimizer")) {
						form.setField(pv001Index + "-" + "RAPID-SHUTDOWN-MODEL", "INTEGRATED IN INV & OPT");
					}
				}
				 
				 
			}
			
			form.setField(pv001Index + "-" + "RSD-QTY", plansetUtils.getQtyRapidShutdown() > 0 ? plansetUtils.getQtyRapidShutdown() + "" : "N/A");

			// Battery mapping
			// Updated by S.B CR-PM-164 07/15/2021
			if (!essSystemMapping.getBatteryQty(projectId).equals("")) {
				form.setField(pv001Index + "-" + "BATTERIES-MODEL",
						essSystemMapping.getBatteryModel(projectId, "PV001"));
				form.setField(pv001Index + "-" + "BATTERIES -QTY", essSystemMapping.getBatteryQty(projectId));
			} else {
				form.setField(pv001Index + "-" + "BATTERIES-MODEL", "N/A");
				form.setField(pv001Index + "-" + "BATTERIES -QTY", "N/A");
			}

			// EMPTY SLOT#1 mapping
			form.setField(pv001Index + "-" + "EQUIP-Manual-1", "");
			form.setField(pv001Index + "-" + "EQUIP-Qty-Manual-1", "TBD");
			// EMPTY SLOT#2 mapping
			form.setField(pv001Index + "-" + "EQUIP-Manual-2", "");
			form.setField(pv001Index + "-" + "EQUIP-Qty-Manual-2", "N/A");

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}

	}

	public void sheetIndexmapping(AcroFields form, PermitEntity permitEntity, PermitHomeSiteInfoEntity permitHomeSite,
			PermitArrayEntityResultSecond permitArraysEntityResult, int pv001Index, GoverningCodesModel governingCodes,
			AHJDesignCriteriaModel designCriteria, Boolean customize, Long idUserConnected, String showPLDimensions) {
		try {
			if (permitEntity != null
					&& (permitEntity.getPlansetVersion() == null || permitEntity.getPlansetVersion() < 2
							|| !checkValue.NotEquals(form.getField("PV001TITLE"), ""))) {

				List<String> sheetIndex = new ArrayList<>();
				List<String> sheetTitle = new ArrayList<>();
				sheetIndex.add("PV-001");
				sheetTitle.add("COVER SHEET");
				List<Long> listPDFs = getPDFReaderService.getCompatibleCustomize(permitEntity.getId(), "PDF-N001",
						idUserConnected);
				if ((permitHomeSite != null && checkValue.NotEquals(permitHomeSite.getState(), "CA"))
						|| (listPDFs != null && listPDFs.size() > 0)) {
					sheetIndex.add("N-001");
					sheetTitle.add("GENERAL NOTES");
				}

				// F.M CR-PM-3787 2021-16-09
				if (permitArraysEntityResult != null && (Boolean.TRUE.equals(permitArraysEntityResult.getRoofMounted())
						|| ((Boolean.TRUE.equals(permitArraysEntityResult.getCarportMounted())
								|| Boolean.TRUE.equals(permitArraysEntityResult.getPatioMounted()))
								&& checkValue.Equals(permitArraysEntityResult.getRoofOrOpenFrame(),
										CARPORT_INCLUDE_ROOF_MATERIAL)))) {
					sheetIndex.add("PV-100R");
					sheetTitle.add("PV ARRAY LAYOUT");
				}
				// A.B CR-636 01-10-2022
				if (permitArraysEntityResult != null && Boolean.TRUE.equals(permitArraysEntityResult.getRoofMounted()) && checkValue.Equals(showPLDimensions, "Yes")) {
					sheetIndex.add("PV-101R");
					sheetTitle.add("SITE MAP");
				}
				if (permitArraysEntityResult != null
						&& Boolean.TRUE.equals(permitArraysEntityResult.getGroundMounted())) {
					sheetIndex.add("PV-100G");
					sheetTitle.add("PV ARRAY LAYOUT");
				}

				if (permitArraysEntityResult != null
						&& Boolean.TRUE.equals(permitArraysEntityResult.getGroundMounted())) {
					sheetIndex.add("PV-101G");
					sheetTitle.add("DETAILED LAYOUT");
				}
				if (checkValue.notChecked(permitArraysEntityResult.getGroundMounted())
						|| checkValue.Equals(permitArraysEntityResult.getRoofMounted(), true)) {
					sheetIndex.add("S-100");
					sheetTitle.add("RACKING LAYOUT");
					sheetIndex.add("S-200");
					sheetTitle.add("SECTION ELEVATION");
					sheetIndex.add("S-201");
					sheetTitle.add("ATTACHMENT DETAILS");
				}
				if (permitArraysEntityResult != null
						&& checkValue.Equals(permitArraysEntityResult.getGroundMounted(), true)) {
					sheetIndex.add("S-300");
					sheetTitle.add("GROUND RACKING LAYOUT");
				}
				sheetIndex.add("E-001");
				sheetTitle.add("EQUIP. CALCULATION");
				sheetIndex.add("E-002");
				sheetTitle.add("WIRE AND COND. CALCS");
				sheetIndex.add("E-003");
				sheetTitle.add("THREE LINE DIAGRAM");
				sheetIndex.add("E-100");
				sheetTitle.add("ELECTRICAL LAYOUT");
				sheetIndex.add("P-001");
				sheetTitle.add("STANDARD PLACARDS");
				sheetIndex.add("P-002");
				sheetTitle.add("DYNAMIC PLACARDS");
				sheetIndex.add("R-1xx");
				sheetTitle.add("EQUIP.CUT SHEETS");

				for (int i = 0; i < sheetTitle.size(); i++) {
					form.setField(pv001Index + "-" + "SHEET-" + (i + 1), sheetIndex.get(i));
					form.setField(pv001Index + "-" + "TITLE-SHEET-" + (i + 1), sheetTitle.get(i));
				}

				if (permitHomeSite != null && checkValue.Equals(permitHomeSite.getState(), "CA")) {
					form.setField(pv001Index + "-" + "CALIFORNIA-BUILDING-CODE",
							checkValue.isStringNotEmpty(governingCodes.getCbc()) ? governingCodes.getCbc()
									: "2019");
					form.setField(pv001Index + "-" + "NATIONAL-ELECTRIC-CODE",
							checkValue.isStringNotEmpty(governingCodes.getNec()) ? governingCodes.getNec()
									: "2017");
					form.setField(pv001Index + "-" + "CALIFORNIA-ELECTRIC-CODE",
							checkValue.isStringNotEmpty(governingCodes.getCec()) ? governingCodes.getCec()
									: "2019");
					form.setField(pv001Index + "-" + "CALIFORNIA-FIRE-CODE",
							checkValue.isStringNotEmpty(governingCodes.getCfc()) ? governingCodes.getCfc()
									: "2019");
					form.setField(pv001Index + "-" + "CALIFORNIA-RESIDENTIAL-CODE",
							checkValue.isStringNotEmpty(governingCodes.getCrc()) ? governingCodes.getCrc()
									: "2019");
					//A.B 04/11/2022 REV-CR-3064--MOD-11-a-xii
					if(checkValue.isStringNotEmpty(governingCodes.getCgbsc())) {
						form.setField(pv001Index + "-CGBSC",governingCodes.getCgbsc());
						form.setField(pv001Index + "-CALIFORNIA GREEN-BUILDING-CODE", "CALIFORNIA GREEN BUILDING CODE");
					}
					// F.S 5-28-20 CR-PM-3290 Update MOD-002:PV-001 Design Criteria Mapping
					// A.B 7-23-21 CR-3064-MOD-11
					if (designCriteria != null
							&& checkValue.isStringNotEmpty(designCriteria.getAsceStandard())) {
						form.setField(pv001Index + "-" + "ASCE 7-10 WIND SPEED",
								designCriteria.getAsceStandard() + " WIND SPEED:");
					} else if (checkValue.isStringNotEmpty(governingCodes.getCbc())
							&& !checkValue.Equals(governingCodes.getCbc(), "2019")) {
						form.setField(pv001Index + "-" + "ASCE 7-10 WIND SPEED", "ASCE 7-10 WIND SPEED:");
					} else {
						form.setField(pv001Index + "-" + "ASCE 7-10 WIND SPEED", "ASCE 7-16 WIND SPEED:");
					}
				} else {
					form.setField(pv001Index + "-" + "CALIFORNIA-BUILDING-CODE",
							checkValue.isStringNotEmpty(governingCodes.getCbc()) ? governingCodes.getCbc()
									: "2016");
					form.setField(pv001Index + "-" + "NATIONAL-ELECTRIC-CODE",
							checkValue.isStringNotEmpty(governingCodes.getNec()) ? governingCodes.getNec()
									: Boolean.FALSE.equals(customize)
											? getDefaultGoverningCodes(permitHomeSite.getState(), "NEC", "2014")
											: "2014");
					form.setField(pv001Index + "-" + "CALIFORNIA-ELECTRIC-CODE",
							checkValue.isStringNotEmpty(governingCodes.getCec()) ? governingCodes.getCec()
									: "2016");
					form.setField(pv001Index + "-" + "CALIFORNIA-FIRE-CODE",
							checkValue.isStringNotEmpty(governingCodes.getCfc()) ? governingCodes.getCfc()
									: "2016");
					form.setField(pv001Index + "-" + "CALIFORNIA-RESIDENTIAL-CODE",
							checkValue.isStringNotEmpty(governingCodes.getCrc()) ? governingCodes.getCrc()
									: "2016");
					// F.S 5-28-20 CR-PM-3290 Update MOD-002:PV-001 Design Criteria Mapping
					// A.B 7-23-21 CR-3064-MOD-11
					if (designCriteria != null
							&& checkValue.isStringNotEmpty(designCriteria.getAsceStandard())) {
						form.setField(pv001Index + "-" + "ASCE 7-10 WIND SPEED",
								designCriteria.getAsceStandard() + " WIND SPEED:");
					} else if (governingCodes.getIbc() == null
							|| !checkValue.Equals(governingCodes.getIbc(), "2018")
							|| governingCodes.getIrc() == null
							|| !checkValue.Equals(governingCodes.getIrc(), "2018")) {
						form.setField(pv001Index + "-" + "ASCE 7-10 WIND SPEED", "ASCE 7-10 WIND SPEED:");
					} else {
						form.setField(pv001Index + "-" + "ASCE 7-10 WIND SPEED", "ASCE 7-16 WIND SPEED:");
					}
				}

				// A.B 10/20/2021 CR-PM-265-MOD-002
				form.setField(pv001Index + "-" + "INTERNATIONAL-BUILDING-CODE",
						checkValue.isStringNotEmpty(governingCodes.getIbc()) ? governingCodes.getIbc()
								: Boolean.FALSE.equals(customize)
										? getDefaultGoverningCodes(permitHomeSite.getState(), "IBC", "")
										: "");
				form.setField(pv001Index + "-" + "INTERNATIONAL-FIRE-CODE",
						checkValue.isStringNotEmpty(governingCodes.getIfc()) ? governingCodes.getIfc()
								: Boolean.FALSE.equals(customize)
										? getDefaultGoverningCodes(permitHomeSite.getState(), "IFC", "")
										: "");
				form.setField(pv001Index + "-" + "INTERNATIONAL-RESIDENTIAL-CODE",
						checkValue.isStringNotEmpty(governingCodes.getIrc()) ? governingCodes.getIrc()
								: Boolean.FALSE.equals(customize)
										? getDefaultGoverningCodes(permitHomeSite.getState(), "IRC", "")
										: "");

				form.setField(pv001Index + "-" + "AHJ-SPECIFIC-CODE", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	// A.B 10/20/2021 CR-PM-265-MOD-002
	private String getDefaultGoverningCodes(String state, String code, String v) {
		try {
			if (state.equals("ID") || state.equals("NC") || state.equals("CO") || state.equals("GA")
					|| state.equals("TX") || state.equals("OR")) {
				return code.equals("IRC") || code.equals("IBC") || code.equals("IFC") ? "2015"
						: code.equals("NEC") ? "2017" : v;
			} else if (state.equals("SC") || state.equals("OK")) {
				return code.equals("IRC") || code.equals("IBC") || code.equals("IFC") ? "2015"
						: code.equals("NEC") ? "2014" : v;
			} else if (state.equals("MA")) {
				return code.equals("IRC") || code.equals("IBC") || code.equals("IFC") ? "2015"
						: code.equals("NEC") ? "2020" : v;
			}  else if (state.equals("MO")) {
				return code.equals("IRC") ? "2009"
						: code.equals("IBC") ? "2012" : code.equals("IFC") ? "2009" : code.equals("NEC") ? "2014" : v;
			} else
				return v;
		} catch (Exception e) {
			return v;
		}
	}

	public File buildingPDFPV001(PermitHomeSiteInfoEntity permitHomeSite,
			PermitAdditionalInfoEntityResult permitAdditionalInfo,
			PermitArrayEntityResultSecond permitArraysEntityResult, ContractorInformationEntity userContactInfo,
			PermitEntity permitEntity, String permitSusyemSize, PermitEngineerEntity permitNewInputs,
			Engineers engineer, ElectricalUtilityEntity electricalCompany, PermitAdvEntityResult permitAdvInfo,
			Inverters inverterInfo, Cmodulev2 moduleInfo, Inverters microInverterInfo,
			RoofAttachmentsEntity stanchionFoot, PermitDrafterDataEntity permitDrafterDatanfo, RailRacking railRacking,
			RailRacking railRackingGround, RailRacking railRackingPatio, RailRacking railRackingCarport,
			DCCombinerDisconnectEntity rapidShutdownModel, RoofMaterialType roofMaterialType, PdfReader reader,
			int pv001Index, String filePath, TitleBlockValues titleBlockValues, PlansetUtils plansetUtils,
			List<PermitSketchEntity> listPermitSketchEntity, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			Integer qtyAcCombiner, GoverningCodesModel governingCodes, AHJNotesModel ahjNotes,
			DCOptimizerEntity dcOptimizer, Inverters secondInverterInfo, Inverters thirdInverterInfo,
			Inverters fourthInverterInfo, Inverters fifthInverterInfo, AHJDesignCriteriaModel designCriteria,
			List<Inverters> inverters, Flashing flashing, DCCombinerDisconnectEntity dcCombinerDisconnect,
			DCCombinerDisconnectEntity seconddcCombinerDisconnect, DCCombinerDisconnectEntity thirddcCombinerDisconnect,
			DCCombinerDisconnectEntity roofTopDCDisconnect, DCCombinerDisconnectEntity jBox, ACDisconnect acDisconnect,
			ACDisconnect secondacDisconnect, ACDisconnect acDisconnectThree, ACDisconnect acDisconnectFour,
			ACDisconnect additionalAcDisconnect, ACCombinerSLC slcAcCombiner, ACCombinerSLC acCombiner,
			LeasePPAMeter leasePPAMeter, Boolean customize) {

		// you only need a PdfStamper if you're going to change the existing PDF.
		File fileRe = null;

		try {

			fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-PV001" + permitEntity.getId() + "-" + pv001Index + ".pdf");
			if (fileRe.exists()) {
				fileRe.delete();
				fileRe = new File(
						Constants.rapportPlansetFolderUrl + "PDF-PV001" + permitEntity.getId() + "-" + pv001Index + ".pdf");
			}

			PdfReader readerOriginNEC = new PdfReader(Constants.rapportPlansetFolderUrl + "NEC-PDF/" + "PDF-PV001.pdf");
			PdfReader readerOriginCEC = new PdfReader(Constants.rapportPlansetFolderUrl + "PDF-PV001.pdf");

			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
			AcroFields form = stamper.getAcroFields();
			
			//A.B remove sheet index if exist when the project was uploaded
			if(permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
				getPDFReaderService.removeRevisionFieldsIndex(stamper, form, pv001Index);
			}
//			form.setExtraMargin(0f, 0.86f);
			// A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName

			getPDFReaderService.addFieldsIndex(stamper, reader, pv001Index, "PV001");
			// A.B: Set PDF Font For Revision
			getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginNEC, permitHomeSite, form,
					pv001Index);
			getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginCEC, permitHomeSite, form,
					pv001Index);

			// A.B CR-3386: map the Project Coordinate
			if (permitHomeSite.getLatitude() != null && permitHomeSite.getLongitude() != null) {
				form.setField(pv001Index + "-" + "GPS-LOCATION-COORDINATES",
						"GPS COORDINATES (" + permitHomeSite.getLatitude() + "," + permitHomeSite.getLongitude() + ")");
				// A.B CR-3386: map the street Address
				if (permitAdvInfo.getGoogleZoom() > 0) {
					String url = "http://maps.googleapis.com/maps/api/staticmap?center=" + permitHomeSite.getLatitude()
							+ "," + permitHomeSite.getLongitude() + "&zoom=" + permitAdvInfo.getGoogleZoom()
							+ "&markers=color:red%7C" + permitHomeSite.getLatitude() + ","
							+ permitHomeSite.getLongitude()
							+ "&size=230x300&key=AIzaSyAVSZo69BWHNU-cpOTMVAIQSUHYcUxqUgE";
					imageMapping.mapUrlImageToPDF(stamper, 937f, 509.5035f, 173.146875f, 225.84375f, url);
				}
			}

			// A.B 07-14-2021 CR-3064
			form.setField(pv001Index + "-" + "SPECIAL-NOTE-PV-001", ahjNotes.getPV001Note());

			if (checkValue.isStringNotEmpty(ahjNotes.getPV001ACRequirement())
					&& (plansetUtils.getQtyACDiscoGRND() > 0 || plansetUtils.getQtyACDisconnect() > 0)) {
				Rectangle linkLocation1 = new Rectangle(40, 40, 240, 140);
				PdfAnnotation stamp1 = PdfAnnotation.createPopup(stamper.getWriter(), linkLocation1,
						ahjNotes.getPV001ACRequirement(), true);
				stamper.addAnnotation(stamp1, 1);
			}

			// A.B CR-3250 03-30 Logo & Signature Mapping
			logoSignMapping.mapLogo_Signature(permitEntity.getAuthentificationEntity().getId(), stamper, filePath);

			/*---------------------Update HO/Site Info Tab – Add Question AHJ - CR-486 Planset Mapping PV001--------------------------*/

			mapTitleBlock(permitHomeSite, permitEntity, form, pv001Index, titleBlockValues);
			/*---------------------End Update HO/Site Info Tab – Add Question AHJ - CR-486 Planset Mapping PV001--------------------------*/
			mapContractorInfo(userContactInfo, permitEntity.getAuthentificationEntity(), form, permitHomeSite, pv001Index, titleBlockValues);
			mapPermitHomeInfo(permitHomeSite, form, permitEntity, permitArraysEntityResult, permitAdvInfo,
					electricalCompany, pv001Index, titleBlockValues, designCriteria);

			// A.B 09-02-2021 CR-PM-REV-2197
			// A.B 01-11-2022 CR-PM-REV-2197-MOD-15
			String utility = checkValue.Equals(permitHomeSite.getUtilityCompanyName(), "Other") ? permitHomeSite.getUtilityCompanyNameOther() : electricalCompany.getUtilityCompanyName();
			 moduleInverterMfgQty.moduleInverterMfgQty("-PV-001",form, permitArraysEntityResult.getDeviceToIncorporate(),
			 		permitEntity.getId(), pv001Index, inverterInfo, moduleInfo, microInverterInfo, utility, permitHomeSite.getMeterNumber(),   plansetUtils);

			/// --- CR 800 planset mapping update ---///
			if (permitSusyemSize != null) {
				form.setField(pv001Index + "-" + "SYSTEMSIZE1", permitSusyemSize);
				form.setField(pv001Index + "-" + "PV001-kW-SYSTEM-SIZE", permitSusyemSize);
				titleBlockValues.setKwSystemSize(permitSusyemSize);
			}

			String mountType = getMountType(permitArraysEntityResult);

			form.setField(pv001Index + "-" + "MOUNT-TYPE", mountType);
			form.setField(pv001Index + "-" + "GRID-OR-ALONE", "GRID TIED");

			// 05/15/2019 / CI : CR 2566
			String scopeOfWork = getScopeOfWork(mountType, permitAdditionalInfo);

			form.setField(pv001Index + "-" + "SCOPE-OF-WORK", scopeOfWork);
			proposedSolarEquipMapping(form, pv001Index, permitArraysEntityResult, moduleInfo, inverterInfo,
					microInverterInfo, stanchionFoot, permitDrafterDatanfo, railRacking, railRackingGround,
					railRackingPatio, railRackingCarport, rapidShutdownModel, plansetUtils,
					dcOptimizer, secondInverterInfo, thirdInverterInfo,
					fourthInverterInfo, fifthInverterInfo, permitEntity.getId());

			// SITE / PROJECT DETAILS mapping
			
			// 08-27-2019: M.A : CR-2879
			if (permitProjectSiteInfo.getMainPanelUpgrade() != null
					&& checkValue.Equals(permitProjectSiteInfo.getMainPanelUpgrade(), true)) {
				form.setField(pv001Index + "-" + "EQUIP-Qty-Manual-2", "(N)");
			} else {
				form.setField(pv001Index + "-" + "EQUIP-Qty-Manual-2", "(E)");

			}

			// 08-27-2019: M.A : CR-2879
			// 01-07-2020: A.B : CR-2879 Revision : Add To Upper Case
			if (permitProjectSiteInfo.getSolarLocation() != null
					&& checkValue.Equals(permitProjectSiteInfo.getSolarLocation(), "Other")) {
				if (checkValue.NotEquals(permitProjectSiteInfo.getSolarLocationOther(), "")) {
					form.setField(pv001Index + "-" + "POC-TYPE",
							permitProjectSiteInfo.getSolarLocationOther().toUpperCase());
				} else
					form.setField(pv001Index + "-" + "POC-TYPE", "");

			} else if (checkValue.NotEquals(permitProjectSiteInfo.getSolarLocation(), "")) {
				form.setField(pv001Index + "-" + "POC-TYPE", permitProjectSiteInfo.getSolarLocation().toUpperCase());
			} else
				form.setField(pv001Index + "-" + "POC-TYPE", "");
			
			//F.B 04-12-2022 CR-PM-805
			Boolean wireTapSetting = userSettingRepo.findWireTapSettingByUserId(permitEntity.getAuthentificationEntity().getId());
			if(Boolean.TRUE.equals(wireTapSetting) && permitProjectSiteInfo.getSolarLocation()!= null 
					&& (checkValue.Equals(permitProjectSiteInfo.getSolarLocation(), "Line-side tap") 
							|| checkValue.Equals(permitProjectSiteInfo.getSolarLocation(), "Load-side tap"))) {
				form.setField(pv001Index + "-" + "POC-TYPE", "WIRE TAP");				
			}

			// 08-27-2019: M.A : CR-2879
			String busRating = "";
			String breakerRating = "";

			if (checkValue.Equals(permitProjectSiteInfo.getPanelBusRating(), "Other")) {
				busRating = permitProjectSiteInfo.getPanelBusRatingOther();
			} else
				busRating = permitProjectSiteInfo.getPanelBusRating();

			if (checkValue.Equals(permitProjectSiteInfo.getPanelMainBreakerRating(), "Other")) {
				breakerRating = permitProjectSiteInfo.getPanelMainBreakerRatingOther();
			} else
				breakerRating = permitProjectSiteInfo.getPanelMainBreakerRating();

			form.setField(pv001Index + "-" + "MSP-RATING", busRating + "A BUS/" + breakerRating + "A MAIN BREAKER");

			// 08-28-2019: M.A : CR-2879
			// S.B CR-3154-MOD-001 Update AC Combiner Mapping
			ACCombinerSLC ac = null;
			if (permitProjectSiteInfo.getCombiningACCircuits() != null
					&& checkValue.NotEquals(permitProjectSiteInfo.getCombiningACCircuits(), "")
					&& (checkValue.Equals(permitProjectSiteInfo.getCombiningACCircuits(),
							"A Proposed AC Combiner Panel (Solar Load Center)")
							|| checkValue.Equals(permitProjectSiteInfo.getCombiningACCircuits(),
									"A Proposed AC Combiner Panel (Solar Load Center) MOUNTED AT THE ARRAY")
							|| checkValue.Equals(permitProjectSiteInfo.getCombiningACCircuits(),
									"A Proposed AC Combiner Panel (Solar Load Center) MOUNTED AT THE MAIN SERVICE PANEL"))
					&& permitProjectSiteInfo.getGroundLevelACCombinerBoxModel() != null) {
				ac = permitProjectSiteInfo.getGroundLevelACCombinerBoxModel();

			} else if (permitProjectSiteInfo.getMicroInverterCabling() != null
					&& checkValue.Equals(permitProjectSiteInfo.getMicroInverterCabling(),
							"Rooftop AC Combiner")
					&& permitProjectSiteInfo.getRoofTopACCombiner() != null) {
				ac = permitProjectSiteInfo.getRoofTopACCombiner();

			} else if (permitProjectSiteInfo.getInstallingACCombiner() != null
					&& permitProjectSiteInfo.getInstallingACCombiner().equals(true)
					&& permitProjectSiteInfo.getACCombinerInstalled() != null) {
				ac = permitProjectSiteInfo.getACCombinerInstalled();

			}

			if (ac != null) {
				form.setField(pv001Index + "-" + "COMBINER-MODEL",
						ac.getManufacturerMappingValue() + " " + ac.getModelMappingValue());
				form.setField(pv001Index + "-" + "EQUIP-Qty-Manual-1", qtyAcCombiner + "");
			} else {
				form.setField(pv001Index + "-" + "COMBINER-MODEL", "N/A");
				form.setField(pv001Index + "-" + "EQUIP-Qty-Manual-1", "N/A");
			}
			String systemSizeDC = systemSize.systemSizeDC(moduleInfo, plansetUtils.getModuleQty());
			if (checkValue.NotEquals(systemSizeDC, "")) {
				form.setField(pv001Index + "-" + "SYSTEM-SIZE-DC", systemSizeDC + " KW");
			} else
				form.setField(pv001Index + "-" + "SYSTEM-SIZE-DC", "");

			String systemSizeAC = "";
			if (permitArraysEntityResult != null
					&& (checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "Neither")
							|| checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(),
									"System Optimizer"))) {
				systemSizeAC = systemSize.stringSystemSizeAC(moduleInfo, plansetUtils.getModuleQty(), inverterInfo, permitEntity.getAuthentificationEntity().getId(), permitAdditionalInfo.getRequiredElectricalStamp());
			} else if (permitArraysEntityResult != null && checkValue
					.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "Micro Inverter")) {
				systemSizeAC = systemSize.microSystemSizeAC(moduleInfo, plansetUtils.getModuleQty(), microInverterInfo, permitEntity.getAuthentificationEntity().getId(), permitAdditionalInfo.getRequiredElectricalStamp());
			}
			if (checkValue.NotEquals(systemSizeAC, "")) {
				form.setField(pv001Index + "-" + "SYSTEM-SIZE-AC", systemSizeAC + " KW");
			} else
				form.setField(pv001Index + "-" + "SYSTEM-SIZE-AC", "");

			if (permitArraysEntityResult != null
					&& (checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "Neither")
							|| checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(),
									"System Optimizer"))) {
				form.setField(pv001Index + "-" + "STRING-CKT-QTY", plansetUtils.getTotalNumberOfStrings() + "");

			} else if (permitArraysEntityResult != null && (checkValue
					.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "Micro Inverter")
					|| checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(),
							"AC Modules"))) {
				form.setField(pv001Index + "-" + "STRING-CKT-QTY", plansetUtils.getNumberOfBranchCircuit() + "");
			}

			if (permitHomeSite != null && permitHomeSite.getIfServiceVoltage() != null
					&& Boolean.TRUE.equals(permitHomeSite.getIfServiceVoltage())) {
				form.setField(pv001Index + "-" + "ELECT-SERVICE", "120/240V - 1Φ");
			} else {
				if (permitHomeSite != null
						&& checkValue.NotEquals(permitHomeSite.getServiceVoltage(), "")) {
					if (checkValue.NotEquals(permitHomeSite.getServiceVoltage(), "Other")) {
						form.setField(pv001Index + "-" + "ELECT-SERVICE", permitHomeSite.getServiceVoltage());
					} else {
						form.setField(pv001Index + "-" + "ELECT-SERVICE", permitHomeSite.getServiceVoltageOther());
					}
				}
			}

			// CR-PM-3787 F.M 2021-09-16

			if (permitArraysEntityResult != null
					&& (checkValue.Equals(permitArraysEntityResult.getRoofMounted(), true)
							|| (checkValue.Equals(permitArraysEntityResult.getCarportMounted(), true)
									&& checkValue.notChecked(permitArraysEntityResult.getRoofMounted())
									&& checkValue.notChecked(permitArraysEntityResult.getGroundMounted())
									&& checkValue.notChecked(permitArraysEntityResult.getPatioMounted())
									&& checkValue.notChecked(permitArraysEntityResult.getOtherMounted())
									&& checkValue.Equals(permitArraysEntityResult.getRoofOrOpenFrame(),
											CARPORT_INCLUDE_ROOF_MATERIAL))
							|| (checkValue.Equals(permitArraysEntityResult.getPatioMounted(), true)
									&& checkValue.notChecked(permitArraysEntityResult.getRoofMounted())
									&& checkValue.notChecked(permitArraysEntityResult.getGroundMounted())
									&& checkValue.notChecked(permitArraysEntityResult.getCarportMounted())
									&& checkValue.notChecked(permitArraysEntityResult.getOtherMounted())
									&& checkValue.Equals(permitArraysEntityResult.getRoofOrOpenFrame(),
											CARPORT_INCLUDE_ROOF_MATERIAL)))) {

				if (permitArraysEntityResult != null
						&& checkValue.NotEquals(roofMaterialType.getMappingValue(), "")) {
					form.setField(pv001Index + "-" + "ROOF-COVERING", roofMaterialType.getMappingValue());
				} else
					form.setField(pv001Index + "-" + "ROOF-COVERING", "");

			} else {
				form.setField(pv001Index + "-" + "ROOF-COVERING", "N/A");

			}

			if (permitArraysEntityResult != null
					&& checkValue.Equals(permitArraysEntityResult.getGroundMounted(), true)) {
				form.setField(pv001Index + "-" + "MAX-ARRAY-HGT-TITLE", "MAX ARRAY HGT.");
			} else
				form.setField(pv001Index + "-" + "MAX-ARRAY-HGT-TITLE", "");
			form.setField(pv001Index + "-" + "MAX-ARRAY-HGT", "");
			// 08-27-2019: M.A: CR-2879
			if (listPermitSketchEntity != null) {
				StringBuilder mapModuleTilt = new StringBuilder();
				StringBuilder mapModuleAzimuth = new StringBuilder();
				ArrayList<String> arrayModule = new ArrayList<>();
				ArrayList<String> arrayAzimuth = new ArrayList<>();
				for (int i = 0; i < listPermitSketchEntity.size(); i++) {
					if (arrayModule != null && !arrayModule.contains(listPermitSketchEntity.get(i).getModelvalue())) {
						if (!checkValue.Equals(mapModuleTilt.toString(), ""))
							mapModuleTilt.append("," + listPermitSketchEntity.get(i).getModelvalue() + "°");
						else
							mapModuleTilt.append(listPermitSketchEntity.get(i).getModelvalue() + "°");

					}
					arrayModule.add(listPermitSketchEntity.get(i).getModelvalue());

				}

				for (int i = 0; i < listPermitSketchEntity.size(); i++) {
					if (arrayAzimuth != null && !arrayAzimuth.contains(listPermitSketchEntity.get(i).getAzimuth())) {
						if (!checkValue.Equals(mapModuleAzimuth.toString(), ""))
							mapModuleAzimuth.append("," + listPermitSketchEntity.get(i).getAzimuth() + "°");
						else
							mapModuleAzimuth.append(listPermitSketchEntity.get(i).getAzimuth() + "°");

					}
					arrayAzimuth.add(listPermitSketchEntity.get(i).getAzimuth());

				}
				form.setField(pv001Index + "-" + "MANUAL-SITE-DETAILS1", mapModuleTilt.toString());
				form.setField(pv001Index + "-" + "MANUAL-SITE-DETAILS2", mapModuleAzimuth.toString());

			}

			/// -------------- End CR 800--------------///
			engineerMapping(permitNewInputs, form, engineer, pv001Index);

			if (checkValue.Equals(permitHomeSite.getUtilityCompanyName(), "Other")) {
				form.setField(pv001Index + "-" + "UTILITY-COMPANY", permitHomeSite.getUtilityCompanyNameOther());
			} else if (electricalCompany != null) {
				form.setField(pv001Index + "-" + "UTILITY-COMPANY", electricalCompany.getMappingValue());
			}
			if (electricalCompany != null) {
				form.setField(pv001Index + "-" + "UTILITY-COMPANY-PHONE", electricalCompany.getPhone());
			}
			AuthorityHavingJurisdctionEntity authority = ahjRepo.findFirstByZipCodes(permitHomeSite.getPostalCode());
			if (authority != null) {
				form.setField(pv001Index + "-" + "BLDGDEPT-PHONE", authority.getBuildingPhone());
				form.setField(pv001Index + "-" + "FIREDEPT", authority.getFireDept());
				form.setField(pv001Index + "-" + "FIREDEPT-PHONE", authority.getFireDeptPhone());
			}
			// FS 3-18-20 CR-2234 starts here
			// FBM 8-31-2021 MOD-ADVP-2234-001

			if (checkValue.Equals(permitArraysEntityResult.getGroundMounted(), true)
					|| checkValue.Equals(permitArraysEntityResult.getCarportMounted(), true)
					|| checkValue.Equals(permitArraysEntityResult.getPatioMounted(), true)) {
				form.setField(pv001Index + "-" + "CONSTRUCTION TYPE", "N/A");
			} else if (checkValue.Equals(permitArraysEntityResult.getRoofMounted(), true)
					&& checkValue.Equals(permitHomeSite.getState(), "FL")) {
				form.setField(pv001Index + "-" + "CONSTRUCTION TYPE", "IIIA");
			} else {
				form.setField(pv001Index + "-" + "CONSTRUCTION TYPE", "VB");
			}
			// FS 3-18-20 CR-2234 ends here
			sheetIndexmapping(form, permitEntity, permitHomeSite, permitArraysEntityResult, pv001Index, governingCodes,
					designCriteria, customize, userContactInfo.getId(), ahjNotes.getShowPLDimensions());
			getPDFReaderService.removeFieldsIndex(stamper, pv001Index, "PV001");
			
			ProposedSubPanel proposedSubPanel = checkValue.isNumericNotZero(permitProjectSiteInfo.getProposedSubPanel())
					? proposedSubPanelRepo.findById(Long.valueOf(permitProjectSiteInfo.getProposedSubPanel()))
							.orElseThrow(() -> new ResourceNotFoundException("No Found"))
					: null;
			TiltLegs tiltLegs = checkValue.isNumericNotZero(permitAdditionalInfo.getTiltLegsMod())
					? tiltLegsRepo.findById(Long.valueOf(permitAdditionalInfo.getTiltLegsMod())).orElseThrow(
							() -> new ResourceNotFoundException("No Found"))
					: null;

			unverifiedEquipment.mapUnverifiedEquipment(form, pv001Index, permitEntity.getId(), moduleInfo, inverters,
					microInverterInfo, dcOptimizer, railRacking, railRackingGround, railRackingPatio,
					railRackingCarport, stanchionFoot, flashing, rapidShutdownModel, dcCombinerDisconnect,
					seconddcCombinerDisconnect, thirddcCombinerDisconnect, roofTopDCDisconnect, jBox, acDisconnect,
					secondacDisconnect, acDisconnectThree, acDisconnectFour, additionalAcDisconnect, slcAcCombiner,
					acCombiner, leasePPAMeter, proposedSubPanel, tiltLegs);
			stamper.close();
			reader.close();
		} catch (IOException | ResourceNotFoundException | NumberFormatException e) {
			e.printStackTrace();

		} catch (DocumentException dE) {
			dE.printStackTrace();
		}
		return fileRe;

	}

}
