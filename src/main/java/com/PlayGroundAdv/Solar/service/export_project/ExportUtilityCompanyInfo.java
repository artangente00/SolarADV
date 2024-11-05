package com.PlayGroundAdv.Solar.service.export_project;

import java.util.LinkedHashMap;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitCompanyInfoEntity;
import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
import com.PlayGroundAdv.Solar.repositories.PermitCompanyInfoRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class ExportUtilityCompanyInfo {

	final CheckValueTypesService checkValueTypes;
	final PermitCompanyInfoRepository companyInfoRepo;

	public ExportUtilityCompanyInfo(CheckValueTypesService checkValueTypes,
			PermitCompanyInfoRepository companyInfoRepo) {
		super();
		this.checkValueTypes = checkValueTypes;
		this.companyInfoRepo = companyInfoRepo;
	}

	public LinkedHashMap<String, String> utilityCompany(GetPermitByIdResult permit) {

		LinkedHashMap<String, String> utility = new LinkedHashMap<>();

		try {
			PermitCompanyInfoEntity permitCompany = companyInfoRepo
					.findByPermitEntityId(permit.getPermitEntity().getId());

			utility.put("Customer's utility Account Type",
					checkValueTypes.convert(permitCompany.getTypeCustomer() + ""));
			utility.put("Other Customer's utility Account Type",
					checkValueTypes.convert(permitCompany.getTypeCustomerOther() + ""));
			utility.put("New Rate Schedule (After Solar Install)",
					checkValueTypes.convert(permitCompany.getNewRate() + ""));
			utility.put("Other new Rate Schedule (After Solar Install )",
					checkValueTypes.convert(permitCompany.getTextOtherNewRate() + ""));
			utility.put("New Commercial Rate Schedule (After Solar Install)",
					checkValueTypes.convert(permitCompany.getNewRateCommercial() + ""));
			utility.put("Other New Commercial Rate Schedule (After Solar Install)",
					checkValueTypes.convert(permitCompany.getOtherNewRateCommercial() + ""));
			utility.put("New Other Rate Schedule (After Solar Install)",
					checkValueTypes.convert(permitCompany.getNewRateOthers() + ""));
			utility.put("Other New Other Rate Schedule (After Solar Install)",
					checkValueTypes.convert(permitCompany.getNewRateOthers() + ""));
			utility.put(
					"The Proposed System Produces more than 110% of the Customer of Record's Past 12 months' kWh usage.",
					checkValueTypes.convert(permitCompany.getCheckIfTheProposedSystemProduce() + ""));
			utility.put("Planned Annual Increase in kWh Usage",
					checkValueTypes.convert(permitCompany.getPlannedAnnual() + ""));
			utility.put("This is a Standard NEM (SNEM) Application",
					checkValueTypes.convert(permitCompany.getSnemApplication() + ""));
			utility.put("Choose Application Type", checkValueTypes.convert(permitCompany.getApplicationType() + ""));
			utility.put("This is a New Service (No Bill Exists)",
					checkValueTypes.convert(permitCompany.getThisIsNewService() + ""));
			utility.put("The Project Is Located in a New Subdivision",
					checkValueTypes.convert(permitCompany.getTheProjectIsLocated() + ""));
			utility.put("Development Name", checkValueTypes.convert(permitCompany.getDevelopemName() + ""));
			utility.put("Developer's name", checkValueTypes.convert(permitCompany.getDevelopersNameSecond() + ""));
			utility.put("Project Is", checkValueTypes.convert(permitCompany.getProjectIs() + ""));
			utility.put("Other Project Is", checkValueTypes.convert(permitCompany.getOtherProjectIs() + ""));
			utility.put("Name of Developer or Leasing Company at the Time of Contract Signing",
					checkValueTypes.convert(permitCompany.getNameOfDevelopersLease() + ""));
			utility.put("Claimed Federal Income Tax Credit (ITC) Cost Basis  $",
					checkValueTypes.convert(permitCompany.getClaimedFederal() + ""));
			utility.put("System cost paid by customer", checkValueTypes.convert(permitCompany.getCostPaid() + ""));
			utility.put("Contract Type", checkValueTypes.convert(permitCompany.getContactType() + ""));
			utility.put("Other Contract Type", checkValueTypes.convert(permitCompany.getTextOtherContractType() + ""));
			utility.put("Project was PACE (Property Assessed Clean Energy) Financed",
					checkValueTypes.convert(permitCompany.getProjectWasPace() + ""));
			utility.put("Choose PACE Finance", checkValueTypes.convert(permitCompany.getChoosePaceFinanced() + ""));
			utility.put("Other PACE Financed ", checkValueTypes.convert(permitCompany.getTextOtherChoosePace() + ""));
			utility.put("The SCIR of the Main Panel is:(Amps)",
					checkValueTypes.convert(permitCompany.getTheScirOfTheMain() + ""));
			utility.put("Electric Vehicles being Charged at this Site",
					checkValueTypes.convert(permitCompany.getElectriccvehichule1() + ""));
			utility.put("Other Electric Vehicles being Charged at this Site",
					checkValueTypes.convert(permitCompany.getOtherElectricVe() + ""));
			utility.put("Performance Monitoring and Reporting Services (PMRS) are being Utilized on this Project",
					checkValueTypes.convert(permitCompany.getPerformanceMonitAndRep() + ""));
			utility.put("An Existing Solar or Wind Generator System is already Interconnected at this Location",
					checkValueTypes.convert(permitCompany.getAnExistingSolarOrWind() + ""));
			utility.put(
					"I authorize Advanced Solar Solutions to submit the application on my behalf and conduct communications about this project with the Utility",
					checkValueTypes.convert(permitCompany.getIAuthorizeAdvanced() + ""));
			utility.put("Recent Annual Kwh Usage", checkValueTypes.convert(permitCompany.getKwhUsage() + ""));
			utility.put(
					"May our representatives contact the Homeowner for additional details, questions or possible errors and omissions?",
					checkValueTypes.convert(permitCompany.getContactHomeowner() + ""));
			utility.put("Meter or AC Disco Behind Locked Gate",
					checkValueTypes.convert(permitCompany.getMeterDisco() + ""));
			utility.put("Meter in Building", checkValueTypes.convert(permitCompany.getMeterBuilding() + ""));
			utility.put("Unrestrained Animal", checkValueTypes.convert(permitCompany.getUndertrainedAnimal() + ""));
			utility.put("Other Meter Access Issues at this Location",
					checkValueTypes.convert(permitCompany.getOtherSystem() + ""));
			utility.put("Request a New Service Meter from PG&E along with the interconnection application",
					checkValueTypes.convert(permitCompany.getNewService() + ""));
			utility.put("Request PG&E to Install a New Service at the Array Location",
					checkValueTypes.convert(permitCompany.getRequestPGaEToInstalNewS() + ""));
			utility.put(
					"List any Variations of the Customer's Name as they Appear on Parcel Documentation and PG&E Bills",
					checkValueTypes.convert(permitCompany.getListAnyVariations() + ""));
			utility.put("Coverage Amount (US Dollars)",
					checkValueTypes.convert(permitCompany.getCoverageamount() + ""));
			utility.put("Insuring Company's name",
					checkValueTypes.convert(permitCompany.getInsuringcompanyName() + ""));
			utility.put("policy #", checkValueTypes.convert(permitCompany.getPolicy() + ""));
			utility.put("Expected Date the Permit will be Signed Off and Delivered to PG&E",
					checkValueTypes.convert(permitCompany.getExpectedDate() + ""));
			utility.put("One or More of the Associated PG&E Accounts is on a Demand Response Program",
					checkValueTypes.convert(permitCompany.getOneOrMoreOfTheAss() + ""));
			utility.put(
					"Demand Response Programs are Incentives to Businesses that Reduce the Energy Use of their Facilities During times of Peak Demand",
					checkValueTypes.convert(permitCompany.getWhichProgram() + ""));
			utility.put("The Local Permitting Authority Rules Dictate the Currently Proposed Tie-in Point",
					checkValueTypes.convert(permitCompany.getTheLocalPermitting() + ""));
			utility.put("What Rule or Rules are Required?",
					checkValueTypes.convert(permitCompany.getWhatRuleOrRules() + ""));
			utility.put("Request PG&E to Shut Down the Transformer for Interconnection Work",
					checkValueTypes.convert(permitCompany.getRequestPGToshutDown() + ""));
			utility.put("De-Energizing of the Service Panel Can Not be Executed During Normal Business Hours",
					checkValueTypes.convert(permitCompany.getDeEnergizingOfTheService() + ""));
			utility.put("What Day of the Week are you Requesting De-Energizing?",
					checkValueTypes.convert(permitCompany.getWhatDayOfTheWeek() + ""));
			utility.put("What Time of Day?", checkValueTypes.convert(permitCompany.getWhatTimeOfDay() + ""));
			utility.put("How Many Hours will it take?", checkValueTypes.convert(permitCompany.getHowManyHours() + ""));
			utility.put("PG&E Personnel will need to Stand By During Interconnection Work",
					checkValueTypes.convert(permitCompany.getPGaEPersonnelWilleNeed() + ""));
			utility.put("Will you need to Obtain Clearance from the AHJ Prior to PG&E Re-Energizing the Service Panel?",
					checkValueTypes.convert(permitCompany.getWillYouNeedToObtain() + ""));
			utility.put("Describe the Point of Interconnection",
					checkValueTypes.convert(permitCompany.getDescribeThePointInt() + ""));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return utility;

	}

}
