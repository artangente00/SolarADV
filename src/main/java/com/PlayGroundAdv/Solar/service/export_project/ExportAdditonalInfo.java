package com.PlayGroundAdv.Solar.service.export_project;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.TiltLegs;
import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
import com.PlayGroundAdv.Solar.model.project.CustomUpload;
import com.PlayGroundAdv.Solar.repositories.libraries.BatteryRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.TiltLegsRepository;
import com.PlayGroundAdv.Solar.repositories.project.ProjectCustomFilesRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class ExportAdditonalInfo {

	final CheckValueTypesService checkValueTypes;
	final BatteryRepository batteryRepo;
	final TiltLegsRepository tiltLegsRepo;
	final ProjectCustomFilesRepository projectFilesRepo;

	public ExportAdditonalInfo(CheckValueTypesService checkValueTypes, BatteryRepository batteryRepo,
			TiltLegsRepository tiltLegsRepo, ProjectCustomFilesRepository projectFilesRepo) {
		super();
		this.checkValueTypes = checkValueTypes;
		this.batteryRepo = batteryRepo;
		this.tiltLegsRepo = tiltLegsRepo;
		this.projectFilesRepo = projectFilesRepo;
	}

	public LinkedHashMap<String, String> additionalInfo(GetPermitByIdResult permit) {

		LinkedHashMap<String, String> additional = new LinkedHashMap<>();
		try {
			additional.put("The System will include Tilted Modules with Tilt Legs",
					Boolean.TRUE.equals(permit.getPermitAdditionalInfoEntity().getTiltLegs()) ? "Yes" : "No");
			if (checkValueTypes.NotEquals(permit.getPermitAdditionalInfoEntity().getTiltLegsMod(), "")
					&& checkValueTypes.NotEquals(permit.getPermitAdditionalInfoEntity().getTiltLegsMod(), "Removed")
					&& checkValueTypes.NotEquals(permit.getPermitAdditionalInfoEntity().getTiltLegsMod(),
							"Fav Removed")) {

				TiltLegs tiltLegs = tiltLegsRepo
						.findById(Long.valueOf(permit.getPermitAdditionalInfoEntity().getTiltLegsMod()))
						.orElse(new TiltLegs());
				additional.put("Tilt Legs",
						checkValueTypes.convert(tiltLegs.getManufacturer() + " " + tiltLegs.getModel()));

			} else {
				additional.put("Tilt Legs", "");
			}
			// A.B Existing Solar System
			additional.put("There is an existing Solar system on the property",
					Boolean.TRUE.equals(permit.getPermitAdditionalInfoEntity().getExistSolarSystem()) ? "Yes" : "No");
			if (Boolean.TRUE.equals(permit.getPermitAdditionalInfoEntity().getExistSolarSystem())) {
				additional.put("Existing Inverter Technology",
						checkValueTypes.convert(permit.getPermitAdditionalInfoEntity().getExistingInverterTech()));
				additional.put("Existing PV Module Model",
						checkValueTypes.convert(permit.getPermitAdditionalInfoEntity().getExistpvmodule()));
				additional.put("Existing Modules Qty",
						checkValueTypes.convert(permit.getPermitAdditionalInfoEntity().getExistmoduleqty() + ""));
				additional.put("Existing Inverter Model",
						checkValueTypes.convert(permit.getPermitAdditionalInfoEntity().getExistinvertermodel()));
				additional.put("Existing Inverter Qty",
						checkValueTypes.convert(permit.getPermitAdditionalInfoEntity().getExistinverterqty() + ""));
				additional.put("Existing Inverter#2 Model",
						checkValueTypes.convert(permit.getPermitAdditionalInfoEntity().getExistinvertermodelTwo()));
				additional.put("Existing Inverter#2 Qty",
						checkValueTypes.convert(permit.getPermitAdditionalInfoEntity().getExistinverterqtyTwo() + ""));
				additional.put("Existing Microinverter Model",
						checkValueTypes.convert(permit.getPermitAdditionalInfoEntity().getExistmicromodel()));
				additional.put("Existing Microinverter Qty",
						checkValueTypes.convert(permit.getPermitAdditionalInfoEntity().getExistmicroqty() + ""));
				additional.put("Existing AC Disconnect",
						checkValueTypes.convert(permit.getPermitAdditionalInfoEntity().getExistacdisconnect()));
				additional.put("Existing Performance Monitoring and Reporting System (PVM)",
						checkValueTypes.convert(permit.getPermitAdditionalInfoEntity().getExistpvmeter()));
				additional.put("ACD/PVM Orientation",
						checkValueTypes.convert(permit.getPermitAdditionalInfoEntity().getAcdpvmorientation()));
				additional.put("Point of Connection",
						checkValueTypes.Equals(permit.getPermitAdditionalInfoEntity().getPointofconnection(), "Other")
								? checkValueTypes
										.convert(permit.getPermitAdditionalInfoEntity().getOtherPointConnection())
								: checkValueTypes
										.convert(permit.getPermitAdditionalInfoEntity().getPointofconnection()));
				additional.put("POC will be at...",
						checkValueTypes.Equals(permit.getPermitAdditionalInfoEntity().getPocwillbeat(), "Other")
								? checkValueTypes.convert(permit.getPermitAdditionalInfoEntity().getOtherpocwillbeat())
								: checkValueTypes.convert(permit.getPermitAdditionalInfoEntity().getPocwillbeat()));
				additional.put("Size of Back-Feed Breaker",
						checkValueTypes.Equals(permit.getPermitAdditionalInfoEntity().getPointofconnection(), "Back-fed Breaker(s)")
						? checkValueTypes.convert(permit.getPermitAdditionalInfoEntity().getSizebackfed()) : "");
				additional.put("Combining with new PV in",
						checkValueTypes.convert(permit.getPermitAdditionalInfoEntity().getCombiningpvin()));

			}

			additional.put("If System is a Tracking Type Select All That apply",
					checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getInverterModel()));

			additional.put("Tracking System Manufacturer", checkValueTypes
					.Equals(permit.getPermitProjectSiteInfoEntityTwo().getTrackingSystemManufacturer(), "Other")
							? checkValueTypes.convert(
									permit.getPermitProjectSiteInfoEntityTwo().getTrackingSystemManufacturerOther())
							: checkValueTypes.convert(
									permit.getPermitProjectSiteInfoEntityTwo().getTrackingSystemManufacturer()));
			additional.put("Tracking System Model",
					checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getTrackingSystemModel(), "Other")
							? checkValueTypes
									.convert(permit.getPermitProjectSiteInfoEntityTwo().getTrackingSystemModelOther())
							: checkValueTypes
									.convert(permit.getPermitProjectSiteInfoEntityTwo().getTrackingSystemModel()));

			additional.put("Second Tracking System Manufacturer",
					checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo()
							.getTrackingSystemManufacturerForSecondTracker(), "Other")
									? checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo()
											.getTrackingSystemManufacturerForSecondTrackerOther())
									: checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo()
											.getTrackingSystemManufacturerForSecondTracker()));
			additional.put("Second Tracking System Model",
					checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo()
							.getTrackingSystemModelForSecondTracker(), "Other")
									? checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo()
											.getTrackingSystemModelForSecondTrackerOther())
									: checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo()
											.getTrackingSystemModelForSecondTracker()));

			additional.put("I need to Include Additional Information On This Project That Has Not Been Covered",
					Boolean.TRUE.equals(permit.getPermitAdditionalInfoEntity().getInformationCovered()) ? "Yes" : "No");
			additional.put("Installation Guidelines",
					checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getInstallationGuidelines()));
			additional.put("Upload Comments",
					checkValueTypes.convert(permit.getPermitAdditionalInfoEntity().getUploadComments()));

			// R.G 08-18-2021: Update Project Excel For Structural Engineering Package
			// Inquiry
			// R.G CR-421
			additional.put("Structural Engineering Package Request", permit.getPermitEngineerEntityResult() != null &&
					Boolean.TRUE
							.equals(checkValueTypes.Equals(permit.getPermitEngineerEntityResult().getApplicableEngineering(), "true"))
									? "Yes, Please Provide Structural Engineering Review & Stamp."
									: "No, Structural Engineering Not Required.");

			// R.G CR-421
			additional.put("Electrical Engineering Package Request",
					Boolean.TRUE.equals(permit.getPermitAdditionalInfoEntity().getRequiredElectricalStamp())
							? "Yes, Please Provide Electrical Engineering Review & Stamp."
							: "No, Electrical Engineering Not Required.");

			additional.put("Delivery Size?",
					checkValueTypes.convert(permit.getPermitAdditionalInfoEntity().getFormatSize()));
			if (permit.getCustomUpload() != null && !permit.getCustomUpload().isEmpty()) {
				List<CustomUpload> customUpload = permit.getCustomUpload().stream()
						.filter(p -> p.getFiles() != null && !p.getFiles().isEmpty()).collect(Collectors.toList());
						if(customUpload.isEmpty()) {
							additional.put("Additional Custom Uploads","Cancelled by Contractor during Submission");
						}else {
							StringBuilder files = new StringBuilder();
							for (int j = 0; j < customUpload.size(); j++) {
								files.append(String.join(" / ", customUpload.get(j).getFiles()));
								if(j < customUpload.size() - 1) files.append(" / ");
							}
							additional.put("Additional Custom Uploads",files.toString());
						}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return additional;
	}
}
