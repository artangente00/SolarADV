package com.PlayGroundAdv.Solar.service.export_project;

import java.util.LinkedHashMap;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Engineers;
import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
import com.PlayGroundAdv.Solar.repositories.libraries.EngineersRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class ExportADVInputs {

	final CheckValueTypesService checkValueTypes;
	final EngineersRepository engineersRepo;

	public ExportADVInputs(CheckValueTypesService checkValueTypes, EngineersRepository engineersRepo) {
		super();
		this.checkValueTypes = checkValueTypes;
		this.engineersRepo = engineersRepo;
	}

	public LinkedHashMap<String, String> advPermitsInputs(GetPermitByIdResult permit) {

		LinkedHashMap<String, String> adv = new LinkedHashMap<>();

		try {
			adv.put("Open 3 Line Diagram Master Library",
					Boolean.TRUE.equals(permit.getPermitAdvEntityResult().getOpenTldLibrary()) ? "Yes" : "No");
			adv.put("Use from the portal's Shortlisted 3 Line Diagram List for E-003 mapping",
					Boolean.TRUE.equals(permit.getPermitAdvEntityResult().getOpenTldLibrary()) ? "Yes" : "No");
			adv.put("3 Line Diagram List", checkValueTypes.convert(permit.getPermitAdvEntityResult().getTldList()));
			adv.put("Building or Residence Exposure Category",
					checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getResidenceBindingCategory()));
			adv.put("Other Building or Residence Exposure Category",
					checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getTextOtherExpo()));
			String moduleLayout = "Module Layout";
			if (checkValueTypes.NotEquals(permit.getPermitAdvEntityResult().getModuleLayout(), "")) {
				if (checkValueTypes.Equals(permit.getPermitAdvEntityResult().getModuleLayout(), "Other")) {
					adv.put(moduleLayout,
							checkValueTypes.convert(permit.getPermitAdvEntityResult().getModuleLayoutOther()));
				} else {
					adv.put(moduleLayout,
							checkValueTypes.convert(permit.getPermitAdvEntityResult().getModuleLayout()));
				}

			} else {
				adv.put(moduleLayout, "");
			}
			adv.put("Elevation", checkValueTypes.convert(permit.getPermtiWeatherEntityResult().getElevation()));
			String deuxAverageHigh = "0.2% Average High";
			String fourAverageHigh = "0.4% Average High";
			if (checkValueTypes.NotEquals(permit.getPermtiWeatherEntityResult().getQuatrePourCentAverageHigh(), "")) {
				if (checkValueTypes.Equals(permit.getPermtiWeatherEntityResult().getQuatrePourCentAverageHigh(),
						"Other")
						&& checkValueTypes
								.NotEquals(permit.getPermtiWeatherEntityResult().getQuatrePourCentAvHighOther(), "")) {
					adv.put(fourAverageHigh, checkValueTypes
							.convert(permit.getPermtiWeatherEntityResult().getQuatrePourCentAvHighOther()));
				} else {
					adv.put(fourAverageHigh, checkValueTypes
							.convert(permit.getPermtiWeatherEntityResult().getQuatrePourCentAverageHigh()));
				}
			} else {
				adv.put(fourAverageHigh, "");
			}
			if (checkValueTypes.NotEquals(permit.getPermtiWeatherEntityResult().getDeuxPourCentAverageHigh(), "")) {
				if (checkValueTypes.Equals(permit.getPermtiWeatherEntityResult().getDeuxPourCentAverageHigh(), "Other")
						&& checkValueTypes.NotEquals(
								permit.getPermtiWeatherEntityResult().getDeuxPourCentAverageHighOther(), "")) {
					adv.put(deuxAverageHigh, checkValueTypes
							.convert(permit.getPermtiWeatherEntityResult().getDeuxPourCentAverageHighOther()));
				} else {
					adv.put(deuxAverageHigh, checkValueTypes
							.convert(permit.getPermtiWeatherEntityResult().getDeuxPourCentAverageHigh()));
				}
			} else {
				adv.put(deuxAverageHigh, "");
			}
			String extreamMinimum = "Extreme Minimum";
			if (checkValueTypes.NotEquals(permit.getPermtiWeatherEntityResult().getExtremeMinimum(), "")) {
				if (checkValueTypes.Equals(permit.getPermtiWeatherEntityResult().getExtremeMinimum(), "Other")
						&& checkValueTypes.NotEquals(permit.getPermtiWeatherEntityResult().getExtremeMinimumOther(),
								"")) {
					adv.put(extreamMinimum,
							checkValueTypes.convert(permit.getPermtiWeatherEntityResult().getExtremeMinimumOther()));
				} else {
					adv.put(extreamMinimum,
							checkValueTypes.convert(permit.getPermtiWeatherEntityResult().getExtremeMinimum()));
				}
			} else {
				adv.put(extreamMinimum, "");
			}

			if (checkValueTypes.isNumericNotZero(permit.getPermitEngineerEntityResult().getEngineeredBy())) {
				Engineers engineer = engineersRepo
						.findById(Long.valueOf(permit.getPermitEngineerEntityResult().getEngineeredBy()))
						.orElse(new Engineers());
				adv.put("Engineered by",
						checkValueTypes.convert(engineer.getContact() + " " + engineer.getLicenseState()));

			} else {
				adv.put("Engineered by", "");
			}
			adv.put("Will the Engineer most likely determine modifications to roofing structural members?",
					Boolean.TRUE.equals(permit.getPermitEngineerEntityResult().getDetermineModification()) ? "Yes"
							: "No");
			adv.put("ASCE 7-10 Wind Speed", checkValueTypes.convert(permit.getPermitAdvEntityResult().getWindSpeed()));
			adv.put("Snow Load", checkValueTypes.convert(permit.getPermitAdvEntityResult().getSnowLoad()));
			adv.put("Street Zoom", checkValueTypes.convert(permit.getPermitAdvEntityResult().getGoogleZoom() + ""));
			adv.put("Engineering Firm",
					checkValueTypes.convert(permit.getPermitAdvEntityResult().getEngineeringFirm()));
			
			adv.put("Upload Google Earth Image Comments",
					checkValueTypes.convert(permit.getPermitAdvEntityResult().getUploadCommentsGoogle()));
			adv.put("Upload NearMap Image Comments",
					checkValueTypes.convert(permit.getPermitAdvEntityResult().getUploadCommentsNearMap()));
			
			adv.put("Customers Service Agreement ID Number",
					checkValueTypes.convert(permit.getPermitAdvEntityResult().getCustomersServiceAgreementIDNumber()));
			adv.put("Customers Rate Schedule",
					checkValueTypes.convert(permit.getPermitAdvEntityResult().getCustomersRateSchedule()));
			adv.put("Customer Name", checkValueTypes.convert(permit.getPermitAdvEntityResult().getCustomerName()));
			adv.put("Customer Account Number",
					checkValueTypes.convert(permit.getPermitAdvEntityResult().getCustomersAccountNumber()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return adv;
	}

}
