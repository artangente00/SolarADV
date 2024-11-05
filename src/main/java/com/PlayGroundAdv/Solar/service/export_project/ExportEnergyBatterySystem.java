package com.PlayGroundAdv.Solar.service.export_project;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.Battery;
import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.entity.libraries.ATS;
import com.PlayGroundAdv.Solar.entity.libraries.Generator;
import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
import com.PlayGroundAdv.Solar.model.ProjectBatteryDto;
import com.PlayGroundAdv.Solar.repositories.libraries.ACDisconnectRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ATSRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.BatteryRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcCombinerDiscoRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.GeneratorRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class ExportEnergyBatterySystem {

	final CheckValueTypesService checkValueTypes;
	final BatteryRepository batteryRep;
	final ATSRepository atsRepo;
	final GeneratorRepository generatoeRepo;
	final DcCombinerDiscoRepository dcdRepo;
	final ACDisconnectRepository acdRepo;

	public ExportEnergyBatterySystem(CheckValueTypesService checkValueTypes, BatteryRepository batteryRep,
			ATSRepository atsRepo, GeneratorRepository generatoeRepo, DcCombinerDiscoRepository dcdRepo,
			ACDisconnectRepository acdRepo) {
		super();
		this.checkValueTypes = checkValueTypes;
		this.batteryRep = batteryRep;
		this.atsRepo = atsRepo;
		this.generatoeRepo = generatoeRepo;
		this.dcdRepo = dcdRepo;
		this.acdRepo = acdRepo;
	}

	public LinkedHashMap<String, String> energyBatterySystem(GetPermitByIdResult permit) {

		LinkedHashMap<String, String> energySystem = new LinkedHashMap<>();
		try {

			energySystem.put("Grid-Tied Sys. Or Off-Grid/Standalone",
					checkValueTypes.convert(permit.getPermitAdditionalInfoEntity().getGridTiedOrStandalone()));

			energySystem.put("System will be Paired with Energy/Battery Storage System (ESS)",
					Boolean.TRUE.equals(permit.getPermitAdditionalInfoEntity().getBatteryStorage()) ? "Yes" : "No");

			if (permit.getPermitAdditionalInfoEntity() != null
					&& checkValueTypes.Equals(permit.getPermitAdditionalInfoEntity().getBatteryStorage(), true)) {

				energySystem.put("Type of Grid-Tied ESS", permit.getPermitEnergyBatterySystem().getTypeGridTied());

				if (permit.getPermitEnergyBatterySystem().getBatteries() != null) {
					for (int i = 0; i < permit.getPermitEnergyBatterySystem().getBatteries().size(); i++) {

						ProjectBatteryDto b = permit.getPermitEnergyBatterySystem().getBatteries().get(i);
						if (checkValueTypes.isLongPositive(b.getBattery())) {
							Battery battery = batteryRep.findById(b.getBattery()).orElse(null);
							energySystem.put("ESS/Battery Model #" + (i + 1),
									battery != null ? battery.getManufacturer() + " " + battery.getModel() : "");
							energySystem.put("Quantity of ESS/Battery Model #" + (i + 1),
									b.getBatteryQuantity() != null ? b.getBatteryQuantity() + "" : "");
						}

					}

				}

				energySystem.put("Auto Transfer Switch/Smart Transfer Switch Included?",
						Boolean.TRUE.equals(permit.getPermitEnergyBatterySystem().getAtsIncluded()) ? "Yes" : "No");
				if (permit.getPermitEnergyBatterySystem().getIdAts() != null
						&& checkValueTypes.isLongPositive(permit.getPermitEnergyBatterySystem().getIdAts())) {

					ATS equipment = atsRepo.findById(permit.getPermitEnergyBatterySystem().getIdAts()).orElse(null);

					energySystem.put("Auto transfer Switch #1",
							equipment != null ? equipment.getManufacturer() + " " + equipment.getModel() : "");
					energySystem.put("Auto transfer Switch #1 Qty",
							permit.getPermitEnergyBatterySystem().getQtyAts() + "");

					if (checkValueTypes.isLongPositive(permit.getPermitEnergyBatterySystem().getIdSecondAts())) {
						ATS secondequipment = atsRepo.findById(permit.getPermitEnergyBatterySystem().getIdSecondAts())
								.orElse(null);
						energySystem.put("Auto transfer Switch #2",
								secondequipment != null
										? secondequipment.getManufacturer() + " " + secondequipment.getModel()
										: "");
						energySystem.put("Auto transfer Switch #2 Qty",
								permit.getPermitEnergyBatterySystem().getQtySecondAts() + "");
					}
				}

				energySystem.put("Will a DC Disco be used to isolate the ESS DC Connection?",
						Boolean.TRUE.equals(permit.getPermitEnergyBatterySystem().getDcDisconnectIncluded()) ? "Yes"
								: "No");
				if (permit.getPermitEnergyBatterySystem().getIdDcDisconnect() != null
						&& checkValueTypes.isLongPositive(permit.getPermitEnergyBatterySystem().getIdDcDisconnect())) {
					DCCombinerDisconnectEntity equipment = dcdRepo
							.findById(permit.getPermitEnergyBatterySystem().getIdDcDisconnect()).orElse(null);
					energySystem.put("DC Disconnect ",
							equipment != null ? equipment.getManufacturer() + " " + equipment.getModel() : "");
				}

				energySystem.put("AC Disco will be used to isolate the ESS AC Connection?",
						Boolean.TRUE.equals(permit.getPermitEnergyBatterySystem().getAcDisconnectIncluded()) ? "Yes"
								: "No");
				if (permit.getPermitEnergyBatterySystem().getIdAcDisconnect() != null
						&& checkValueTypes.isLongPositive(permit.getPermitEnergyBatterySystem().getIdAcDisconnect())) {

					ACDisconnect equipment = acdRepo.findById(permit.getPermitEnergyBatterySystem().getIdAcDisconnect())
							.orElse(null);
					energySystem.put("AC Disconnect #1",
							equipment != null ? equipment.getManufacturer() + " " + equipment.getModel() : "");
					energySystem.put("AC Disconnect #1 Qty", permit.getPermitEnergyBatterySystem().getQtyAcd() + "");

					if (checkValueTypes
							.isLongPositive(permit.getPermitEnergyBatterySystem().getIdSecondAcDisconnect())) {
						ACDisconnect secondequipment = acdRepo
								.findById(permit.getPermitEnergyBatterySystem().getIdSecondAcDisconnect()).orElse(null);
						energySystem.put("AC Disconnect #2",
								secondequipment != null
										? secondequipment.getManufacturer() + " " + secondequipment.getModel()
										: "");
						energySystem.put("AC Disconnect #2 Qty",
								permit.getPermitEnergyBatterySystem().getQtySecondAcd() + "");
					}
				}
				energySystem.put("Will a Separate RSD Actuation Device be connected to the ESS Specifically?",
						Boolean.TRUE.equals(permit.getPermitEnergyBatterySystem().getRsdConnected()) ? "Yes" : "No");

				energySystem.put("Fuel Generator Included?",
						Boolean.TRUE.equals(permit.getPermitEnergyBatterySystem().getGeneratorIncluded()) ? "Yes"
								: "No");
				if (Boolean.TRUE.equals(permit.getPermitEnergyBatterySystem().getGeneratorIncluded())) {
					energySystem.put("Existing OR Proposed",
							permit.getPermitEnergyBatterySystem().getGeneratorStatus());

					if (permit.getPermitEnergyBatterySystem().getIdGenerator() != null
							&& checkValueTypes.isLongPositive(permit.getPermitEnergyBatterySystem().getIdGenerator())) {
						Generator equipment = generatoeRepo
								.findById(permit.getPermitEnergyBatterySystem().getIdGenerator()).orElse(null);
						energySystem.put("Generator Manufacturer & Model ",
								equipment != null ? equipment.getManufacturer() + " " + equipment.getModel() : "");
					}

					energySystem.put("Fuel Type", permit.getPermitEnergyBatterySystem().getFuelType());
					energySystem.put("Type of Fuel Distribution Pipe",
							checkValueTypes.Equals(permit.getPermitEnergyBatterySystem().getFuelDistributionPipeType(),
									"Other") ? permit.getPermitEnergyBatterySystem().getFuelDistributionPipeTypeOther()
											: permit.getPermitEnergyBatterySystem().getFuelDistributionPipeType());
					energySystem.put("Pipe Size",
							checkValueTypes.Equals(permit.getPermitEnergyBatterySystem().getPipeSize(), "Other")
									? permit.getPermitEnergyBatterySystem().getPipeSizeOther()
									: permit.getPermitEnergyBatterySystem().getPipeSize());
				}
				energySystem.put("Upload Comments", permit.getPermitEnergyBatterySystem().getEssSpecificationComment());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return energySystem;
	}
}
